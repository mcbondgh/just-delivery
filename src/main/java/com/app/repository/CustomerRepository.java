package com.app.repository;

import java.util.List;

public interface CustomerRepository {
    public List<Object> getAllCustomers();
    String getCustomerById(int customerId);
}
