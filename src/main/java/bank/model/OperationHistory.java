package bank.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;


@Entity
public class OperationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category operationType;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account fromAccount;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account toAccount;
    @NotNull(message = "Operation amount can not be 0")
    private double amount;
    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currencyFrom;
    @NotNull
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currencyTo;
    private double commissionUSD;
    private double commissionEUR;
    private double commissionPLN;

    public OperationHistory(long id, Category operationType, Account fromAccount, Account toAccount, double amount, @NotNull Currency currencyFrom, @NotNull Currency currencyTo, double commissionUSD, double commissionEUR, double commissionPLN) {
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

    public Category getOperationType() {
        return operationType;
    }

    public void setOperationType(Category operationType) {
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

    public @NotNull Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(@NotNull Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public @NotNull Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(@NotNull Currency currencyTo) {
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
