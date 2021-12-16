package com.aograph.drools.test;

import org.drools.core.io.impl.UrlResource;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.InputStream;
import java.util.ArrayList;
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
 * @create 2021/12/14
 **/
public class TestRemoteRules {

    @Test
    public void testRemoteRules() throws  Exception{
        //远程加载maven仓库中的jar包
        String url="http://172.16.0.16:18080/kie-drools-wb/maven2/com/aograph/chuan_air/1.0.0/chuan_air-1.0.0.jar";
        KieServices kieServices=KieServices.Factory.get();
        UrlResource resource=(UrlResource) kieServices.getResources().newUrlResource(url);
        resource.setUsername("testuser");
        resource.setPassword("testpwd");
        resource.setBasicAuthentication("enabled");
        InputStream inputStream=resource.getInputStream();
        KieRepository repository=kieServices.getRepository();
        KieModule kieModule=repository.addKieModule(kieServices.getResources().newInputStreamResource(inputStream));

        KieContainer kieContainer=kieServices.newKieContainer(kieModule.getReleaseId());

//        Student student=new Student();
//        student.setAge(13);
//        student.setName("zhangsan");
//
        KieSession session=kieContainer.newKieSession();
        List testList=new ArrayList<>();
        testList.add("sdfsdfsdf");
        session.setGlobal("discount_cabin",testList );
//        session.insert(student);
//        session.fireAllRules();
//        session.dispose();
//        System.out.println(student);

    }
}
