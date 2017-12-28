package com.net.gongdan.controller;

import com.net.gongdan.model.GoodsModel;
import com.net.gongdan.model.JsonVo;
import com.net.gongdan.model.SellerModel;
import com.net.gongdan.query.GoodsQuery;
import com.net.gongdan.query.PagedData;
import com.net.gongdan.query.SellerQuery;
import com.net.gongdan.service.GoodsService;
import com.net.gongdan.service.SellerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SellerService sellerService;

    @RequestMapping("/goodsList")
    public String queryList(HttpServletRequest request, ModelMap model) {

        GoodsQuery query = new GoodsQuery();

        PagedData<GoodsModel> goods = goodsService.query(query);
        model.put("pageVo", goods);
        return "goodsList";
    }

    @RequestMapping("/toUpdateOrAdd")
    public String toUpdateOrAdd(HttpServletRequest request, ModelMap model) {

        String id = request.getParameter("id");

        if (StringUtils.isBlank(id)) {
            return "goodsForm";
        }

        SellerQuery query = new SellerQuery();

        PagedData<SellerModel> seller = sellerService.query(query);


        GoodsModel goods = goodsService.findGoodsById(id);
        model.put("gongdan",goods);

        List<SellerModel> list =  seller.getList();
        if(list!=null){
            for(SellerModel sm:list){
                if(StringUtils.equals(String.valueOf(sm.getId()),goods.getSellerId())){
                    sm.setSelect(true);
                }
            }

            model.put("sellerList", seller.getList());
        }


        return "goodsForm";
    }

    @RequestMapping("/updateOrAddGoods")
    @ResponseBody
    public JsonVo<String> updateOrAdd(HttpServletRequest request, ModelMap model) {

        String id = request.getParameter("id");
        JsonVo<String> json = new JsonVo<String>();

        String goodsName = request.getParameter("goodsName");


        if(StringUtils.isBlank(goodsName)){
            json.setResult(false);
            json.setMsg("参数出错");
            return json;
        }
        String price = request.getParameter("price");
        String sellerId = request.getParameter("sellerId");
        String status = request.getParameter("status");
        String masterPicture = request.getParameter("masterPicture");
        String productionPlace = request.getParameter("productionPlace");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String label = request.getParameter("label");
        try {
        GoodsModel mode = new GoodsModel();
        if(StringUtils.isNotBlank(id)){
            mode.setId(Integer.valueOf(id));
        }
        mode.setGoodsName(goodsName);
        mode.setDesc(desc);
        mode.setLabel(label);
        mode.setType(type);
        mode.setStatus(StringUtils.equals(status,"on")?"1":"0");
        mode.setSellerId(sellerId);
        mode.setPrice(price);
        mode.setProductionPlace(productionPlace);

        goodsService.updateOrAdd(mode);

        // 检测校验结果
        validate(json);
        json.setResult(true);
        } catch (Exception e) {
            json.setResult(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    @RequestMapping("/goodsDelete")
    public String delete(HttpServletRequest request, ModelMap model) {
        String id = request.getParameter("id");

        goodsService.deleteGoods(id);
        GoodsQuery query = new GoodsQuery();
        PagedData<GoodsModel> goods = goodsService.query(query);
        model.put("pageVo", goods);
        return "gongdan-list";
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
