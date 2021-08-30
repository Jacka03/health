package com.example.dao;

import com.example.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {
    void add(CheckItem checkItem);
    Page<CheckItem> findByCondition(String queryString);

    void deleteById(Integer id);
    Integer findCountByCheckItemId(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
