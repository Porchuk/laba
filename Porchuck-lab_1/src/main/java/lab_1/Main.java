package lab_1;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Створення об'єкта Room за допомогою патерну Builder
        Room room = new Room.RoomBuilder()
                .setRoomType("Deluxe")
                .setNumberOfBeds(2)
                .setRoomNumber(101)
                .setAmenities("Wi-Fi, TV, Minibar")
                .build();

        // Виведення інформації про номер
        System.out.println("Створений номер: " + room);

        // Створення об'єкта Customer
        Customer customer = new Customer("Іванов", "Петро", "AB123456", LocalDate.parse("1992-05-15")); // 1992-05-15

        // Виведення інформації про замовника
        System.out.println("Створений замовник: " + customer);

        // Створення об'єкта Booking
        LocalDate startDate = LocalDate.now(); // поточна дата
        LocalDate endDate = LocalDate.now().plusDays(3); // 3 дні після поточної дати

        Booking booking = new Booking(room, customer, startDate, endDate, true);

        // Виведення інформації про бронювання
        System.out.println("Створене бронювання: " + booking);

        // Перевірка методу equals на об'єктах
        Room room2 = new Room.RoomBuilder()
                .setRoomType("Deluxe")
                .setNumberOfBeds(2)
                .setRoomNumber(101)
                .setAmenities("Wi-Fi, TV, Minibar")
                .build();

        System.out.println("Чи однакові номери room і room2? " + room.equals(room2));

        Customer customer2 = new Customer("Іванов", "Петро", "AB123456", LocalDate.parse("1992-04-15"));
        System.out.println("Чи однакові замовники customer і customer2? " + customer.equals(customer2));

        // Перевірка об'єктів Booking
        Booking booking2 = new Booking(room2, customer2, startDate, endDate, true);
        System.out.println("Чи однакові бронювання booking і booking2? " + booking.equals(booking2));
    }
}
