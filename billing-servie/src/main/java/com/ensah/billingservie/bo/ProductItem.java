package com.ensah.billingservie.bo;

import com.ensah.billingservie.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor  @AllArgsConstructor  @ToString
public class ProductItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	private double price ;

	private double quantity ;

	private  Long productId ;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Bill bill ;

	@Transient
	private Product product ;
}
