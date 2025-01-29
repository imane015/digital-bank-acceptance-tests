package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankTransaction;
import co.wedevx.digitalbank.automation.ui.models.CheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.models.SavingsAccountInfo;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {


    @DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownership = entry.get("ownership");
        String accountNumber = entry.get("accountNumber");
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));

        return new AccountCard(accountName, accountType, ownership,
                accountNumber, interestRate, balance);

    }




    @DataTableType
    public BankTransaction transactionEntry(Map<String, String> entry) {
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));

        return new BankTransaction(date, category, description, amount, balance);


    }


    @DataTableType
    public CheckingAccountInfo checkingAccountInfoEntry(Map<String, String> entry) {
        String accountType = entry.get("checkingAccountType");
        String ownership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        String amount = entry.get("initialDepositAmount");


    return new CheckingAccountInfo(accountType,ownership,accountName,amount);
    }

    @DataTableType
    public SavingsAccountInfo savingsAccountInfo(Map<String,String> entry) {
        String savingsAccountType = entry.get("savingsAccountType");
        String ownership = entry.get("savingsAccountOwnership");
        String accountName = entry.get("accountName");
        String amount = entry.get("initialDepositAmount");

        return new SavingsAccountInfo(savingsAccountType,ownership,accountName,amount);
    }
}
