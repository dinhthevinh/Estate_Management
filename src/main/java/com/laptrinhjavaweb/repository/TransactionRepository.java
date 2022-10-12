package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.repository.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	List<TransactionEntity> findByCustomerId(Long customerId);

}
