package com.hansospina.samples.coffeeshop.coffee;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
public enum CoffeeOrigin {
    ANTIOQUIA, MAGDALENA, TOLIMA, HUILA, QUILLA;


    private static Map<String, CoffeeOrigin> mapped = Arrays.stream(values()).collect(Collectors.toMap(
            a -> a.name().toLowerCase(),
            a -> a)
    );

    public static Optional<CoffeeOrigin> lookup(String name) {
        return Optional.ofNullable(mapped.get(name.toLowerCase()));
    }

}
