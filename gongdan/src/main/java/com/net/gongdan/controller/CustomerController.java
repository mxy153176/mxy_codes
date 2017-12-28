package com.net.gongdan.controller;

import com.net.gongdan.dao.CustomerMapper;
import com.net.gongdan.dao.HallMapper;
import com.net.gongdan.model.*;
import com.net.gongdan.query.UserQuery;
import com.net.gongdan.service.CustomerService;
import com.net.gongdan.service.HallService;
import com.net.gongdan.service.UserService;
import oracle.jdbc.driver.OracleTypes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private HallService hallService;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private UserService userService;

    List<HallModel> hall_list = new LinkedList<>();

    public CustomerController() {
    }

    @RequestMapping("/myController")
    @ResponseBody
    public JsonVo<HallModel> toHandle(HttpServletRequest request){
        CustomerModel model = new CustomerModel();
        JsonVo<HallModel> json = new JsonVo<HallModel>();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if(StringUtils.isBlank(name)){
            json.setResult(false);
            json.setMsg("参数出错");
            return json;
        }
        String tel = request.getParameter("tel");
        String address = request.getParameter("addr");
        try {
            CustomerModel mode = new CustomerModel();
            mode.setId(0);
            mode.setAddress(address);
            mode.setName(name);
            mode.setTel(tel);
            customerService.updateOrAdd(mode);
            if (request.getParameter("axis_x") == null || request.getParameter("axis_x").length() <= 0)
            {
                json.setResult(true);
                json.setMsg("用户地点未知！");
                return json;
            }
            System.out.print(request.getParameter("axis_x")+",");
            System.out.print(request.getParameter("axis_y"));
            Double axis_X = Double.valueOf(request.getParameter("axis_x")).doubleValue();
            Double axis_Y = Double.valueOf(request.getParameter("axis_y")).doubleValue();
            CoordinateModel coordinate_local = new CoordinateModel();
            coordinate_local.setX(axis_X);
            coordinate_local.setY(axis_Y);
            for (int i=1;i<hallMapper.countList()+1;i++)
            {
                hall_list.add(hallService.findHallById(Integer.toString(i)));
            }
            HallModel nearest_hall = findNearestHall(coordinate_local,hall_list);
            json.setT(nearest_hall);
            // 检测校验结果
            validate(json);
            json.setResult(true);
        } catch (Exception e) {
            json.setResult(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }


    /**
     * 参数校验
     *
     * @param json
     *            json数据Bean
     * @throws Exception
     */
    protected <T> void validate(JsonVo<T> json) throws Exception {
        if (json.getErrors().size() > 0) {
            json.setResult(false);
            throw new Exception("有错误发生");
        } else {
            json.setResult(true);
        }
    }

    protected HallModel findNearestHall(CoordinateModel coordinate,List<HallModel> hall_list)
    {
        Double Min_distance = 10000000.0;
        HallModel nearestHall = new HallModel();
        for (int i = 0; i < hall_list.size(); i++) {
           Double distance = 6371004.0*Math.acos((Math.sin(Math.toRadians(hall_list.get(i).getAxis_Y()))*Math.sin(Math.toRadians(coordinate.getY()))+Math.cos(Math.toRadians(hall_list.get(i).getAxis_Y()))*Math.cos(Math.toRadians(coordinate.getY()))*Math.cos(Math.toRadians(Math.abs(hall_list.get(i).getAxis_X()-coordinate.getX())))));
           distance = (double)Math.round(distance*100000000)/100000000;
           if (distance<Min_distance)
           {
               Min_distance=distance;
               nearestHall=hall_list.get(i);
           }
        }
        System.out.print(nearestHall.getName()+":"+nearestHall.getAxis_X()+","+nearestHall.getAxis_Y()+"\n");
        System.out.print("距离:"+Min_distance+"m\n");
        return nearestHall;
    }

    @RequestMapping("/modifyRecord")
    @ResponseBody
    public JsonVo<List<RecordModel>> searchModifyRecord(HttpServletRequest request) {
        String tel = request.getParameter("tel");
        JsonVo<List<RecordModel>> json = new JsonVo<List<RecordModel>>();
        List<RecordModel> model = customerMapper.modifyRecord(tel);
        json.setT(model);
        return json;
    }

    @RequestMapping("/countPd")
    @ResponseBody
    public JsonVo<List<PaidanbociModel>> countPd(HttpServletRequest request){
        String start_time=request.getParameter("start_time");
        String end_time=request.getParameter("end_time");
        String sql_where=request.getParameter("main_type");
        sql_where=" and main_type='"+sql_where+"' ";
        //System.out.print(gd_id);
        Map<String, Object> param = new HashMap<String, Object>();
        //对于in参数赋值
        param.put("start_time", start_time);
        param.put("end_time", end_time);
        param.put("sql_where", sql_where);
        //对于out参数 申明
        param.put("result", OracleTypes.CURSOR);
        JsonVo<List<PaidanbociModel>> json = new JsonVo<List<PaidanbociModel>>();
        List<PaidanbociModel> model_1 = hallService.countGongdanByTime(param);
        json.setT(model_1);
        //System.out.print(model_1);
        return json;
    }

    @PostMapping("/getUserInfo")
    @ResponseBody
    public JsonVo<UserModel> getUserInfo(@RequestBody AccountModel account){
        String staff_id=account.getStaff_id();
        String password=account.getPassword();
        UserQuery userQuery = new UserQuery();
        userQuery.setStaff_id(staff_id);
        userQuery.setPassword(password);
        JsonVo<UserModel> json = new JsonVo<UserModel>();
        try{
            UserModel user_info=userService.varifyUserInfo(userQuery);
            if(user_info==null){
                json.setResult(false);
                json.setMsg("账号密码错误！");
            }
            else {
                json.setT(user_info);
                // 检测校验结果
                validate(json);
                json.setResult(true);
            }
        } catch (Exception e) {
            json.setResult(false);
            json.setMsg(e.getMessage());
        }
        return  json;
    }
}
