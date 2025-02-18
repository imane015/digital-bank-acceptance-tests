package co.wedevx.digitalbank.automation.ui.models;

public class BankTransaction {

    private String date;
    private String category;
    private String description;
    private double amount;
    private double balance;

    public BankTransaction(String date, String category, String description, double amount, double balance) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
