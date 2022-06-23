package bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double usdToEur;
    private double eurToUsd;
    private double usdToPln;
    private double plnToUsd;
    private double eurToPln;
    private double plnToEur;
    private LocalDate date;

    public ExchangeRate(long id, double usdToEur, double eurToUsd, double usdToPln, double plnToUsd, double eurToPln, double plnToEur, LocalDate date) {
        this.id = id;
        this.usdToEur = usdToEur;
        this.eurToUsd = eurToUsd;
        this.usdToPln = usdToPln;
        this.plnToUsd = plnToUsd;
        this.eurToPln = eurToPln;
        this.plnToEur = plnToEur;
        this.date = date;
    }

    public ExchangeRate() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getUsdToEur() {
        return usdToEur;
    }

    public void setUsdToEur(double usdToEur) {
        this.usdToEur = usdToEur;
    }

    public double getEurToUsd() {
        return eurToUsd;
    }

    public void setEurToUsd(double eurToUsd) {
        this.eurToUsd = eurToUsd;
    }

    public double getUsdToPln() {
        return usdToPln;
    }

    public void setUsdToPln(double usdToPln) {
        this.usdToPln = usdToPln;
    }

    public double getPlnToUsd() {
        return plnToUsd;
    }

    public void setPlnToUsd(double plnToUsd) {
        this.plnToUsd = plnToUsd;
    }

    public double getEurToPln() {
        return eurToPln;
    }

    public void setEurToPln(double eurToPln) {
        this.eurToPln = eurToPln;
    }

    public double getPlnToEur() {
        return plnToEur;
    }

    public void setPlnToEur(double plnToEur) {
        this.plnToEur = plnToEur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
