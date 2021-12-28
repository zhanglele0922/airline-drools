package com.aograph.drools.test;

import com.aograph.chuan_air.AirlinePredict;
import com.aograph.drools.DroolsApplication;
import com.aograph.excel.utils.ExcelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
 * @create 2021/12/10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsApplication.class)
public class TestRule1 {

    static {

    }

    @Autowired
    private KieBase kieBase;

    @Test
    public void testRule1(){

        List<AirlinePredict> aps = ExcelUtils.read("/Users/lelezhang/aograph/airline-drools/src/test/java/com/aograph/drools/test/prepare.xlsx", AirlinePredict.class);
        KieSession kieSession = kieBase.newKieSession();

        kieSession.setGlobal("predictDay","2021-12-28");
        for(AirlinePredict ap:aps){
            kieSession.insert(ap);
        }
        kieSession.fireAllRules();
//        System.out.println(ap);
        kieSession.dispose();
    }
}
