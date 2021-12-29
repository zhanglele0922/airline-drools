package com.aograph.drools.test;

import com.aograph.chuan_air.AirlinePredict;
import com.aograph.chuan_air.PredictLoader;
import com.aograph.DroolsApplication;
import com.aograph.excel.utils.ExcelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    @Autowired
    private KieBase kieBase;

    @Autowired
    private PredictLoader predictLoader;

    @Test
    public void testRule1(){
        KieSession kieSession = kieBase.newKieSession();
        String date=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00"));


        predictLoader.init(kieSession);
        List<AirlinePredict> aps = ExcelUtils.read("/Users/lelezhang/aograph/airline-drools/src/test/java/com/aograph/drools/test/prepare.xlsx", AirlinePredict.class);

        kieSession.setGlobal("predictDay",date);
        for(AirlinePredict ap:aps){
            kieSession.insert(ap);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
