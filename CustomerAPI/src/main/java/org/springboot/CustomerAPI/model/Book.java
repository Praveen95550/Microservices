package org.springboot.CustomerAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

@Document(collection = "Book")
public class Book {
	@Id
	private int id;
	private String bookName;
	private String authorName;
}
