package com.example.dao;

import com.example.pojo.CheckItem;
import com.example.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> findByCondition(String queryString);
}
