package com.zsg.address;

import com.orm.SugarRecord;

/**
 * Created by zzc on 2015/8/28.
 */
public class AddressBook extends SugarRecord<AddressBook> {

    String name;
    String phone;

    public AddressBook() {
    }

    public AddressBook(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}