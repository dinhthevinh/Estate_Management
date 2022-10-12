package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.TransactionDTO;

public interface TransactionService {
	List<TransactionDTO> findTransactionByCustomerId(Long customerId);

	Map<String, String> getTransactionTypes();
}
