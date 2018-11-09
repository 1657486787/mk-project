/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2018-11-8 10:30 <br>
 */

package com.suns.controller;

import com.suns.constant.ZkConstant;
import com.suns.db.SunsDataSource;
import com.suns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * ClassName: UserController <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 10:30 <br>
 * @version
 */
@RestController
@RequestMapping("/config")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return userService.sayHello();
    }

    @RequestMapping("/register")
    public String register(String userName,String pwd){
        boolean flag = userService.register(userName, pwd);
        if(flag){
            return "注册成功";
        }
        return "注册失败";
    }

    @RequestMapping("/login")
    public String login(String userName,String pwd){
        boolean flag = userService.login(userName,pwd);
        if(flag){
            return "登陆成功";
        }
        return "登陆失败";
    }

    @Resource
    private DataSource dataSource;

    @RequestMapping("/changeDb/{db}")
    public String changeDb(@PathVariable("db") String db) {
        SunsDataSource dataSource = (SunsDataSource) this.dataSource;
        if("1".equals(db)){
            dataSource.setUrl(ZkConstant.config_db1);
        }else{
            dataSource.setUrl(ZkConstant.config_db2);
        }
        dataSource.changeDataSource();
        return "切换成功";
    }

}
