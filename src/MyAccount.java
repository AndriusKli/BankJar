import ibank.Account;

import java.math.BigDecimal;

public class MyAccount implements Account {
    private static int totalAccounts = 0;
    private String number;
    private String holderName;
    private BigDecimal balance;
    private boolean debit;
    private BigDecimal creditLimit;


    public MyAccount(String holderName) {
        this.number = "" + totalAccounts;
        this.holderName = holderName;
        this.balance = new BigDecimal(0);
        this.debit = true;
        totalAccounts++;
    }

    public MyAccount(String holderName, BigDecimal creditLimit) {
        this.number = "A" + totalAccounts;
        this.holderName = holderName;
        this.balance = BigDecimal.ZERO;
        this.creditLimit = creditLimit;
        this.debit = false;
        totalAccounts++;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getHolderName() {
        return this.holderName;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public boolean deposit(BigDecimal bigDecimal) {
        if (bigDecimal.equals(BigDecimal.ZERO) || BigDecimal.ZERO.compareTo(bigDecimal) > 0) {
            return false;
        } else {
            this.balance = balance.add(bigDecimal);
            return true;
        }
    }

    @Override
    public boolean withdraw(BigDecimal bigDecimal) {
        if (this.debit) {
            if (balance.compareTo(bigDecimal) >= 0) {
                this.balance = balance.subtract(bigDecimal);
                return true;
            } else {
                return false;
            }

        } else {
            if ((balance.add(creditLimit)).compareTo(bigDecimal) > 0) {
                this.balance = balance.subtract(bigDecimal);
                return true;
            } else {
                return false;
            }
        }

    }
}

