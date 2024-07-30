package com.nishat.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishat.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
