/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.service <br>
 * @author mk <br>
 * Date:2018-11-8 10:10 <br>
 */
 
package com.suns.service;
/**
 * Name: UserService <br>
 * Description:  <br>
 * @author mk <br>
 * @Date 2018-11-8 10:10 <br>
 * @version
 */
public interface UserService {

    String sayHello();

    boolean register(String userName,String pwd);

    boolean login(String userName,String pwd);
}
