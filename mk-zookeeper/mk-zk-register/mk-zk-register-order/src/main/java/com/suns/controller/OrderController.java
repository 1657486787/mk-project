/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.controller <br>
 *
 * @author mk <br>
 * Date:2018-11-6 10:29 <br>
 */

package com.suns.controller;

import com.suns.pojo.Order;
import com.suns.pojo.Product;
import com.suns.utils.LoadBalance;
import com.suns.utils.ZkNativeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * ClassName: OrderController <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-6 10:29 <br>
 * @version
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalance loadBalance;

    @RequestMapping("/getOrder/{id}")
    public Object getOrder(@PathVariable("id") String id){
        String result = loadBalance.choseServiceHost();
        System.out.println("product 随机服务："+result);
        String url ="http://"+result+"/product/getProduct/"+id;
        Product product = restTemplate.getForObject(url, Product.class);
        return new Order(id,"order_"+id,product);
    }
}
