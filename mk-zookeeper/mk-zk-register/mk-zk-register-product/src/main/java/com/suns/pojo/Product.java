/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.pojo <br>
 *
 * @author mk <br>
 * Date:2018-11-5 16:46 <br>
 */

package com.suns.pojo;

/**
 * ClassName: Product <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-5 16:46 <br>
 * @version
 */
public class Product {

    private String id;
    private String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
