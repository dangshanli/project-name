package com.companyxxx.projectname.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

/**
 * @author: luzj
 * @date: 2019-01-25
 * @description: redis配置，配置lettuce,可以自由切换到Jedis
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
//        return new JedisConnectionFactory();

    }

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        //替换序列化工具为Jackson2JsonRedisSerializer(json形式)，默认为JdkSerializationRedisSerializer(二进制形式)
        //========================================可省略
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Objects.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setValueSerializer(jsonRedisSerializer);//值序列化工具
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化工具
        //========================================

        return redisTemplate;
    }
}
