package com.net.gongdan.service;

import com.net.gongdan.model.GoodsModel;
import com.net.gongdan.query.GoodsQuery;
import com.net.gongdan.query.PagedData;

public interface GoodsService {

    /**
     *  商品查询
     * @param query
     * @return
     */
    public PagedData<GoodsModel> query(GoodsQuery query);

    /**
     * 商品新增或修改
     * @param mode
     */
    void updateOrAdd(GoodsModel mode);

    /**
     * 商品根据id查询
     * @param id
     * @return
     */
    GoodsModel findGoodsById(String id);

    /**
     * 商品删除
     * @param id
     */
    void deleteGoods(String id);
}
