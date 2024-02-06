package tester;

import static utils.ValidationRules.*;

import java.util.Scanner;

import com.app.core.Color;
import com.app.core.Vehicle;

import custom_Exception.ShowroomHandlingException;

public class ShowroomTester {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Showroom Capacity");
			Vehicle[] vehicles = new Vehicle[sc.nextInt()];
			int counter = 0;
			boolean exit = false;
			System.out.println(
					"options: 1. Add a vehicle 2.Display 3.Display all vehicles having specified color 4.Add discount on particular Vehicle 5.Purchase a vehicle 6.choose color for your car 10.Exit");

			while (!exit) {
				if (counter == 0)
					System.out.println("Choose");
				else
					System.out.println("Choose Again..");
				try {
					switch (sc.nextInt()) {
					case 1:
						if (counter < vehicles.length) {
							System.out.println(
									"Enter vehicle details : chasisNo,  color,  basePrice,  manufactureDate(day/mon/yr),  company");
							Vehicle v1 = new Vehicle(validateChasisNo(sc.next(), vehicles), validateColor(sc.next()),
									validatePrice(sc.nextDouble()), validateDate(sc.next()), sc.next());
							vehicles[counter++] = v1;
							System.out.println("Vehicle added successFully");

						} else {
							throw new ShowroomHandlingException("Showroom capacity is full");
						}

						break;
					case 2:
						for (Vehicle v : vehicles) {
							if (v != null)
								System.out.println(v);
						}

						break;

					case 3:
						System.out.println("Enter color");
						Color color = validateColor(sc.next());
						for (Vehicle v : vehicles) {
							if (v != null) {
								if (color.equals(v.getColor()))
									System.out.println(v);
							}
						}
						break;
					case 4:
						System.out.println("Enter chasisNo and Discount amount");
						String chasisNo = sc.next();
						double discount = sc.nextDouble();
						Vehicle vehicle = findVehicle(chasisNo, vehicles);
						System.out.println(vehicle.getBasePrice());
						double amount = vehicle.getBasePrice() - discount;
						vehicle.setBasePrice(amount);
						System.out.println("Final Price= " + vehicle.getBasePrice() + " Discount of Rupees " + discount
								+ " Applied ");
						break;

					case 5: // Purchasing
						System.out.println("Enter chasis No: ");
						chasisNo = sc.next();
						vehicle = findVehicle(chasisNo, vehicles);
						color = vehicle.getColor();
						if (vehicle.getAddress() == null) {
							for (Color c : Color.values()) {
								if (c.equals(color)) {
									double total = vehicle.getBasePrice() + c.getAdditionalCost();
									vehicle.setBasePrice(total);
								}

							}
							System.out.println(
									"Enter address: 1.addressLine1 2.addressLine2  3.city  4.state  5.country  6.zipCode");
							vehicle.linkAddress(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
							System.out.println("Car " + vehicle.getCompany() + " with color " + color.name()
									+ " is ready to Deliver with final price " + vehicle.getBasePrice());
						} else {
							throw new ShowroomHandlingException("Vehicle is not available for purchasing...!!");
						}
						break;

					case 10:
						System.out.println("Thank you for coming !!");
						exit = true;

						break;

					}
				} catch (Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}
		}

	}

}
