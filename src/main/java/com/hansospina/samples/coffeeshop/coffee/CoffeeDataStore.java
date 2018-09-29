package com.hansospina.samples.coffeeshop.coffee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
class CoffeeDataStore {

    private static Map<String, Coffee> dataStore = new HashMap<>();

    static {
        String tmpId = UUID.randomUUID().toString();
        dataStore.put(tmpId, new Coffee(tmpId, CoffeeType.SOFT, CoffeeOrigin.ANTIOQUIA, "Café Antioqueño"));

        tmpId = UUID.randomUUID().toString();
        dataStore.put(tmpId, new Coffee(tmpId, CoffeeType.STRONG, CoffeeOrigin.MAGDALENA, "Café de la Sierra"));

        tmpId = UUID.randomUUID().toString();
        dataStore.put(tmpId, new Coffee(tmpId, CoffeeType.MILD, CoffeeOrigin.TOLIMA, "Café Tolimense"));

        tmpId = UUID.randomUUID().toString();
        dataStore.put(tmpId, new Coffee(tmpId, CoffeeType.STRONG, CoffeeOrigin.MAGDALENA, "Café de la Esquina"));

        tmpId = UUID.randomUUID().toString();
        dataStore.put(tmpId, new Coffee(tmpId, CoffeeType.SOFT, CoffeeOrigin.MAGDALENA, "Café Tinto"));

    }


    static List<Coffee> list() {
        return new ArrayList<>(dataStore.values());
    }

    static Coffee getCoffee(String id) {
        return dataStore.get(id);
    }

    static Coffee save(Coffee coffee) {
        String tmpId = UUID.randomUUID().toString();
        coffee.setId(tmpId);
        dataStore.put(tmpId, coffee);
        return dataStore.get(tmpId);
    }

    static Coffee update(String id, Coffee coffee) {
        dataStore.put(id, coffee);
        return dataStore.get(id);
    }

    static Coffee delete(String id) {
        return dataStore.remove(id);
    }

    static List<Coffee> findByName(String name) {
        return dataStore.values().stream().filter(u -> u.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    static List<Coffee> findByType(CoffeeType type) {
        return dataStore.values().stream().filter(u -> u.getType().equals(type)).collect(Collectors.toList());
    }

    static List<Coffee> findByOrigin(CoffeeOrigin origin) {
        return dataStore.values().stream().filter(u -> u.getOrigin().equals(origin)).collect(Collectors.toList());
    }

}
