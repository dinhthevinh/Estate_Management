package com.laptrinhjavaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.TransactionDTO;
import com.laptrinhjavaweb.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionAPI {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public TransactionDTO insertBuilding(@RequestBody TransactionDTO transactionDTO) {
		transactionService.save(transactionDTO);
		return transactionDTO;
	}

}
