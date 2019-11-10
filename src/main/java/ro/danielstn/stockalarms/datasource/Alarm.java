package ro.danielstn.stockalarms.datasource;

import javax.persistence.*;

@Entity
@Table(name = "alarms")
public class Alarm {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private String stockName;
    private boolean active;
    private double initialValue;
    private double variance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id",
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "user-owner"), nullable = false)
    private User user;

    public String getName() {
        return name;
    }

    public Alarm setName(String name) {
        this.name = name;
        return this;
    }

    public String getStockName() {
        return stockName;
    }

    public Alarm setStockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Alarm setActive(boolean active) {
        this.active = active;
        return this;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public Alarm setInitialValue(double initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    public double getVariance() {
        return variance;
    }

    public Alarm setVariance(double variance) {
        this.variance = variance;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Alarm setUser(User user) {
        this.user = user;
        return this;
    }

    public long getId() {
        return id;
    }

    public Alarm setId(long id) {
        this.id = id;
        return this;
    }
}
