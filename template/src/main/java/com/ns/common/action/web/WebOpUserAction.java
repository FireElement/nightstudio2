package com.ns.common.action.web;


import com.ns.common.bean.Token;
import com.ns.common.biz.OpUserBiz;
import com.ns.common.biz.WebTokenBiz;
import com.ns.common.util.constant.PathConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(PathConstant.WEB_PREFIX + "/opUser")
public class WebOpUserAction {
    @Resource
    private OpUserBiz biz;
    @Resource
    private WebTokenBiz webTokenBiz;

    /*@RequestMapping("/getById")
    public Object getById(@RequestParam long id) throws Throwable {
        return biz.getById(id);
    }

    @RequestMapping("/getByName")
    public Object getByName(@RequestParam String name) throws Throwable {
        return biz.getByName(name);
    }*/

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestParam String name, String passwd) throws Throwable {
        return biz.login(name, passwd);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Object logout(@RequestParam String token) throws Throwable {
        Token t = webTokenBiz.get(token);
        biz.logout(t);
        return null;
    }

    /*@RequestMapping("/modifyPasswd")
    public Object modifyPasswd(@RequestParam String name, String passwd) {
        return biz.modifyPasswd(name, passwd);
    }*/
}
