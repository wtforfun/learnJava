package org.springproject.swagger.controller2;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping(value = "/getUser1",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "编号",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "name",value = "名字",paramType = "query",dataType = "String")
    })
    @ResponseBody
    public Object getUser1(String id,String name){
        return id+name;
    }

    @RequestMapping(value = "/getUser2",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "编号"),
            @ApiImplicitParam(name = "name",value = "名字")
    })
    @ResponseBody
    public Object getUser2(Map map){
        Set<String> keys = map.keySet();
        for(String key : keys){
            System.out.println(map.get(key));
        }
        return "success";
    }

    @RequestMapping(value = "/getUser3",method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "编号",paramType = "form",dataType = "String"),
            @ApiImplicitParam(name = "name",value = "名字",paramType = "form",dataType = "String")
    })
    @ResponseBody
    public Object getUser3(String id,String name){
        return id+name;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public Object getList(@RequestParam List<Map<String,Object>> list){
        System.out.println(list);
        return "";
    }

}
