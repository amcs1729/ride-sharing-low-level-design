package com.company.manager;

import com.company.exception.DriverAlreadyPresentException;
import com.company.exception.DriverNotFoundException;
import com.company.model.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Driver manager class is used to manage all operations related to driver.
 */
public class DriverManager {

	private Map<Integer, Driver> drivers = new HashMap<>();

	public void createDriver(final Driver driver) {
		if (drivers.containsKey(driver.getId()))
			throw new DriverAlreadyPresentException("Driver with driver id = " + driver.getId() + " already present, try with different Id.");

		drivers.put(driver.getId(), driver);
	}

	public void updateDriverAvailability(final int driverId, boolean newAvailability) {
		if (!drivers.containsKey(driverId))
			throw new DriverNotFoundException("No driver with driver id = " + driverId + ", try with correct driver Id.");

		drivers.get(driverId).setAcceptingRider(newAvailability);
	}

	public List<Driver> getDrivers() {
		return drivers.values()
				.stream()
				.filter(Driver::isAvailable)
				.collect(Collectors.toList());
	}

}
