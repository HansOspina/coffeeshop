package com.hansospina.samples.coffeeshop.coffee;

import java.util.List;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
public interface CoffeeService {
    List<Coffee> list();

    Coffee get(String id);

    Coffee save(Coffee user);

    Coffee update(String id, Coffee user);

    Coffee delete(String id);

    List<Coffee> findByName(String name);

    List<Coffee> findByType(CoffeeType type);

    List<Coffee> findByOrigin(CoffeeOrigin origin);
}
