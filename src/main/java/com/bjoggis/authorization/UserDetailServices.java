package com.bjoggis.authorization;

import com.bjoggis.authorization.entity.Account;
import com.bjoggis.authorization.repository.AccountRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServices implements UserDetailsService {

  private final Logger logger = LoggerFactory.getLogger(UserDetailServices.class);
  private final AccountRepository accountRepository;

  public UserDetailServices(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    logger.info("User found: {}", account.getUsername());
    return new AccountPrincipal(account);
  }

  public static class AccountPrincipal implements UserDetails {

    private final Account account;

    public AccountPrincipal(Account account) {
      this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return new ArrayList<>();
    }

    @Override
    public String getPassword() {
      return account.getPassword();
    }

    @Override
    public String getUsername() {
      return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }
}

