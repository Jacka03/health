package com.example.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dao.CheckGroupDao;
import com.example.entity.PageResult;
import com.example.entity.QueryPageBean;
import com.example.pojo.CheckGroup;
import com.example.pojo.CheckItem;
import com.example.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;
    // 新增检查组, 同事关联检查项
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 新增检查组 t_checkgroup
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();

        // 设置检查组和检查项的关联关系 t_checkgroup_checkitem
        // if(checkitemIds != null && checkitemIds.length > 0) {
        //     for(Integer checkitemId : checkitemIds) {
        //
        //         Map<String, Integer> map = new HashMap<String, Integer>();
        //         map.put("checkGroupId", checkGroupId);
        //         map.put("checkItemId", checkitemId);
        //
        //         // System.out.println(checkGroupId+" "+checkitemId);
        //         checkGroupDao.setCheckGroupAndCheckItem(map);
        //     }
        // }

        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }


    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {


        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);

        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        long total = page.getTotal();
        List<CheckGroup> result = page.getResult();
        return new PageResult(total, result);
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        checkGroupDao.deleteAssociation(id);
        checkGroupDao.deleteById(id);

    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 修改检查组基本信息，t_checkgroup表
        checkGroupDao.edit(checkGroup);

        // 清理检查组关联的检查项， t_checkgroup_checkitem
        checkGroupDao.deleteAssociation(checkGroup.getId());

        // 重新建立关联关系
        Integer checkGroupId = checkGroup.getId();

        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);

    }

    // 建立检查组检查项关联关系
    private void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkItemIds) {
        // 设置检查组和检查项的关联关系 t_checkgroup_checkitem
        if(checkItemIds != null && checkItemIds.length > 0) {
            for(Integer checkitemId : checkItemIds) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkitemId);

                // System.out.println(checkGroupId+" "+checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }


}
