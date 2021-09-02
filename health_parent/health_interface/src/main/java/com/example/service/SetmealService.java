package com.example.service;


import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.Setmeal;

// 服务接口
public interface SetmealService {

    void add(Setmeal setmeal, Integer[] checkGroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);
}
