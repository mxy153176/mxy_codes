package com.net.gongdan.service.impl;

import com.net.gongdan.dao.CustomerMapper;
import com.net.gongdan.model.CustomerModel;
import com.net.gongdan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public void updateOrAdd(CustomerModel mode) {
        if(mode.getId()==0){
            customerMapper.insertCustomer(mode);
        }else{
            customerMapper.updateCustomer(mode);
        }
    }

    @Override
    public CustomerModel findCustomerById(String id) {
        return customerMapper.findCustomerById(id);
    }

    @Override
    public void deleteCustomer(String id) {
        customerMapper.deleteCustomer(id);
    }
}
