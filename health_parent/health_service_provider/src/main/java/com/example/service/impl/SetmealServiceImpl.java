package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.constant.RedisConstant;
import com.example.dao.CheckItemDao;
import com.example.dao.SetmealDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.CheckItem;
import com.example.pojo.Setmeal;
import com.example.service.CheckItemService;
import com.example.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查项服务
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealDao setmealDao;

    // 新增套餐信息， 同时关联检查组
    @Override
    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckGroup(setmealId, checkGroupIds);

        String fileName = setmeal.getImg();
        // 将图片名称保存到redis集合
        savePic2Redis(fileName);

    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {

        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        Integer pageSize = queryPageBean.getPageSize();

        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> pages = setmealDao.findByCondition(queryString);
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    // 将图片名称保存到redis
    private void savePic2Redis(String pic) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
    }

    // 设置检查组和套餐的关系
    public void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkGroupIds) {
        if(checkGroupIds != null && checkGroupIds.length > 0) {
            for (Integer checkGroupId : checkGroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmealId", setmealId);
                map.put("checkGroupId", checkGroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }


}
