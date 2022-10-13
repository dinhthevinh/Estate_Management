package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.enums.TransactionTypesEnum;
import com.laptrinhjavaweb.model.TransactionDTO;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.entity.TransactionEntity;
import com.laptrinhjavaweb.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private TransactionConverter transactionConverter;

	@Override
	public List<TransactionDTO> findTransactionByCustomerId(Long customerId) {
		List<TransactionDTO> results = new ArrayList<>();
		List<TransactionEntity> transactionEntities = transactionRepository.findByCustomerId(customerId);
		for (TransactionEntity item : transactionEntities) {
			TransactionDTO transactionDTO = transactionConverter.convertEntityToDTO(item);
			results.add(transactionDTO);
		}
		return results;
	}

	@Override
	public Map<String, String> getTransactionTypes() {
		Map<String, String> transactionTypes = new HashMap<>();
		for (TransactionTypesEnum item : TransactionTypesEnum.values()) {
			transactionTypes.put(item.toString(), item.getTransactionValue());
		}
		return transactionTypes;
	}

	@Override
	public void save(TransactionDTO transactionDTO) {
		TransactionEntity transactionEntity = transactionConverter.convertDTOToEntity(transactionDTO);
		transactionRepository.save(transactionEntity);
	}

}
