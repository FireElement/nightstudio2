package com.ns.common.action.web;


import com.ns.common.bean.OpUser;
import com.ns.common.biz.OpUserBiz;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/opUser")
public class OpUserAction {
    @Resource
    private OpUserBiz biz;

    @RequestMapping("/getByName")
    public OpUser getByName(@RequestParam String name) {
        return biz.getByName(name);
    }

    @RequestMapping("/getByName1")
    public OpUser getByName1(@RequestParam String name) {
        return biz.getByName1(name);
    }

    @RequestMapping("/modifyPasswd")
    public int modifyPasswd(@RequestParam String name, String passwd) {
        return biz.modifyPasswd(name, passwd);
    }
}
