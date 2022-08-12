package com.example.carparkee13;

public class Carpark{

    private String total;
    private String available;
    private String type;

    public Carpark( String total, String available, String type) {
        this.total = total;
        this.available = available;
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public String getAvailable() {
        return available;
    }


    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Carpark{" +
                ", total=" + total +
                ", available=" + available +
                ", type=" + type +
                '}';
    }
}

