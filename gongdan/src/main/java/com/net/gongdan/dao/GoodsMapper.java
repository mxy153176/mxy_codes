package com.net.gongdan.dao;

import com.net.gongdan.model.GoodsModel;
import com.net.gongdan.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    public List<GoodsModel> queryList(GoodsQuery goodsQuery);//goodsQuery为输入对象

    int countList(GoodsQuery query);

    public void insertGoods(GoodsModel model);

    public void updateGoods(GoodsModel model);

    GoodsModel findGoodsById(@Param("id") String id);

    void deleteGoods(@Param("id") String id);
}
