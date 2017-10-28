package com.watchShop.entity;

/**
 * Created by Oleksandr Ryzhkov on 28.10.2017.
 */
public class Watch {
    private long id;
    private String title;
    private String manufacturer;
    private boolean isDigital;
    private Status status;

    public Watch() {
    }

    public Watch(String title, String manufacturer, boolean isDigital) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.isDigital = isDigital;
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
}
