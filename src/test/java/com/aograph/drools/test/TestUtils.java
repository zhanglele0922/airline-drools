package com.aograph.drools.test;

import com.aograph.chuan_air.AirlinePredict;
import com.aograph.chuan_air.DiscountCabin;
import com.aograph.chuan_air.Utils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @create 2021/12/16
 **/
public class TestUtils {

    public static Date getFormatDay(String day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }


    @Test
    public void testgetLowerPrice() throws  Exception{
        List<DiscountCabin> list=new ArrayList<>();
        DiscountCabin discountCabin=new DiscountCabin();
        discountCabin.setStart_date(getFormatDay("2021-12-15"));
        discountCabin.setOw_price(200.00);
        discountCabin.setDeparture_3code("A");
        discountCabin.setArrival_3code("B");
        list.add(discountCabin);

        DiscountCabin discountCabin1=new DiscountCabin();
        discountCabin1.setStart_date(getFormatDay("2021-12-15"));
        discountCabin1.setOw_price(500.00);
        discountCabin1.setDeparture_3code("A");
        discountCabin1.setArrival_3code("B");
        list.add(discountCabin1);

        DiscountCabin discountCabin2=new DiscountCabin();
        discountCabin2.setStart_date(getFormatDay("2021-12-15"));
        discountCabin2.setOw_price(400.00);
        discountCabin2.setDeparture_3code("A");
        discountCabin2.setArrival_3code("B");
        list.add(discountCabin);

        AirlinePredict ap=new AirlinePredict();
        ap.setDep("A");
        ap.setArr("B");

        Utils.getLowerPrice(list,null,ap,"2021-12-16");
    }
}
