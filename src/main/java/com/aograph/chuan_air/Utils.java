package com.aograph.chuan_air;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;

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
public class Utils {

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
    /**
     * 获取航班最低价
     * @param discountCabinList
     * @param discount_cabin_extend
     * @return
     */
    public static Double getLowerPrice(List<DiscountCabin> discountCabinList,List<RmDiscountCabin> discount_cabin_extend,AirlinePredict ap){
        DiscountCabin cabin1 = discountCabinList.stream()
                .filter(dc ->dc.getDeparture_3code().equals(ap.getDep()))//过滤起始地
                .filter(dc ->dc.getArrival_3code().equals(ap.getArr()))
                .sorted((e1, e2) -> {
                    return e1.getOw_price().compareTo(e2.getOw_price());
                }).findFirst().orElse(null);
        RmDiscountCabin cabin2=discount_cabin_extend.stream()
                .filter(dc ->dc.getDeparture_3code().equals(ap.getDep()))//过滤起始地
                .filter(dc ->dc.getArrival_3code().equals(ap.getArr()))
                .filter(dc ->(dc.getFlight_date_start().before(ap.getFlight_date())) ||  dc.getFlight_date_start().getTime()==ap.getFlight_date().getTime())
                .filter(dc ->(dc.getFlight_date_end().after(ap.getFlight_date())) || dc.getFlight_date_end().getTime()==ap.getFlight_date().getTime())
                .sorted((e1, e2) -> {
                    return e1.getOw_price().compareTo(e2.getOw_price());
                }).findFirst().orElse(null);

        
        if (null==cabin1 && null==cabin2){
            return ap.getStd_price()*0.3;
        }

        if (null!=cabin1 && null!=cabin2){
            double[] arrs= {cabin1.getOw_price(),cabin2.getOw_price()};
            return Arrays.stream(arrs).min().getAsDouble();
        }

        if (null!=cabin1){
            return cabin1.getOw_price();
        }
        if (null!=cabin2){
            return cabin2.getOw_price();
        }
        return ap.getStd_price()*0.3;
    }


}
