/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2018-11-5 16:59 <br>
 */

package com.suns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-5 16:59 <br>
 * @version
 */
@Controller
//@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/m1.do")
    @ResponseBody
    public String metho1(){
        return "test...";
    }
}
