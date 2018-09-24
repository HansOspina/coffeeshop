package com.hansospina.samples.coffeeshop.coffee;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
public enum CoffeeType {
    SOFT, MILD, STRONG;


    private static Map<String, CoffeeType> mapped = Arrays.stream(values()).collect(Collectors.toMap(
            a -> a.name().toLowerCase(),
            a -> a)
    );

    public static Optional<CoffeeType> lookup(String name) {
        return Optional.ofNullable(mapped.get(name.toLowerCase()));
    }

}
