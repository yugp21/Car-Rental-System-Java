public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C1", "BMW", "M8", 15000);
        Car car2 = new Car("C2", "McLaren", "720S", 25000);
        Car car3 = new Car("C3", "BMW", "M4 Competition", 13000);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}
