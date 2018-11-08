/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2018-11-8 8:54 <br>
 */

package com.suns.controller;

import com.suns.listener.ElectionMaster;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: IndexController <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 8:54 <br>
 * @version
 */
@RestController
@RequestMapping("/leader")
public class IndexController {


    @RequestMapping("/getServerInfo")
    public Object getServerInfo(HttpServletRequest request){
        String result = ElectionMaster.isSurvival ? "当前服务器["+request.getLocalAddr()+":"+request.getLocalPort()+"]为主节点" : "当前服务器["+request.getLocalAddr()+":"+request.getLocalPort()+"]为从节点";
        System.out.println("getServerInfo:"+result);
        return result;
    }

}
