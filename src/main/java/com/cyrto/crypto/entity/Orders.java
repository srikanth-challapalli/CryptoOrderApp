
package com.cyrto.crypto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cyrto.crypto.model.CoinType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column
	@JsonInclude(Include.NON_NULL)
	private Long orderId;
	@Setter
	@Getter
	@Column(name="Coin_type")
	@Enumerated(EnumType.STRING)
	private CoinType type;
	@Setter
	@Getter
	@Column(name="Order_quantity")
	private long orderQuantity;
	@Setter
	@Getter
	@Column(name="Price_per_unit")
	private double pricePerUnit;
	@Setter
	@Getter
	@Column(name="Order_type")
	private String orderType;
	@Setter
	@Getter
	@Column
	private String rating;
	@Setter
	@Getter
	@Column(name = "active")
	private String active;
}


