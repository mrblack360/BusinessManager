package com.bms.ui.purchases;

public class Particular {
    public String particular_id;
    public String description;
    public int unit_price;

    public Particular (String particular_id, String description, int unit_price){
        this.particular_id=particular_id;
        this.description=description;
        this.unit_price=unit_price;
    }
}
