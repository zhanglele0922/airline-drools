package com.aograph.chuan_air;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
 * @create 2021/12/29
 **/
@Setter
@Getter
public class RmDiscountCabin implements Serializable {
    private Integer id;
    private String departure_3code;
    private String arrival_3code;
    private String airline_2code;
    private String cabin;
    private Double ow_price;
    private Date flight_date_start;
    private Date flight_date_end;
    private String discount;
    private String sbyjdm;
    private String sjyjdm;
    private String sy_object;
    private String jsfs;


    @Override
    public String toString() {
        return "RmDiscountCabin{" +
                "id=" + id +
                ", departure_3code='" + departure_3code + '\'' +
                ", arrival_3code='" + arrival_3code + '\'' +
                ", airline_2code='" + airline_2code + '\'' +
                ", cabin='" + cabin + '\'' +
                ", ow_price=" + ow_price +
                ", flight_date_start=" + flight_date_start +
                ", flight_date_end=" + flight_date_end +
                ", discount='" + discount + '\'' +
                ", sbyjdm='" + sbyjdm + '\'' +
                ", sjyjdm='" + sjyjdm + '\'' +
                ", sy_object='" + sy_object + '\'' +
                ", jsfs='" + jsfs + '\'' +
                '}';
    }
}
