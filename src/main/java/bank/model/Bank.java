package bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double profitUSD;
    private double profitEUR;
    private double profitPLN;

    public Bank(long id, double profitUSD, double profitEUR, double profitPLN) {
        this.id = id;
        this.profitUSD = profitUSD;
        this.profitEUR = profitEUR;
        this.profitPLN = profitPLN;
    }

    public Bank() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getProfitUSD() {
        return profitUSD;
    }

    public void setProfitUSD(double profitUSD) {
        this.profitUSD = profitUSD;
    }

    public double getProfitEUR() {
        return profitEUR;
    }

    public void setProfitEUR(double profitEUR) {
        this.profitEUR = profitEUR;
    }

    public double getProfitPLN() {
        return profitPLN;
    }

    public void setProfitPLN(double profitPLN) {
        this.profitPLN = profitPLN;
    }
}
