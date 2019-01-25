package com.companyxxx.projectname.controller;

import com.companyxxx.projectname.domain.p.User;
import com.companyxxx.projectname.exceptions.UniException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description:
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    static Map<Long,User> users = Collections.synchronizedMap(new HashMap<>());
    static {
        //初始化数据
        users.put(1L,new User(1L,"name1",11));
        users.put(2L,new User(2L,"name2",33));
    }

    /**
     * swagger2 的演示
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户列表",notes="")
    @ApiImplicitParam(name = "id",value = "user id")
    @GetMapping("/getlist")
    public List<User> getUserList(Long id){
        List<User> r = new ArrayList<>(users.values());
        r.stream()
                .filter((item)-> item.getId() == id)
                .forEach((item)-> System.out.println(item));
        return r;
    }

    /**
     * 统一异常处理演示
     * @return
     * @throws UniException
     */
    @RequestMapping("/json")
    public String json() throws UniException {
        throw  new UniException("统一错误示例!!!");
    }
}
