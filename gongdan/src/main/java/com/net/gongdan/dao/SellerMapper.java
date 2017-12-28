package com.net.gongdan.dao;

import com.net.gongdan.model.SellerModel;
import com.net.gongdan.query.SellerQuery;

import java.util.List;

public interface SellerMapper {

    public List<SellerModel> queryList(SellerQuery goodsQuery);

    int countList(SellerQuery query);

    public void insertSeller(SellerModel model);

    public void updateSeller(SellerModel model);

    SellerModel findSellerById(String id);

    void deleteSeller(String id);
}
