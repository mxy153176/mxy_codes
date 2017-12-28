package com.net.gongdan.service;

import com.net.gongdan.model.SellerModel;
import com.net.gongdan.query.PagedData;
import com.net.gongdan.query.SellerQuery;

public interface SellerService {

    /**
     * 商家查询
     * @param query
     * @return
     */
    public PagedData<SellerModel> query(SellerQuery query);

    /**
     * 商家新增或修改
     * @param mode
     */
    void updateOrAdd(SellerModel mode);

    /**
     * 商家根据id查信息
     * @param id
     * @return
     */
    SellerModel findSellerById(String id);

    /**
     * 商家删除
     * @param id
     */
    void deleteSeller(String id);

}
