package com.nishat.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishat.bankingapp.dto.AccountDto;
import com.nishat.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
    	return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
	
	
	//Get Account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getaccountByID(@PathVariable Long id){
		return new ResponseEntity<>(accountService.getAccountByID(id), HttpStatus.OK);
	}

	//Deposit Amount REST API
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> amountDeposite(@PathVariable Long id, @RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto totalAmount = accountService.amountDeposit(id, amount);
		return ResponseEntity.ok(totalAmount);
	}
	
	//Withdraw Amount REST API
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdrawAmount(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	//Get All Accounts REST API
	@GetMapping() 
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	
	//Delete Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccountByID(@PathVariable Long id){
		accountService.deleteAccountByID(id);
		return ResponseEntity.ok("Account is Deleted successfully!");
	}
	
	
	
}
