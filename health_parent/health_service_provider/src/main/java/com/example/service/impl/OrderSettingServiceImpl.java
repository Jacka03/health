package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.OrderSettingDao;
import com.example.pojo.OrderSetting;
import com.example.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public List<Map> getOrderSettingByMonth(String data) {
        String begin = data + "-1";
        String end = data + "-31";
        Map<String, String> map = new HashMap<>();
        map.put("begin", begin);
        map.put("end", end);

        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();

        if(list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                Map<String, Object> m = new HashMap<>();
                m.put("date", orderSetting.getOrderDate().getDate());  // 获取日期中的天数
                m.put("number", orderSetting.getNumber());
                m.put("reservations", orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public void add(List<OrderSetting> data) {
        if(data != null && data.size() > 0) {
            for (OrderSetting orderSetting : data) {
                int countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(countByOrderDate > 0) {
                    // 已经存在预约设置记录了，执行更行操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    // 还没有存在预约设置
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        // 查询该日期是否已经设置了
        Date orderDate = orderSetting.getOrderDate();

        int countByOrderDate = orderSettingDao.findCountByOrderDate(orderDate);
        if(countByOrderDate > 0) {
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            orderSettingDao.add(orderSetting);
        }


    }
}
