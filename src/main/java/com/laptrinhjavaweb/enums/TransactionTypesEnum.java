package com.laptrinhjavaweb.enums;

public enum TransactionTypesEnum {

	CHAM_SOC_KHACH_HANG("CHĂM SÓC KHÁCH HÀNG"), DAN_DI_XEM("DẪN ĐI XEM");

	private final String transactionValue;

	TransactionTypesEnum(String transactionValue) {
		this.transactionValue = transactionValue;
	}

	public String getTransactionValue() {
		return transactionValue;
	}

}
