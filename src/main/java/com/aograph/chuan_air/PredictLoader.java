package com.aograph.chuan_air;


import com.alibaba.fastjson.JSONArray;
import com.aograph.config.ClickHouseConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author lelezhang
 * @description
 * @create 2021/12/28
 **/
@Component
public class PredictLoader {

    @Autowired
    private ClickHouseConfig clickHouseConfig;

    @Autowired
    private KieBase kieBase;


    public void init(KieSession kieSession){

        String sql = "select * from t_tbl_fd where insert_date = (select max(insert_date) from t_tbl_fd) and airline_2code ='3U'";
        List<Map> result = ClickHouseConfig.exeSql(sql);
        String json = JSONArray.toJSONString(result);
        List<DiscountCabin> discount_cabin = null;
        discount_cabin = JSONArray.parseArray(json,DiscountCabin.class);
        kieSession.setGlobal("discount_cabin",discount_cabin);

    }
}
