package org.springboot.CustomerAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springboot.CustomerAPI.model.Book;
import org.springboot.CustomerAPI.model.Customer;
import org.springboot.CustomerAPI.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value ="/addCustomer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registerCustomer(@RequestBody Customer c) {
		customerRepo.save(c);
		return "Added Customer to DB";
	}

	@GetMapping("/getCustomerById/{Id}")
	public Optional<Customer> getCustomerById(@PathVariable int Id) {
		return customerRepo.findById(Id);
	}
	@GetMapping("/buyBookById/{bookId}/{customerId}")
	public String buyBooksFromStore(@PathVariable int bookId, @PathVariable int customerId) {
		String result = "Book successfully added to Customer";
		HttpHeaders http = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<String>(http);
		ResponseEntity<Book> res = restTemplate.exchange("http://localhost:2022/BookStoreAPI/getBooks/{bookId}",
				HttpMethod.GET, httpEntity, Book.class, bookId);
		if (res != null && res.getBody() !=null) {
			Book b = res.getBody();
			Optional<Customer> customer = customerRepo.findById(customerId);
			if (customer.isEmpty()) {
				result = "Customer Id is not available";
			} else {
				Customer c = addBookToCustomer(customer.get(), b);
				customerRepo.save(c);
			}
		} else {
			result = "Book Id is not available";
		}
		return result;
	}

	private Customer addBookToCustomer(Customer customer, Book b) {
		HashMap<Integer,String> ls = null;
		if (customer.getBookNames() != null) {
			ls = customer.getBookNames();
			ls.put(b.getId(),b.getBookName() + " -> " + b.getAuthorName());
			customer.setBookNames(ls);
		} else {
			ls = new HashMap<Integer,String>();
			ls.put(b.getId(),b.getBookName() + " -> " + b.getAuthorName());
			customer.setBookNames(ls);
		}

		return customer;
	}

	@GetMapping("/getCustomersData")
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}
}
