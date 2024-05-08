package org.example.testDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Customer2Repository extends JpaRepository<Customer, Customer.Id> {
}
