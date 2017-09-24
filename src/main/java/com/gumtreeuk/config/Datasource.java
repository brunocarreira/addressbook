package com.gumtreeuk.config;

import java.util.List;

/**
 * A generic datasource structure for Address Book
 *
 * @author Bruno Carreira
 *
 */
public class Datasource<T extends Object> {

    private List<T> addressBook;

    public List<T> getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(List<T> addressBook) {
        this.addressBook = addressBook;
    }
}
