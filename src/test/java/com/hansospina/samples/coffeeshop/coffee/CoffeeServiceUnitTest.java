package com.hansospina.samples.coffeeshop.coffee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:hansospina@gmail.com">Hans Ospina</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeServiceUnitTest {

    private static final String tmpName = "Tinto Campesino";


    @Autowired
    private CoffeeService service;

    @Test
    public void testList() {
        assertEquals(3, service.list().size());
    }

    @Test
    public void testSave() {
        String id = service
                .save(new Coffee(null, CoffeeType.SOFT, CoffeeOrigin.ANTIOQUIA, tmpName))
                .getId();

        assertEquals(tmpName, service.get(id).getName());
    }

    @Test
    public void testGetCoffee() {

        Coffee first = service.list().get(0);
        assertNotNull(first);
        assertEquals(first.getId(), service.get(first.getId()).getId());
    }


    @Test
    public void testUpdate() {
        Coffee first = service.list().get(0);
        assertNotNull(first);
        String newName = "Café Fuerte";
        service.update(first.getId(), new Coffee(first.getId(), first.getType(), first.getOrigin(), newName));
        assertEquals(newName, service.get(first.getId()).getName());
    }

    @Test
    public void testFindByName() {
        Coffee coffee = service.findByName(tmpName).get(0);
        assertNotNull(coffee);
        assertEquals(CoffeeOrigin.ANTIOQUIA, coffee.getOrigin());
    }

    @Test
    public void testDelete() {
        Coffee coffee = service.findByName(tmpName).get(0);
        assertNotNull(coffee);
        Coffee deleted = service.delete(coffee.getId());
        assertEquals(coffee.getId(), deleted.getId());
        assertEquals(coffee.getName(), deleted.getName());
    }


}
