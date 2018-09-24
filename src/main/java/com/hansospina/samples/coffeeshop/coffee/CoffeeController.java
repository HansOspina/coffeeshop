package com.hansospina.samples.coffeeshop.coffee;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
@RestController
@RequestMapping("api/coffee/v1")
public class CoffeeController {

    private final CoffeeService service;

    public CoffeeController(CoffeeService service) {
        this.service = service;
    }


    @GetMapping(value = "/")
    public List<Coffee> list() {
        return service.list();
    }

    @GetMapping(value = "{id}")
    public Coffee getCoffee(@PathVariable String id) {
        return service.get(id);
    }

    @PostMapping(value = "coffee")
    public Coffee save(@RequestBody Coffee user) {
        return service.save(user);
    }

    @PutMapping(value = "{id}")
    public Coffee update(@PathVariable String id, @RequestBody Coffee coffee) {
        return service.update(id, coffee);
    }

    @DeleteMapping(value = "{id}")
    public Coffee delete(@PathVariable String id) {
        return service.delete(id);
    }

    @GetMapping(value = "find/name/{name}")
    public List<Coffee> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping(value = "find/type/{type}")
    public List<Coffee> findByType(@PathVariable String type) {

        // check if the type value matches into the enum
        return Stream.of(CoffeeType.lookup(type))
                .flatMap(o -> o.isPresent() ? service.findByType(o.get()).stream() : Stream.empty())
                .collect(Collectors.toList());

    }

    @GetMapping(value = "find/origin/{origin}")
    public List<Coffee> findByOrigin(@PathVariable String origin) {

        // check if the type value matches into the enum
        return Stream.of(CoffeeOrigin.lookup(origin))
                .flatMap(o -> o.isPresent() ? service.findByOrigin(o.get()).stream() : Stream.empty())
                .collect(Collectors.toList());
    }


}
