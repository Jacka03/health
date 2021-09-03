package com.example.service;

import com.example.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author Jacka
 */
public interface OrderSettingService {

    void add(List<OrderSetting> data);

    List<Map> getOrderSettingByMonth(String data);

    void editNumberByDate(OrderSetting orderSetting);

}
