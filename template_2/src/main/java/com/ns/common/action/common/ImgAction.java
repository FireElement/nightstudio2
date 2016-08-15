package com.ns.common.action.common;

import com.ns.common.util.constant.PathConstant;
import com.ns.common.util.image.ImgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuezhucao on 16/4/14.
 */
@RestController
@RequestMapping(PathConstant.COMMON_PREFIX + "/img")
public class ImgAction {

    @RequestMapping("/upload")
    public Object upload(@RequestParam String name, String img) throws Throwable {
        ImgUtil.uploadImage(name, img);
        return null;
    }

}
