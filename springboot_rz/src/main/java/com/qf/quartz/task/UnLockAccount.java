package com.qf.quartz.task;

import com.qf.entity.SysUser;
import com.qf.service.SysUserService;
import com.qf.utils.Lg;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component(value = "unLockAccount")
public class UnLockAccount {

    @Resource
    private SysUserService sysUserService;

    public void unLock(){
        Lg.log("解封账户开始");
        List<SysUser> list = sysUserService.findLockAccount();
        for (SysUser user : list) {
            Date date = user.getLockdate();
            Date now = new Date();
            long time = now.getTime() - date.getTime();
            long day = time/(1000*60*60*24);
            if (day>=3){
                Lg.log("准备解封账户！");
                SysUser sysUser = new SysUser();
                sysUser.setUserId(user.getUserId());
                sysUser.setStatus((byte)1);
                sysUserService.unLockAccount(sysUser);
                Lg.log("解封账户成功");
            }else{
                Lg.log("-----未到解封时间");
            }
        }
    }
}
