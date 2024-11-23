/**
 * 
 */
package org.spring.mongo.BookStoreAPI.repository;

import org.spring.mongo.BookStoreAPI.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 *
 */

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

}
