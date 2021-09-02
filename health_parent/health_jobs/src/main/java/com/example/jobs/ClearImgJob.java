package com.example.jobs;

import com.example.constant.RedisConstant;
import com.example.utils.AliyunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    // 定时清理垃圾图片
    public void clearImg() {

        // 根据redis中保存的两个set获取垃圾图片的名称
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        if(sdiff != null) {
            for (String picName : sdiff) {
                // 从阿里云删除垃圾图片
                AliyunUtil.deleteFileFromAliyun(picName);
                // 将已经删除的垃圾图片的名称从
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, picName);
            }
        }
    }

}
