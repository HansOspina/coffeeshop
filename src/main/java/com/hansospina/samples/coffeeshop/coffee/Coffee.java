package com.hansospina.samples.coffeeshop.coffee;


import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
@JsonRootName("Product")
public class Coffee {

    private String id;

    private CoffeeType type;

    private CoffeeOrigin origin;

    private String name;

    private Date created;


    public Coffee() {
    }

    public Coffee(String id, CoffeeType type, CoffeeOrigin origin, String name) {
        this.id = id;
        this.type = type;
        this.origin = origin;
        this.name = name;
        this.created = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public CoffeeOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(CoffeeOrigin origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
