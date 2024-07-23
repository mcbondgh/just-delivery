package com.app.repository;

import com.app.data.DriverData;

import java.util.List;

public interface DriversRepository {
    List<DriverData> getAllDrivers();
    int getDriverIdByName(String name);
    String getDriverNameById(int id);
}
