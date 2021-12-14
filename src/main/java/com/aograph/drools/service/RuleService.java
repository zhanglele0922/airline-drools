package com.aograph.drools.service;

import com.aograph.chuan_air.AirlinePredict;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class RuleService {

    @Autowired
    private KieBase kieBase;

    public AirlinePredict rule(){
        KieSession kieSession=kieBase.newKieSession();
        AirlinePredict pp=new AirlinePredict();
        pp.setStd_price(2000.00);
        pp.setLabel(1200.00);
        pp.setPredict_price(1800.00);
        pp.setModel_type("ota");
        kieSession.insert(pp);
        kieSession.fireAllRules();
        kieSession.dispose();
        return pp;
    }
}
