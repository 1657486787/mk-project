/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-6 16:45 <br>
 */

package com.suns.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LoadBalance <br>
 * Description: 负载算法 <br>
 * @author mk
 * @Date 2018-11-6 16:45 <br>
 * @version
 */
public abstract class LoadBalance {

    public volatile static List<String> service_list = new ArrayList<>();//产品服务ip+port列表

    public abstract String choseServiceHost();//随机选择一台服务
}
