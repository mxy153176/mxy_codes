package com.net.gongdan.dao;

import com.net.gongdan.model.CustomerModel;
import com.net.gongdan.model.RecordModel;
import com.net.gongdan.query.CustomerQuery;

import java.util.List;

public interface CustomerMapper {

    public List<CustomerModel> queryList(CustomerQuery goodsQuery);

    public List<RecordModel> modifyRecord(String tel);

    int countList(CustomerQuery query);

    public void insertCustomer(CustomerModel model);

    public void updateCustomer(CustomerModel model);

    CustomerModel findCustomerById(String id);

    void deleteCustomer(String id);
}
