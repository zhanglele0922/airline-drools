package com.aograph.controllers;

import com.aograph.config.ClickHouseConfig;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
 * @create 2021/12/15
 **/
@RestController
@RequestMapping("/global")
public class GobalTestController {

    @Autowired
    private KieBase kieBase;

    @RequestMapping("/test")
    public String testGlobal(){
        ClickHouseConfig.getConn();
        KieSession session=kieBase.newKieSession();
        List testList=new ArrayList<>();
        testList.add("sdfsdfsdf");
        testList.add(new HashMap<>());
        session.setGlobal("discount_cabin",testList );
        session.fireAllRules();
        return "OK";
    }
}
