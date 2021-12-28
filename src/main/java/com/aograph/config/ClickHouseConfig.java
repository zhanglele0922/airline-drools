package com.aograph.config;

import com.aograph.utils.SpringContextUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author luchunyang
 * @date 2019-05-26
 *
 */
@Setter
@Getter
@Data
@Component
@ConfigurationProperties(prefix = "spring.clickhouse")
public class ClickHouseConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ClickHouseConfig.class);

    private String address;

    private String username;

    private String password;

    private String db;

    private Integer socketTimeout;


    public static Connection getConn() {
        ClickHouseConfig clickHouseConfig= SpringContextUtil.getBean(ClickHouseConfig.class);
        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(clickHouseConfig.username);
        properties.setPassword(clickHouseConfig.password);
        properties.setDatabase(clickHouseConfig.db);
        properties.setSocketTimeout(clickHouseConfig.socketTimeout);
        String[] url=clickHouseConfig.address.split(",");
        for (int i = 0; i < url.length; i++) {
            ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(url[i], properties);
            try {
                conn = clickHouseDataSource.getConnection();
                return conn;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
    public static List<Map> exeSql(String sql) {
        Connection connection = getConn();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while (results.next()) {
                Map row = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
                }
                list.add(row);
            }
            return list;
        } catch (SQLException e) {
            LOG.error("ExeSql:{}", sql);
            e.printStackTrace();
        }
        return null;
    }



}