package com.bms;

public class SQL {
    public String user, manager, manager_phone_number, worker, worker_phone_number, business, business_owner;
    public String particular, purchase, particular_purchase, folio, expenditure, liability, asset;
    public String sale, particular_sale;

    public SQL() {
        this.user = "CREATE TABLE user(user_id char(10), password varchar(20) NOT NULL, user_role" +
                " varchar(10), PRIMARY KEY (user_id));";
        this.manager="CREATE TABLE manager(manager_id char(10), first_name varchar(20) NOT " +
            "NULL, middle_name varchar(20), last_name varchar(20) NOT NULL, email varchar(20), PRIMARY KEY (manager_id)" +
                ")";
        this.manager_phone_number="CREATE TABLE manager_phone_number(manager varchar(10) " +
                "REFERENCES manager(manager_id), phone_number bigint, PRIMARY KEY (manager, " +
                "phone_number), CHECK (phone_number>10000));";
        this.worker = "CREATE TABLE worker(worker_id char(10), first_name varchar(20) NOT " +
                "NULL, middle_name varchar(20), last_name varchar(20) NOT NULL, manager_id char(10) REFERENCES manager" +
                "(manager_id), PRIMARY KEY(worker_id));";
        this.worker_phone_number = "CREATE TABLE worker_phone_number(worker char(20) " +
            "REFERENCES worker(worker_id), phone_number bigint, PRIMARY KEY (worker, " +
                "phone_number), CHECK (phone_number>10000));";
        this.business = "CREATE TABLE business(business_id char(10), category varchar(20), " +
            "location varchar(20), PRIMARY KEY (business_id));";
        this.business_owner="CREATE TABLE business_owner(business char(10) REFERENCES " +
                "business(business_id), manager " +
                "char(10) REFERENCES manager(manager_id), PRIMARY KEY (business, manager));";
        this.particular="CREATE TABLE particular(particular_id char(10), description " +
                "varchar(255), unit_price int, PRIMARY KEY(particular_id), CHECK (unit_price>0));";
        this.purchase= "CREATE TABLE purchase(particular_id char(10) REFERENCES particular" +
            "(particular_id), " +
                "quantity int, amount int, business_id char(10) REFERENCES business(business_id), PRIMARY" +
                " KEY(particular_id), CHECK(quantity>0), CHECK(amount>0));";
        this.particular_purchase="CREATE TABLE particular_purchase(particular_id char(10)" +
                " REFERENCES particular" +
                "(particular_id), date date, time time, PRIMARY KEY(particular_id, date));";
        this.folio="CREATE TABLE folio(particular_id char(10) REFERENCES particular" +
            "(particular-id), folio int, PRIMARY KEY(particular_id, folio), INDEXED(folio), CHECK" +
                "(folio>0));";
        this.expenditure="CREATE TABLE expenditure(date date, time time, particular char" +
                "(10) REFERENCES particular" +
                "(particular_id), amount int, business_id char(10) REFERENCES business(business_id), " +
                "PRIMARY KEY(date, time, particular), CHECK (amount>0));";
        this.liability="CREATE TABLE liability(liability_id char(10), name varchar(20) NOT NULL, " +
                "date date, amount bigint, business char(10) REFERENCES business(business_id), " +
                "PRIMARY KEY(liability_id), CHECK (amount>0));";
        this.asset="CREATE TABLE asset(asset_id char(10), date date, name varchar(20) NOT NULL," +
            " price bigint, business_id char(10) REFERENCES business(business_id), PRIMARY KEY (asset_id), " +
                "CHECK(price>0));";
        this.sale="CREATE TABLE sale(particular_id char(10) REFERENCES particular" +
            "(particular_id), folio int" +
                " REFERENCES folio(folio), amount int, worker char(10) REFERENCES worker(worker_id), " +
                "PRIMARY KEY (particular_id), CHECK (amount>0));";
        this.particular_sale="CREATE TABLE particular_sale (particular char(10) REFERENCES " +
                "particular(particular_id), date date, time time, PRIMARY KEY(particular));";
    }
}
