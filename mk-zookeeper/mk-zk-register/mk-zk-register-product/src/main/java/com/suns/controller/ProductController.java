/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2018-11-5 16:47 <br>
 */

package com.suns.controller;

import com.suns.pojo.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: ProductController <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-5 16:47 <br>
 * @version
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/getProduct/{id}")
    public Object get(@PathVariable("id") String id, HttpServletRequest request){
        System.out.println("id="+id);
        return new Product(id,"product_ip_port>>>>>>>>>>>>"+request.getLocalAddr()+":"+request.getLocalPort());
    }

}
