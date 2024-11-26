package lab_3;

import lab_2.Booking;
import lab_2.Customer;
import lab_2.Room;
import lab_2.Services.BookingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення об'єктів Room
        lab_2.Room room1 = new lab_2.Room.RoomBuilder()
                .setRoomType("Deluxe")
                .setNumberOfBeds(2)
                .setRoomNumber(101)
                .setAmenities("Wi-Fi, TV, Minibar")
                .build();

        lab_2.Room room2 = new lab_2.Room.RoomBuilder()
                .setRoomType("Standard")
                .setNumberOfBeds(1)
                .setRoomNumber(102)
                .setAmenities("Wi-Fi")
                .build();

        lab_2.Room room3 = new lab_2.Room.RoomBuilder()
                .setRoomType("Suite")
                .setNumberOfBeds(3)
                .setRoomNumber(103)
                .setAmenities("Wi-Fi, TV, Minibar, Jacuzzi")
                .build();

        // Створення об'єктів Customer
        lab_2.Customer customer1 = new lab_2.Customer("Іванов", "Петро", "AB123456", LocalDate.parse("1992-05-15")); // 1992-05-15
        lab_2.Customer customer2 = new Customer("Петрова", "Олена", "CD789012", LocalDate.parse("1990-03-10")); // 1990-03-10

        // Створення об'єктів Booking
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate1 = LocalDate.now().minusDays(2); // 2 дні тому
        LocalDate endDate1 = LocalDate.now().plusDays(1);   // Завтра

        LocalDate startDate2 = LocalDate.now().plusDays(1); // Завтра
        LocalDate endDate2 = LocalDate.now().plusDays(4);   // Через 4 дні

        lab_2.Booking booking1 = new lab_2.Booking(room1, customer1, startDate1, endDate1, true);
        lab_2.Booking booking2 = new lab_2.Booking(room2, customer2, startDate2, endDate2, false);

        // Додавання об'єктів до списків
        List<lab_2.Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        List<lab_2.Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        // Створення сервісу BookingService
        BookingService bookingService = new BookingService();

        // Перевірка методів сервісу
        System.out.println("Доступні номери:");
        List<lab_2.Room> availableRooms = bookingService.getAvailableRooms(rooms, bookings);
        availableRooms.forEach(System.out::println);

        System.out.println("\nБронювання, відсортовані за датою початку:");
        List<lab_2.Booking> sortedBookings = bookingService.sortBookingsByStartDate(bookings);
        sortedBookings.forEach(System.out::println);

        System.out.println("\nНомери, відсортовані за кількістю ліжок:");
        List<Room> sortedRooms = bookingService.sortRoomsByBeds(rooms);
        sortedRooms.forEach(System.out::println);

        System.out.println("\nБронювання для замовника " + customer1.getFirstName() + " " + customer1.getLastName() + ":");
        List<lab_2.Booking> customerBookings = bookingService.getBookingsByCustomer(bookings, customer1);
        customerBookings.forEach(System.out::println);

        System.out.println("\nБронювання після поточної дати:");
        List<Booking> bookingsAfterDate = bookingService.getBookingsAfterDate(bookings, currentDate);
        bookingsAfterDate.forEach(System.out::println);
    }
}
