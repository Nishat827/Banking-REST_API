package com.nishat.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishat.bankingapp.dto.AccountDto;
import com.nishat.bankingapp.entity.Account;
import com.nishat.bankingapp.mapper.AccountMapper;
import com.nishat.bankingapp.repository.AccountRepository;
import com.nishat.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountByID(Long id) {
             
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto amountDeposit(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
		
        double totalBalance = account.getBalance() + amount;
		account.setBalance(totalBalance);
        Account depositedBalance = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto withdrawAmount(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
        
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double totalAmmount = account.getBalance()- amount;
		account.setBalance(totalAmmount);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
		        .collect(Collectors.toList());
	}

	@Override
	public void deleteAccountByID(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));

		accountRepository.deleteById(id);
	}

}
