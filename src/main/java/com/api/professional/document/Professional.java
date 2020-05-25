package com.api.professional.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Professional {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Double salary;
    private String allocatedTo;

    public Professional() {

    }

    public Professional(String id, String name, String email, String phone, Double salary, String allocatedTo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.allocatedTo = allocatedTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(String allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    @Override
    public String toString() {
        return "Professional{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", allocatedTo='" + allocatedTo + '\'' +
                '}';
    }
}
