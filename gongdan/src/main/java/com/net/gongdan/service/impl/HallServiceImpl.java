package com.net.gongdan.service.impl;

import com.net.gongdan.dao.HallMapper;
import com.net.gongdan.model.GongdanModel;
import com.net.gongdan.model.HallModel;
import com.net.gongdan.model.PaidanbociModel;
import com.net.gongdan.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class HallServiceImpl implements HallService {

    @Autowired
    private HallMapper hallMapper;


    @Override
    public HallModel findHallById(String id) {
        return hallMapper.findHallById(id);
    }

    @Override
    public List<PaidanbociModel> countGongdanByTime(Map<String, Object> param) {
//查询的过程中,已经将结果集封装到了param集合中
//根据key获取到结果集,并进行强转
        hallMapper.countGongdanByTime(param);
        List<PaidanbociModel> pdList=(List<PaidanbociModel>)param.get("result");
        return pdList;
    }



}
