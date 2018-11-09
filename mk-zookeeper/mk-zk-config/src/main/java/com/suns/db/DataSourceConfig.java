/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.db <br>
 *
 * @author mk <br>
 * Date:2018-11-8 10:57 <br>
 */

package com.suns.db;

import com.suns.constant.ZkConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * ClassName: DataSourceConfig <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-8 10:57 <br>
 * @version
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){
        SunsDataSource dataSource = new SunsDataSource();
        dataSource.setUrl(ZkConstant.config_db1);
        dataSource.setUsername("develop");
        dataSource.setPassword("");
        dataSource.setDefaultReadOnly(false);
        return dataSource;
    }

}
