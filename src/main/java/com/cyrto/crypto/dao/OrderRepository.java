package com.cyrto.crypto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cyrto.crypto.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	  @Query(value = "SELECT * FROM orders WHERE coin_type = ? and active=true order by order_quantity", nativeQuery = true)
	  List<Orders> findOrdersByCoinType(String coint_type);
}
