package com.companyxxx.projectname.repository;

import com.companyxxx.projectname.domain.p.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: luzj
 * @date: 2019-01-25
 * @description: RedisTemplate的演示
 */
@Repository
public class UserDao {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    public void save(User user){
        redisTemplate.opsForValue().set(String.valueOf(user.getId()),user);
    }

    public User getUser(String key){
        return (User) redisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    //存储list
    public void save(List<User> users){
        //对链表name进行累加
        User user =users.stream()
                .reduce((u,item)->{
                    u.setName(u.getName()+"$"+item.getName().substring(0,1));
                    return u;
                }).get();

        redisTemplate.opsForList().leftPush(user.getName(),users);
    }

}
