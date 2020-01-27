import ibank.Account;
import ibank.Bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class MyBank implements Bank {

    private Collection<Account> accounts;
    private BigDecimal reserve;

    public MyBank() {
        this.accounts = new ArrayList<>();
        this.reserve = BigDecimal.ZERO;
    }

    @Override
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    @Override
    public BigDecimal getTotalReserves() {
        reserve = accounts.stream().map(Account::getBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
        return reserve;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account openDebitAccount(String s) {
        if (getAccountByHolderName(s) != null) {
            return null;
        } else {
            Account fresh = new MyAccount(s);
            accounts.add(fresh);
            return fresh;
        }
    }

    @Override
    public Account openCreditAccount(String s, BigDecimal bigDecimal) {
        if (getAccountByHolderName(s) != null) {
            return null;
        } else {
            Account fresh = new MyAccount(s, bigDecimal);
            accounts.add(fresh);
            return fresh;
        }
    }

    @Override
    public Account getAccountByHolderName(String s) {
        return accounts.stream()
                .filter(name -> name.getHolderName().equals(s))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Account getAccountByNumber(String s) {
        return accounts.stream()
                .filter(id -> id.getNumber().equals(s))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void closeAccount(Account account) {
        accounts.remove(account);
    }
}
