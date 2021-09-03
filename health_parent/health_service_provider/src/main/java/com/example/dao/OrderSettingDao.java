package com.example.dao;

import com.example.pojo.OrderSetting;
import com.example.pojo.Setmeal;
import com.github.pagehelper.Page;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {

    void add(OrderSetting orderSetting);

    void editNumberByOrderDate(OrderSetting orderSetting);

    int findCountByOrderDate(Date orderdate);

    List<OrderSetting> getOrderSettingByMonth(Map<String, String> map);
}
