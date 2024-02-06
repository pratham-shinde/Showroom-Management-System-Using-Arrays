package utils;

import static com.app.core.Vehicle.sdf;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import com.app.core.Color;
import com.app.core.Vehicle;

import custom_Exception.ShowroomHandlingException;

public class ValidationRules {
	public static final double MIN_PRICE;
	public static final double MAX_PRICE;
	public static Date beginDate;
	public static Date endDate;

	static {
		MIN_PRICE = 10000;
		MAX_PRICE = 100000;

		try {
			beginDate = sdf.parse("01/01/2024");
			endDate = sdf.parse("31/12/2024");
		} catch (ParseException e) {
			System.out.println("In static block " + e);
		}

	}

	public static String validateChasisNo(String chasisNo, Vehicle vehicles[]) throws ShowroomHandlingException {
		Vehicle newVehicle = new Vehicle(chasisNo);
		for (Vehicle v : vehicles) {
			if (v != null)
				if (v.equals(newVehicle)) // dup detected
					throw new ShowroomHandlingException("Duplicate chasisNo detected !!!");

		}
		return chasisNo;

	}

	public static Color validateColor(String color) throws ShowroomHandlingException {
		try {
			return Color.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException e) {
			StringBuilder sb = new StringBuilder("Available colors \n");
			sb.append(Arrays.toString(Color.values()));
			sb.append("Choose correct color !");
			throw new ShowroomHandlingException(sb.toString());
		}
	}

	public static double validatePrice(double price) throws ShowroomHandlingException {
		if (price < MIN_PRICE || price > MAX_PRICE) {
			throw new ShowroomHandlingException("price is out of range !!!");
		}
		return price;
	}

	public static Date validateDate(String date) throws ParseException, ShowroomHandlingException {
		Date validDate = sdf.parse(date);
		if (validDate.before(beginDate) || validDate.after(endDate))
			throw new ShowroomHandlingException("Date is out of range !!!");
		return validDate;

	}

	public static Vehicle findVehicle(String chasisNo, Vehicle[] vehicles) throws ShowroomHandlingException {
		Vehicle vehicle = new Vehicle(chasisNo);
		for (Vehicle v : vehicles) {
			if (v != null)
				if (v.equals(vehicle))
					return v;

		}
		throw new ShowroomHandlingException("Vehicle with " + chasisNo + "is not found");
	}

}

//117 black 95000 11/07/2024 THAR
//253 red 50000 25/03/2024 SWIFT