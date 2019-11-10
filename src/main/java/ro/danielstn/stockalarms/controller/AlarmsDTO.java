package ro.danielstn.stockalarms.controller;

public class AlarmsDTO {
    private long id;
    private String name;
    private String stockName;
    private boolean active;
    private double initialValue;
    private double variance;

    public long getId() {
        return id;
    }

    public AlarmsDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlarmsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getStockName() {
        return stockName;
    }

    public AlarmsDTO setStockName(String stockName) {
        this.stockName = stockName;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AlarmsDTO setActive(boolean active) {
        this.active = active;
        return this;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public AlarmsDTO setInitialValue(double initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    public double getVariance() {
        return variance;
    }

    public AlarmsDTO setVariance(double variance) {
        this.variance = variance;
        return this;
    }
}
