package vttp.csf.server.models;

public class PaymentInfo {

    private int amount; // stripe uses cents so $1 is 100 cents
    private String currency;
    private String receiptEmail;

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getReceiptEmail() {
        return receiptEmail;
    }
    public void setReceiptEmail(String receiptEmail) {
        this.receiptEmail = receiptEmail;
    }
    @Override
    public String toString() {
        return "PaymentInfo [amount=" + amount + ", currency=" + currency + ", receiptEmail=" + receiptEmail + "]";
    }

    
    
}
