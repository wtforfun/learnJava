package org.springproject.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Contact;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springproject.swagger.entity.User;

@Controller
@RequestMapping("/home")
@Api(value = "/indexController", description = "接口开放示例")
public class IndexController {

    @RequestMapping("/user")
    @ResponseBody
    public String index(@RequestBody User user){
        System.out.println(user.getName());
        System.out.println(user.getAge());

        return user.getName();
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "返回id", notes = "返回用户实体对象note")
    public String test(@RequestParam String id){
        return id;
    }

    @RequestMapping(value = "/getName/{name}",method = RequestMethod.GET)
    @ResponseBody
    public String getName(@ApiParam(name = "name", value = "名字", required = true)@PathVariable String name){
        System.out.println(name);
        return "www";
    }
}
