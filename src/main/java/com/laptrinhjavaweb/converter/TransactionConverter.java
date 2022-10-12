package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.enums.TransactionTypesEnum;
import com.laptrinhjavaweb.model.TransactionDTO;
import com.laptrinhjavaweb.repository.entity.TransactionEntity;

@Component
public class TransactionConverter {

	@Autowired
	private ModelMapper modelMapper;

	public TransactionDTO convertEntityToDTO(TransactionEntity transactionEntity) {
		TransactionDTO result = modelMapper.map(transactionEntity, TransactionDTO.class);
		String transactionvalue = null;
		if (transactionEntity.getCode() != null) {
			transactionvalue = TransactionTypesEnum.valueOf(transactionEntity.getCode()).getTransactionValue();
		}
		result.setTransactionType(transactionvalue);
		return result;
	}

}
