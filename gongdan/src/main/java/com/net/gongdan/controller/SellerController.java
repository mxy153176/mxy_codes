package com.net.gongdan.controller;

import com.net.gongdan.model.JsonVo;
import com.net.gongdan.model.SellerModel;
import com.net.gongdan.query.PagedData;
import com.net.gongdan.query.SellerQuery;
import com.net.gongdan.service.SellerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
@CrossOrigin
public class SellerController {


    @Autowired
    private SellerService sellerService;

    @RequestMapping("/sellerList")
    public String queryList(HttpServletRequest request, ModelMap model) {

        SellerQuery query = new SellerQuery();

        PagedData<SellerModel> seller = sellerService.query(query);
        model.put("pageVo", seller);
        return "sellerList";
    }

    @RequestMapping("/toUpdateOrAddSeller")
    public String toUpdateOrAdd(HttpServletRequest request, ModelMap model) {

        String id = request.getParameter("id");

        if (StringUtils.isBlank(id)) {
            return "sellerForm";
        }
        SellerModel seller = sellerService.findSellerById(id);
        model.put("seller",seller);
        /**返回页面地址*/
        return "sellerForm";
    }

    @RequestMapping("/updateOrAddSeller")
    @ResponseBody
    public JsonVo<String> updateOrAdd(HttpServletRequest request, ModelMap model) {

        String id = request.getParameter("id");
        JsonVo<String> json = new JsonVo<String>();

        String name = request.getParameter("name");


        if(StringUtils.isBlank(name)){
            json.setResult(false);
            json.setMsg("参数出错");
            return json;
        }
        String level = request.getParameter("level");
        String address = request.getParameter("address");
        String legalPerson = request.getParameter("legalPerson");
        String legalPersonCode = request.getParameter("legalPersonCode");
        try {
            SellerModel mode = new SellerModel();
            if(StringUtils.isNotBlank(id)){
                mode.setId(Integer.valueOf(id));
            }
            mode.setAddress(address);
            mode.setLegalPerson(legalPerson);
            mode.setName(name);
            mode.setLevel(level);
            mode.setLegalPersonCode(legalPersonCode);

            sellerService.updateOrAdd(mode);

            // 检测校验结果
            validate(json);
            json.setResult(true);
        } catch (Exception e) {
            json.setResult(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    @RequestMapping("/sellerDelete")
    public String delete(HttpServletRequest request, ModelMap model) {
        String id = request.getParameter("id");

        sellerService.deleteSeller(id);
        SellerQuery query = new SellerQuery();
        PagedData<SellerModel> seller = sellerService.query(query);
        model.put("pageVo", seller);
        return "sellerList";
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
}
