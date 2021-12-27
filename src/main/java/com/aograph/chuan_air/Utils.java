package com.aograph.chuan_air;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
    public static Double getLowerPrice(List<DiscountCabin> discountCabinList,List discount_cabin_extend,AirlinePredict ap,String predictDay){
        Date predictDate=getFormatDay(predictDay);
        DiscountCabin value1 = discountCabinList.stream()
                .filter(dc ->dc.getDeparture_3code().equals(ap.getDep()))//过滤起始地
                .filter(dc ->dc.getArrival_3code().equals(ap.getArr()))
                .filter(dc ->dc.getStart_date().before(predictDate))
                .sorted((e1, e2) -> {
                    return e1.getOw_price().compareTo(e2.getOw_price());
                }).findFirst().orElse(null);
        discountCabinList.stream()
                .filter(dc ->dc.getDeparture_3code().equals(ap.getDep()))//过滤起始地
                .filter(dc ->dc.getArrival_3code().equals(ap.getArr()))
                .filter(dc ->dc.getStart_date().before(predictDate))
                .sorted((e1, e2) -> {
                    return e1.getOw_price().compareTo(e2.getOw_price());
                }).forEach(System.out::println);

        
        if (null!=value1){
            return value1.getOw_price();
        }
        return ap.getStd_price()*0.3;
    }

//    ##获取当前折扣表最低价
//    def getLowerPrice(dep,arr,flightDate,predictDay,defaultPrice):
//    df1 = discount_cabin.loc[(discount_cabin.departure_3code==dep) & (discount_cabin.arrival_3code==arr) & (discount_cabin.start_date <= predictDay)]
//    df2 = discount_cabin_extend.loc[ (discount_cabin_extend.departure_3code==dep) & (discount_cabin_extend.arrival_3code==arr) &  \
//            (discount_cabin_extend.flight_date_start <= flightDate.strftime(dayFormat)) \
//            & (discount_cabin_extend.flight_date_end >= flightDate.strftime(dayFormat))]
//    df=None
//    if len(df2) >0:
//    df = pd.merge(df1[['cabin','ow_price']],df2[['cabin','ow_price']],how='outer')
//            else:
//    df =df1
//            df = df.sort_values(by=['ow_price'], ascending=False).reset_index(drop=True)
//    if len(df) > 0:
//            return df.tail(1)['ow_price'].values[0]
//            return defaultPrice


}
