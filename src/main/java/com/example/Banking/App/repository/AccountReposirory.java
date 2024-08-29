package com.example.Banking.App.repository;

import com.example.Banking.App.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountReposirory extends JpaRepository<Account, Long> {
}
