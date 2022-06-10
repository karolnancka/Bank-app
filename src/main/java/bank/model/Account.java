package bank.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long number;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private double balanceUSD;
    private double balanceEUR;
    private double balancePLN;

    public Account(long id, long number, User user, double balanceUSD, double balanceEUR, double balancePLN) {
        this.id = id;
        this.number = number;
        this.user = user;
        this.balanceUSD = balanceUSD;
        this.balanceEUR = balanceEUR;
        this.balancePLN = balancePLN;
    }

    public Account(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalanceUSD() {
        return balanceUSD;
    }

    public void setBalanceUSD(double balanceUSD) {
        this.balanceUSD = balanceUSD;
    }

    public double getBalanceEUR() {
        return balanceEUR;
    }

    public void setBalanceEUR(double balanceEUR) {
        this.balanceEUR = balanceEUR;
    }

    public double getBalancePLN() {
        return balancePLN;
    }

    public void setBalancePLN(double balancePLN) {
        this.balancePLN = balancePLN;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", user=" + user +
                ", balanceUSD=" + balanceUSD +
                ", balanceEUR=" + balanceEUR +
                ", balancePLN=" + balancePLN +
                '}';
    }

}
