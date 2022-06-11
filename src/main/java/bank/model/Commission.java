package bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String commissionName;
    private double commissionRate;

    public Commission(long id, double commissionRate, String commissionName) {
        this.id = id;
        this.commissionName = commissionName;
        this.commissionRate = commissionRate;
    }

    public Commission() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getCommissionName() {
        return commissionName;
    }

    public void setCommissionName(String commissionName) {
        this.commissionName = commissionName;
    }

    @Override
    public String toString() {
        return "Commission{" +
                "id=" + id +
                ", commissionName=" + commissionName +
                ", commissionRate='" + commissionRate + '\'' +
                '}';
    }
}
