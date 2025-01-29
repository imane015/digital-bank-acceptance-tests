package co.wedevx.digitalbank.automation.ui.models;

public class CheckingAccountInfo {

    private String checkingAccountType;
    private String accountOwnership;
    private String accountName;
    private String initialDepositAmount;

    public CheckingAccountInfo(String checkingAccountType, String accountOwnership,String accountName, String initialDepositAmount) {
        this.checkingAccountType = checkingAccountType;
        this.accountOwnership = accountOwnership;
        this.accountName = accountName;
        this.initialDepositAmount = initialDepositAmount;
    }

    public String getCheckingAccountType() {
        return checkingAccountType;
    }

    public void setCheckingAccountType(String checkingAccountType) {
        this.checkingAccountType = checkingAccountType;
    }

    public String getAccountOwnership() {
        return accountOwnership;
    }

    public void setAccountOwnership(String accountOwnership) {
        this.accountOwnership = accountOwnership;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getInitialDepositAmount() {
        return initialDepositAmount;
    }

    public void setInitialDepositAmount(String initialDepositAmount) {
        this.initialDepositAmount = initialDepositAmount;
    }
}
