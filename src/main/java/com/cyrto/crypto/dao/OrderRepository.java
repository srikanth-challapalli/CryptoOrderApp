package com.cyrto.crypto.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyrto.crypto.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
