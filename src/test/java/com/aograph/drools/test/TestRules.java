package com.aograph.drools.test;

import com.aograph.chuan_air.AirlinePredict;
import com.aograph.drools.DroolsApplication;
import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.InputStream;

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
 * @create 2021/12/10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsApplication.class)
public class TestRules {

    @Autowired
    private KieBase kieBase;

    @Test
    public void testRule0(){
        KieSession kieSession = kieBase.newKieSession();
        AirlinePredict ap=new AirlinePredict();
        ap.setStd_price(2000.00);
        ap.setLabel(1500.00);
        ap.setPredict_price(200.00);
        ap.setFlight_type(1);
        ap.setModel_type("ota");
        kieSession.insert(ap);
        kieSession.fireAllRules();
//        System.out.println(ap);
        kieSession.dispose();
    }
}
