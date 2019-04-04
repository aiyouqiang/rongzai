package com.qf.controller;

import com.qf.utils.R;
import com.qf.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogoutController {
//
//    @RequestMapping("/logout")
//    @ResponseBody
//    public R logout(){
//        return R.ok();
//    }


    @RequestMapping("logout")
    @ResponseBody
    public R logout(){
        ShiroUtils.logout();

        return R.ok();
    }
}
