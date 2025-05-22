
import java.util.*;

public class CarRentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

            double totalPrice = car.calculatePrice(days);

            System.out.println("\n== Rental Information ==\n");
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Car: " + car.getBrand() + " " + car.getModel());
            System.out.println("Rental Days: " + days);
            System.out.printf("Total Price: ₹%.2f\n", totalPrice);
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }

        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int customerCounter = 1;

        while (true) {
            System.out.println("\n==== Car Rental System ====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    String customerId = "CU" + customerCounter++;
                    Customer customer = new Customer(customerId, name);
                    addCustomer(customer);

                    System.out.println("\nAvailable Cars:");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println(car.getCarId() + ": " + car.getBrand() + " " + car.getModel() + " - ₹" + car.calculatePrice(1) + "/day");
                        }
                    }

                    System.out.print("Enter Car ID to rent: ");
                    String carId = scanner.nextLine();

                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(carId) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }

                    if (selectedCar == null) {
                        System.out.println("Invalid or unavailable car.");
                        break;
                    }

                    System.out.print("Enter number of days to rent: ");
                    int days = scanner.nextInt();
                    rentCar(selectedCar, customer, days);
                    break;

                case 2:
                    System.out.print("Enter Car ID to return: ");
                    String returnId = scanner.nextLine();
                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equalsIgnoreCase(returnId) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }

                    if (carToReturn != null) {
                        returnCar(carToReturn);
                    } else {
                        System.out.println("Invalid car ID or car is already available.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
