package com.ensah.billingservie.models;

import lombok.Data;

@Data
public class Product {
	private Long id;

	private String name;

	private double price;

	private int quantity;
}
