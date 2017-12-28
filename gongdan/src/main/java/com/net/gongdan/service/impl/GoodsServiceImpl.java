package com.net.gongdan.service.impl;

import com.net.gongdan.dao.GoodsMapper;
import com.net.gongdan.model.GoodsModel;
import com.net.gongdan.query.GoodsQuery;
import com.net.gongdan.query.PagedData;
import com.net.gongdan.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public PagedData<GoodsModel> query(GoodsQuery query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }

        try {
            PagedData<GoodsModel> pagedData = new PagedData<GoodsModel>();
            pagedData.setPageNo(query.getPageNo());
            pagedData.setPageSize(query.getPageSize());

            List<GoodsModel> taskConfigDOs = goodsMapper.queryList(query);

            pagedData.setList(taskConfigDOs);

            if (taskConfigDOs != null && !taskConfigDOs.isEmpty() && query.getNeedPagedInfo()) {
                pagedData.setTotalNum(goodsMapper.countList(query));
            }
            return pagedData;
        } catch (Exception e) {
//            throw new SeiyaException(location, e);
            return null;
        }
    }

    @Override
    public void updateOrAdd(GoodsModel mode) {
        if(mode.getId()==0){
            goodsMapper.insertGoods(mode);
        }else{
            goodsMapper.updateGoods(mode);
        }
    }

    @Override
    public GoodsModel findGoodsById(String id) {
        return goodsMapper.findGoodsById(id);
    }

    @Override
    public void deleteGoods(String id) {
        goodsMapper.deleteGoods(id);
    }
}
