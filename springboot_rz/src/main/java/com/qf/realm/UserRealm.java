package com.qf.realm;

import com.qf.entity.SysUser;
import com.qf.service.SysMenuService;
import com.qf.service.SysRoleService;
import com.qf.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "userRealm")
public class UserRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("------>授权");
        //得到当前登录的用户
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        //根据当前用户id查看角色名
        List<String> roles = sysRoleService.findRolesByUserId(user.getUserId());
        //再查询权限
        List<String> perms = sysMenuService.findPermsByUserId(user.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("----->认证");
        UsernamePasswordToken u = (UsernamePasswordToken) token;
        String uname = u.getUsername();
        String pass = new String (u.getPassword());

        //调用service层方法
        SysUser user = sysUserService.findByUname(uname);
        if (user==null){
            throw new UnknownAccountException("账号未知");
        }
        if (!user.getPassword().equals(pass)){
            throw new IncorrectCredentialsException("密码错误");
        }
        if (user.getStatus()==0){
            throw  new LockedAccountException("账号被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,pass,this.getName());

        return info;

    }
}
