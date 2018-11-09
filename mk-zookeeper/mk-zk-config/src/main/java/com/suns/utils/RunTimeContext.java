/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.utils <br>
 *
 * @author mk <br>
 * Date:2018-11-8 14:29 <br>
 */

package com.suns.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ClassName: RunTimeContext <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 14:29 <br>
 * @version
 */
@Component
public class RunTimeContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(null == RunTimeContext.applicationContext){
            RunTimeContext.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return RunTimeContext.applicationContext;
    }

    //通过name获取bean
    public static Object getBean(String name){
        try {
            Object bean = getApplicationContext().getBean(name);
            System.out.println(bean);
            return bean;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //通过class获取bean
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name及class获取bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name,clazz);
    }
}
