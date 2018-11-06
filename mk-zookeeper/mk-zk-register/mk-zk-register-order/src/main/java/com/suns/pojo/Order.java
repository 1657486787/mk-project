/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.pojo <br>
 *
 * @author mk <br>
 * Date:2018-11-6 11:35 <br>
 */

package com.suns.pojo;

/**
 * ClassName: Order <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-11-6 11:35 <br>
 * @version
 */
public class Order {

    private String id;
    private String name;
    private Product product;

    public Order(String id, String name, Product product) {
        this.id = id;
        this.name = name;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
