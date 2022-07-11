package com.ensah.billingservie.bo;

import com.ensah.billingservie.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Bill {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	private Date dateBilling  ;

	@OneToMany(mappedBy = "bill")
	private Collection<ProductItem> productItems ;

	private Long customerId ;
	@Transient
	private Customer customer ;
}
