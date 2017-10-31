package com.watchShop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Oleksandr Ryzhkov on 28.10.2017.
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"title"})})
public class Watch {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String manufacturer;
    @NotNull
    private boolean isDigital;
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @NotNull
    private double price;

    public Watch() {
    }

    public Watch(String title, String manufacturer, boolean isDigital, Status status) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.isDigital = isDigital;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isDigital() {
        return isDigital;
    }

    public void setDigital(boolean digital) {
        isDigital = digital;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
