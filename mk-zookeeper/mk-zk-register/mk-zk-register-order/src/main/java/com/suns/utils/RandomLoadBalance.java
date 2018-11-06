/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-6 16:48 <br>
 */

package com.suns.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Random;

/**
 * ClassName: RandomLoadBalance <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-6 16:48 <br>
 * @version
 */
@Component
public class RandomLoadBalance extends LoadBalance {

    @Override
    public String choseServiceHost() {
        if(CollectionUtils.isEmpty(service_list)){
            return "";
        }
        return service_list.get(new Random().nextInt(service_list.size()));
    }
}
