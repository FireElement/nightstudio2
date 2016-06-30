package com.ns.common.action.web;


import com.ns.common.biz.OpUserBiz;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/opUser")
public class OpUserAction {
    @Resource
    private OpUserBiz biz;

    @RequestMapping("/getByName")
    public Object getByName(@RequestParam String name) {
        return biz.getByName(name);
    }

    @RequestMapping("/getByName1")
    public Object getByName1(@RequestParam String name) {
        return biz.getByName1(name);
    }

    @RequestMapping("/getAll1")
    public Object getAll1() {
        return biz.getAll1();
    }

    @RequestMapping("/modifyPasswd")
    public Object modifyPasswd(@RequestParam String name, String passwd) {
        return biz.modifyPasswd(name, passwd);
    }
}
