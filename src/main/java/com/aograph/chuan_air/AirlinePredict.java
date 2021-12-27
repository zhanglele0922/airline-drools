package com.aograph.chuan_air;

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
 * @create 2021/12/13
 **/
public class AirlinePredict implements java.io.Serializable {

    private String skey;
    private Date flight_date;
    private Date insert_date;
    private String comp;
    private String eqt;
    private String fltno;
    private String deptime;
    private String arrtime;
    private String dep;
    private String arr;
    private Integer cap;
    private Integer max;
    private Integer flow_ex_dif;
    private String od;
    private Integer bkd;
    private String forecast_date;
    private Double predict_price;
    private Double org_predict_price;
    private String model_type;
    private Double ota_kzl;
    private Double label;
    private Double org_price_gap;
    private Double price_gap;
    private String compete_fltno;
    private String compete_deptime;
    private Integer ex_dif;
    private Double advise_discount;
    private Integer discountMedianModelPredict;
    private Integer flight_type;
    private Double rule_predict_price;
    private Double compete_lowest_price;
    private Double yesterday_ota_price;
    private Double r1m_ota_min_price;
    private Double std_price;
    private Integer his_1;
    private Integer his_2;
    private Integer his_3;
    private String flow_model_type;
    private Integer advise_seats;
    private Integer org_pre_seat;


    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
        this.flight_date = flight_date;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getEqt() {
        return eqt;
    }

    public void setEqt(String eqt) {
        this.eqt = eqt;
    }

    public String getFltno() {
        return fltno;
    }

    public void setFltno(String fltno) {
        this.fltno = fltno;
    }

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    public String getArrtime() {
        return arrtime;
    }

    public void setArrtime(String arrtime) {
        this.arrtime = arrtime;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getFlow_ex_dif() {
        return flow_ex_dif;
    }

    public void setFlow_ex_dif(Integer flow_ex_dif) {
        this.flow_ex_dif = flow_ex_dif;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public Integer getBkd() {
        return bkd;
    }

    public void setBkd(Integer bkd) {
        this.bkd = bkd;
    }

    public String getForecast_date() {
        return forecast_date;
    }

    public void setForecast_date(String forecast_date) {
        this.forecast_date = forecast_date;
    }

    public Double getPredict_price() {
        return predict_price;
    }

    public void setPredict_price(Double predict_price) {
        this.predict_price = predict_price;
    }

    public Double getOrg_predict_price() {
        return org_predict_price;
    }

    public void setOrg_predict_price(Double org_predict_price) {
        this.org_predict_price = org_predict_price;
    }

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }

    public Double getOta_kzl() {
        return ota_kzl;
    }

    public void setOta_kzl(Double ota_kzl) {
        this.ota_kzl = ota_kzl;
    }

    public Double getLabel() {
        return label;
    }

    public void setLabel(Double label) {
        this.label = label;
    }

    public Double getOrg_price_gap() {
        return org_price_gap;
    }

    public void setOrg_price_gap(Double org_price_gap) {
        this.org_price_gap = org_price_gap;
    }

    public Double getPrice_gap() {
        return price_gap;
    }

    public void setPrice_gap(Double price_gap) {
        this.price_gap = price_gap;
    }

    public String getCompete_fltno() {
        return compete_fltno;
    }

    public void setCompete_fltno(String compete_fltno) {
        this.compete_fltno = compete_fltno;
    }

    public String getCompete_deptime() {
        return compete_deptime;
    }

    public void setCompete_deptime(String compete_deptime) {
        this.compete_deptime = compete_deptime;
    }

    public Integer getEx_dif() {
        return ex_dif;
    }

    public void setEx_dif(Integer ex_dif) {
        this.ex_dif = ex_dif;
    }

    public Double getAdvise_discount() {
        return advise_discount;
    }

    public void setAdvise_discount(Double advise_discount) {
        this.advise_discount = advise_discount;
    }

    public Integer getDiscountMedianModelPredict() {
        return discountMedianModelPredict;
    }

    public void setDiscountMedianModelPredict(Integer discountMedianModelPredict) {
        this.discountMedianModelPredict = discountMedianModelPredict;
    }

    public Integer getFlight_type() {
        return flight_type;
    }

    public void setFlight_type(Integer flight_type) {
        this.flight_type = flight_type;
    }

    public Double getRule_predict_price() {
        return rule_predict_price;
    }

    public void setRule_predict_price(Double rule_predict_price) {
        this.rule_predict_price = rule_predict_price;
    }

    public Double getCompete_lowest_price() {
        return compete_lowest_price;
    }

    public void setCompete_lowest_price(Double compete_lowest_price) {
        this.compete_lowest_price = compete_lowest_price;
    }

    public Double getYesterday_ota_price() {
        return yesterday_ota_price;
    }

    public void setYesterday_ota_price(Double yesterday_ota_price) {
        this.yesterday_ota_price = yesterday_ota_price;
    }

    public Double getR1m_ota_min_price() {
        return r1m_ota_min_price;
    }

    public void setR1m_ota_min_price(Double r1m_ota_min_price) {
        this.r1m_ota_min_price = r1m_ota_min_price;
    }

    public Double getStd_price() {
        return std_price;
    }

    public void setStd_price(Double std_price) {
        this.std_price = std_price;
    }

    public Integer getHis_1() {
        return his_1;
    }

    public void setHis_1(Integer his_1) {
        this.his_1 = his_1;
    }

    public Integer getHis_2() {
        return his_2;
    }

    public void setHis_2(Integer his_2) {
        this.his_2 = his_2;
    }

    public Integer getHis_3() {
        return his_3;
    }

    public void setHis_3(Integer his_3) {
        this.his_3 = his_3;
    }

    public String getFlow_model_type() {
        return flow_model_type;
    }

    public void setFlow_model_type(String flow_model_type) {
        this.flow_model_type = flow_model_type;
    }

    public Integer getAdvise_seats() {
        return advise_seats;
    }

    public void setAdvise_seats(Integer advise_seats) {
        this.advise_seats = advise_seats;
    }

    public Integer getOrg_pre_seat() {
        return org_pre_seat;
    }

    public void setOrg_pre_seat(Integer org_pre_seat) {
        this.org_pre_seat = org_pre_seat;
    }


    @Override
    public String toString() {
        return "AirlinePredict{" +
                "skey='" + skey + '\'' +
                ", flight_date=" + flight_date +
                ", insert_date=" + insert_date +
                ", comp='" + comp + '\'' +
                ", eqt='" + eqt + '\'' +
                ", fltno='" + fltno + '\'' +
                ", deptime='" + deptime + '\'' +
                ", arrtime='" + arrtime + '\'' +
                ", dep='" + dep + '\'' +
                ", arr='" + arr + '\'' +
                ", cap=" + cap +
                ", max=" + max +
                ", flow_ex_dif=" + flow_ex_dif +
                ", od='" + od + '\'' +
                ", bkd=" + bkd +
                ", forecast_date='" + forecast_date + '\'' +
                ", predict_price=" + predict_price +
                ", org_predict_price=" + org_predict_price +
                ", model_type='" + model_type + '\'' +
                ", ota_kzl=" + ota_kzl +
                ", label=" + label +
                ", org_price_gap=" + org_price_gap +
                ", price_gap=" + price_gap +
                ", compete_fltno='" + compete_fltno + '\'' +
                ", compete_deptime='" + compete_deptime + '\'' +
                ", ex_dif=" + ex_dif +
                ", advise_discount=" + advise_discount +
                ", discountMedianModelPredict=" + discountMedianModelPredict +
                ", flight_type=" + flight_type +
                ", rule_predict_price=" + rule_predict_price +
                ", compete_lowest_price=" + compete_lowest_price +
                ", yesterday_ota_price=" + yesterday_ota_price +
                ", r1m_ota_min_price=" + r1m_ota_min_price +
                ", std_price=" + std_price +
                ", his_1=" + his_1 +
                ", his_2=" + his_2 +
                ", his_3=" + his_3 +
                ", flow_model_type='" + flow_model_type + '\'' +
                ", advise_seats=" + advise_seats +
                ", org_pre_seat=" + org_pre_seat +
                '}';
    }
}
