package com.aograph.chuan_air;

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
 * @create 2021/12/16
 **/
public class DiscountCabin {
    private String departure_3code;
    private String arrival_3code;
    private Date start_date;
    private Date flight_date;
    private Double ow_price;
    private Double discount;
    private String cabin;

    public String getDeparture_3code() {
        return departure_3code;
    }

    public void setDeparture_3code(String departure_3code) {
        this.departure_3code = departure_3code;
    }

    public String getArrival_3code() {
        return arrival_3code;
    }

    public void setArrival_3code(String arrival_3code) {
        this.arrival_3code = arrival_3code;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
        this.flight_date = flight_date;
    }

    public Double getOw_price() {
        return ow_price;
    }

    public void setOw_price(Double ow_price) {
        this.ow_price = ow_price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    @Override
    public String toString() {
        return "DiscountCabin{" +
                "departure_3code='" + departure_3code + '\'' +
                ", arrival_3code='" + arrival_3code + '\'' +
                ", start_date=" + start_date +
                ", flight_date=" + flight_date +
                ", ow_price=" + ow_price +
                ", discount=" + discount +
                ", cabin='" + cabin + '\'' +
                '}';
    }
}
