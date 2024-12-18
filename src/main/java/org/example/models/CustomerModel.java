package org.example.models;

public class CustomerModel {
    private int id;
    private String name;
    private String email;
    private String phone;

    public CustomerModel(int id, String name, String email, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Constructeur par défaut requis par Jackson
    public CustomerModel() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email){
        if (email != null){
            this.email = email;
        }else {
            throw new IllegalArgumentException("Email is null");
        }
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
