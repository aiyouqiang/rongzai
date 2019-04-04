package com.qf.controller;

import com.google.code.kaptcha.Producer;
import com.qf.dto.SysUserDTO;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;
import com.qf.log.Mylog;
import com.qf.service.SysUserService;
import com.qf.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource  //config包下的KaptchaConfig类中定义
    private Producer producer;

    @RequestMapping("/findAll")
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }


    @RequestMapping("/sys/login")
    public R login(@RequestBody SysUserDTO sysUser){
        //服务端生成验证码
        String code = ShiroUtils.getCaptcha();
        //用户输入的验证码
        String c = sysUser.getCaptcha();
        if (code!=null&&!code.equalsIgnoreCase(c)){
            return R.error("验证码错误");
        }

               String s = null;
        //得到subject
        try {

            Subject subject = SecurityUtils.getSubject();
            String pwd = sysUser.getPassword();
            Md5Hash md5Hash = new Md5Hash(pwd,sysUser.getUsername(),1024);
            pwd = md5Hash.toString();
            //把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(),pwd);

            if (sysUser.isRememberMe()){
                token.setRememberMe(true);
            }

            //登录
            subject.login(token);//自定义Realm的认证方法

            return R.ok();
        } catch (Exception e) {
           s = e.getMessage();
        }
        return R.error(s);

//        return sysUserService.login(sysUser);


    }

    @Mylog(value = "用户列表",description = "分页显示列表")
    @RequiresPermissions("sys:user:list")
    @RequestMapping("/sys/user/list")
    public ResultData findByPage(Pager pager, String search, Sorter sorter){
        return sysUserService.findByPage(pager,search,sorter);
    }

    @RequestMapping("/captcha.jpg")
    public  void captcha(HttpServletResponse response){
        try {
            String text = producer.createText();//生成验证码
            ShiroUtils.setAttribute("code",text);
            BufferedImage bufferedImage = producer.createImage(text);
            OutputStream os = response.getOutputStream();
            //把生成的验证码展示到客户端
            ImageIO.write(bufferedImage,"jpg",os);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Mylog(value = "删除用户",description = "根据用户编号删除菜单")
    @RequiresPermissions("sys:user:delete")
    @RequestMapping("/sys/user/del")
    public R del(@RequestBody List<Long> ids){
        return sysUserService.del(ids);
    }

    @Mylog(value = "查询用户信息",description = "显示分页信息")
    @RequestMapping("/sys/user/info")
    //@RequiresPermissions("sys:user:select")
    public R userInfo(){
        SysUser user = ShiroUtils.getCurrentUser();

        System.out.println(user);
        return R.ok().put("user",user);
    }

    @Mylog(value = "新增用户",description = "新增用户")
    @RequestMapping("/sys/user/save")
    @RequiresPermissions("sys:user:save")
    public R saveUser(@RequestBody SysUser sysUser){
        sysUser.setCreateTime(new Date(System.currentTimeMillis()));
        return sysUserService.save(sysUser);
    }


    @Mylog(value = "查询用户",description = "查询用户")
    @RequiresPermissions("sys:user:select")
    @RequestMapping("/sys/user/info/{userId}")
    public R findUser(@PathVariable long userId){
        return sysUserService.findUser(userId);
    }

    @Mylog(value = "修改用户",description = "根据用户编号修改菜单")
    @RequiresPermissions("sys:user:update")
    @RequestMapping("/sys/user/update")
    public R update(@RequestBody SysUser sysUser){
        sysUser.setCreateTime(new Date(System.currentTimeMillis()));
        return sysUserService.update(sysUser);
    }




}
