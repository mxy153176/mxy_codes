package com.net.gongdan.service;

import com.net.gongdan.model.CustomerModel;
import com.net.gongdan.model.GongdanModel;

public interface CustomerService {


    /**
     * 商家新增或修改
     * @param mode
     */
    void updateOrAdd(CustomerModel mode);

    /**
     * 商家根据id查信息
     * @param id
     * @return
     */
    CustomerModel findCustomerById(String id);

    /**
     * 商家删除
     * @param id
     */
    void deleteCustomer(String id);


}
