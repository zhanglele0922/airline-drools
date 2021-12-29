package com.aograph.chuan_air;


import com.alibaba.fastjson.JSONArray;
import com.aograph.config.ClickHouseConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void init(KieSession kieSession){

        String date= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));
        String sql = "select * from t_tbl_fd where insert_date = (select max(insert_date) from t_tbl_fd) and airline_2code ='3U'";
        List<Map> result = ClickHouseConfig.exeSql(sql);
        String json = JSONArray.toJSONString(result);
        List<DiscountCabin> discount_cabin = null;
        discount_cabin = JSONArray.parseArray(json,DiscountCabin.class);
        kieSession.setGlobal("discount_cabin",discount_cabin);

        List<RmDiscountCabin> list=jdbcTemplate.query("select * from rm_discount_cabin where sale_date_start <='"+time+"' and sale_date_end>= '"+time+"'"
        ,new Object[]{},new BeanPropertyRowMapper<>(RmDiscountCabin.class));

        kieSession.setGlobal("discount_cabin_extend",list);

    }
}
