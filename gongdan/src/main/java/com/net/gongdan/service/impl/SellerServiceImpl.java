package com.net.gongdan.service.impl;

import com.net.gongdan.dao.SellerMapper;
import com.net.gongdan.model.SellerModel;
import com.net.gongdan.query.PagedData;
import com.net.gongdan.query.SellerQuery;
import com.net.gongdan.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    public PagedData<SellerModel> query(SellerQuery query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }

        try {
            PagedData<SellerModel> pagedData = new PagedData<SellerModel>();
            pagedData.setPageNo(query.getPageNo());
            pagedData.setPageSize(query.getPageSize());

            List<SellerModel> taskConfigDOs = sellerMapper.queryList(query);

            pagedData.setList(taskConfigDOs);

            if (taskConfigDOs != null && !taskConfigDOs.isEmpty() && query.getNeedPagedInfo()) {
                pagedData.setTotalNum(sellerMapper.countList(query));
            }
            return pagedData;
        } catch (Exception e) {
//            throw new SeiyaException(location, e);

            return null;
        }
    }

    @Override
    public void updateOrAdd(SellerModel mode) {
        if(mode.getId()==0){
            sellerMapper.insertSeller(mode);
        }else{
            sellerMapper.updateSeller(mode);
        }
    }

    @Override
    public SellerModel findSellerById(String id) {
        return sellerMapper.findSellerById(id);
    }

    @Override
    public void deleteSeller(String id) {
        sellerMapper.deleteSeller(id);
    }
}
