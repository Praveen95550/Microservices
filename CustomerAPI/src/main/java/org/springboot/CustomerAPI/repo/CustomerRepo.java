package org.springboot.CustomerAPI.repo;

import org.springboot.CustomerAPI.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository                                         // bean type, id type
public interface CustomerRepo extends MongoRepository<Customer, Integer> {

}
