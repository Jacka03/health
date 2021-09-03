package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.constant.MessageConstant;
import com.example.entity.Result;
import com.example.pojo.OrderSetting;
import com.example.service.OrderSettingService;
import com.example.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

// 预约设置
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile multipartFile) {
        try {
            List<String[]> list = POIUtils.readExcel(multipartFile);
            List<OrderSetting> data = new ArrayList<>();

            for (String[] strings : list) {
                String orderData = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderData), Integer.parseInt(number));
                data.add(orderSetting);
            }

            // 通过dubbo远程调用服务
            orderSettingService.add(data);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam String date) {

        List<Map> list = null;
        try {
            list = orderSettingService.getOrderSettingByMonth(date);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);

    }
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        return new Result(true, MessageConstant.ORDER_SUCCESS);

    }
}
