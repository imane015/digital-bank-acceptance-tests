package co.wedevx.digitalbank.automation.ui.models;

public class SavingsAccountInfo {

    private String savingsAccountType;
    private String savingsAccountOwnership;
    private String accountName;
    private String initialDepositAmount;

    public SavingsAccountInfo(String savingsAccountType, String savingsAccountOwnership, String accountName, String initialDepositAmount) {
        this.savingsAccountType = savingsAccountType;
        this.savingsAccountOwnership = savingsAccountOwnership;
        this.accountName = accountName;
        this.initialDepositAmount = initialDepositAmount;
    }

    public String getSavingsAccountType() {
        return savingsAccountType;
    }

    public void setSavingsAccountType(String savingsAccountType) {
        this.savingsAccountType = savingsAccountType;
    }

    public String getSavingsAccountOwnership() {
        return savingsAccountOwnership;
    }

    public void setSavingsAccountOwnership(String savingsAccountOwnership) {
        this.savingsAccountOwnership = savingsAccountOwnership;
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
