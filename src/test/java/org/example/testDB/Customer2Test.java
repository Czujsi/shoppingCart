package org.example.testDB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Customer2Test {
    @Autowired
    Customer2Repository customer2Repository;

    @Test
    void name() {
        Customer customer = new Customer();
        customer.setName("hahahahha");
        customer.setId(new Customer.Id("foo", "bar"));

        customer2Repository.save(customer);
    }
}