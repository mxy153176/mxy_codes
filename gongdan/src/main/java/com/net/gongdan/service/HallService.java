package com.net.gongdan.service;

import com.net.gongdan.model.GongdanModel;
import com.net.gongdan.model.HallModel;
import com.net.gongdan.model.PaidanbociModel;
import java.util.List;
import java.util.Map;

public interface HallService {
    
    /**
     * 商家根据id查信息
     * @param id
     * @return
     */
    HallModel findHallById(String id);

    /**
     * 时间段统计
     * @param param
     * @return
     */
    List<PaidanbociModel> countGongdanByTime(Map<String, Object> param);

}
