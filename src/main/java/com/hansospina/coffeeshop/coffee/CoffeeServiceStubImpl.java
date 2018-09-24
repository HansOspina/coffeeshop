package com.hansospina.coffeeshop.coffee;


import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
@Service
public class CoffeeServiceStubImpl implements CoffeeService {

    @Override
    public List<Coffee> list() {
        return CoffeeDataStore.list();
    }

    @Override
    public Coffee get(String id) {
        return CoffeeDataStore.getCoffee(id);
    }

    @Override
    public Coffee save(Coffee user) {
        return CoffeeDataStore.save(user);
    }

    @Override
    public Coffee update(String id, Coffee user) {
        return CoffeeDataStore.update(id, user);
    }

    @Override
    public Coffee delete(String id) {
        return CoffeeDataStore.delete(id);
    }

    @Override
    public List<Coffee> findByName(String name) {
        return CoffeeDataStore.findByName(name);
    }


    @Override
    public List<Coffee> findByType(CoffeeType type) {
        return CoffeeDataStore.findByType(type);
    }


    @Override
    public List<Coffee> findByOrigin(CoffeeOrigin origin) {
        return CoffeeDataStore.findByOrigin(origin);
    }


}
