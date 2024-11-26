package lab_2.Services;

import lab_2.Booking;
import lab_2.Comparator.BookingComparator;
import lab_2.Comparator.RoomComparator;
import lab_2.Customer;
import lab_2.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервіс для роботи з бронюванням, номерами та замовниками.
 */
public class BookingService {

    /**
     * Повертає список доступних кімнат (не зайнятих у вказані дати).
     */
    public List<Room> getAvailableRooms(List<Room> rooms, List<Booking> bookings) {
        return rooms.stream()
                .filter(room -> bookings.stream()
                        .noneMatch(booking -> booking.getRoom().equals(room)))
                .collect(Collectors.toList());
    }

    /**
     * Повертає список бронювань відсортованих за датою початку.
     */
    public List<Booking> sortBookingsByStartDate(List<Booking> bookings) {
        return bookings.stream()
                .sorted(BookingComparator.byStartDate())
                .collect(Collectors.toList());
    }

    /**
     * Повертає список номерів, відсортованих за кількістю ліжок.
     */
    public List<Room> sortRoomsByBeds(List<Room> rooms) {
        return rooms.stream()
                .sorted(RoomComparator.byNumberOfBeds())
                .collect(Collectors.toList());
    }

    /**
     * Повертає список бронювань, здійснених конкретним замовником.
     */
    public List<Booking> getBookingsByCustomer(List<Booking> bookings, Customer customer) {
        return bookings.stream()
                .filter(booking -> booking.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    /**
     * Повертає список бронювань, які були здійснені після вказаної дати.
     */
    public List<Booking> getBookingsAfterDate(List<Booking> bookings, LocalDate date) {
        return bookings.stream()
                .filter(booking -> booking.getStartDate().isAfter(date))
                .collect(Collectors.toList());
    }
}
