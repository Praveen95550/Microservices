package org.springboot.CustomerAPI.model;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Document(collection ="Customer")
public class Customer {

	@Id
	private int id;
	private String customerName;
	private HashMap<Integer,String> bookNames;
}
