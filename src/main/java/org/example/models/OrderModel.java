package org.example.models;

import java.util.Date;

public class OrderModel {
    private int id;
    private Date date;
    private double amount;
    private CustomerModel customer;

    private int customer_id;

    public OrderModel(int id, Date date, double amount, CustomerModel customer) {
        if (amount <=0){
            throw new IllegalArgumentException("Montant dois asidi etre positif");
        }
        if (date == null){
            throw new IllegalArgumentException("La date asidi ne dois pas etre nulle");
        }
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customer = customer;
    }
    // Constructeur par défaut requis par Jackson
    public OrderModel() {
    }
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public CustomerModel getCustomer() {
        return customer;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        if (date == null){
            throw new IllegalArgumentException("La date asidi ne dois pas etre nulle");
        }
        this.date = date;
    }
    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        this.amount = amount;
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
        this.customer = new CustomerModel(customer_id, null, null, null); // Crée un objet temporaire
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", customer=" + customer +
                '}';
    }
}
