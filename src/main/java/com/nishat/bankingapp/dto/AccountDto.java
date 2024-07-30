package com.nishat.bankingapp.dto;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;
	private String accountHolderName;
	private double balance;
	public AccountDto(Long id, String accountHolderName, double balance) {
		super();
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}
	public AccountDto() {
		
	}
}
