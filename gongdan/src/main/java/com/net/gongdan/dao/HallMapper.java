package com.net.gongdan.dao;

import com.net.gongdan.model.GongdanModel;
import com.net.gongdan.model.HallModel;

import java.util.List;
import java.util.Map;

public interface HallMapper {

    int countList();

    HallModel findHallById(String id);

    public List<GongdanModel> findGongdanById(String gd_id);

    public void countGongdanByTime(Map<String, Object> param);

}
