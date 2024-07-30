package com.nishat.bankingapp.service;
import java.util.List;

import com.nishat.bankingapp.dto.AccountDto;

public interface AccountService {

	public AccountDto createAccount(AccountDto account);
	
	public AccountDto getAccountByID(Long id);
	
	public AccountDto amountDeposit(Long id, double amount);
	
	public AccountDto withdrawAmount(Long id, double amount);

	public List<AccountDto> getAllAccounts();

	public void deleteAccountByID(Long id );

}
