/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.service <br>
 *
 * @author mk <br>
 * Date:2018-11-8 10:12 <br>
 */

package com.suns.service;

import com.suns.dao.IUserDao;
import com.suns.module.UserDo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserServiceImpl <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 10:12 <br>
 * @version
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IUserDao userDao;

    @Override
    public String sayHello() {
        return "hello suns";
    }

    @Override
    public boolean register(String userName, String pwd) {
        UserDo user = new UserDo();
        user.setUserName(userName);
        user.setPwd(pwd);
        return userDao.addUser(user) > 0;
    }

    @Override
    public boolean login(String userName, String pwd) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName",userName);
        paramMap.put("pwd",pwd);
        List list = userDao.selectUser(paramMap);
        boolean result = null != list && list.size() > 0;
        return result;
    }
}
