package bank.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class OperationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String operationType;
    @OneToOne
    @JoinColumn(name = "from_account")
    private Account fromAccount;
    @OneToOne
    @JoinColumn(name = "to_account")
    private Account toAccount;
    @NotNull(message = "Operation amount can not be 0")
    private double amount;
    @NotNull
    private String currencyFrom;
    @NotNull
    private String currencyTo;
    private double commissionUSD;
    private double commissionEUR;
    private double commissionPLN;

    public OperationHistory(long id, String operationType, Account fromAccount, Account toAccount, double amount, String currencyFrom, String currencyTo, double commissionUSD, double commissionEUR, double commissionPLN) {
        this.id = id;
        this.operationType = operationType;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.commissionUSD = commissionUSD;
        this.commissionEUR = commissionEUR;
        this.commissionPLN = commissionPLN;
    }

    public OperationHistory() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getCommissionUSD() {
        return commissionUSD;
    }

    public void setCommissionUSD(double commissionUSD) {
        this.commissionUSD = commissionUSD;
    }

    public double getCommissionEUR() {
        return commissionEUR;
    }

    public void setCommissionEUR(double commissionEUR) {
        this.commissionEUR = commissionEUR;
    }

    public double getCommissionPLN() {
        return commissionPLN;
    }

    public void setCommissionPLN(double commissionPLN) {
        this.commissionPLN = commissionPLN;
    }

    @Override
    public String toString() {
        return "OperationHistory{" +
                "id=" + id +
                ", operationType='" + operationType + '\'' +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", commissionUSD=" + commissionUSD +
                ", commissionEUR=" + commissionEUR +
                ", commissionPLN=" + commissionPLN +
                '}';
    }
}
