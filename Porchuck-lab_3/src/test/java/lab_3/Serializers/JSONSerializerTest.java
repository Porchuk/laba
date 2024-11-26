package lab_3.Serializers;

import lab_3.Booking;
import lab_3.Customer;
import lab_3.Room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

class JSONSerializerTest {
    private JSONSerializer<Booking> jsonSerializer;
    private ObjectMapper objectMapper;
    private Booking booking;
    private Room room;
    private Customer customer;

    @BeforeEach
    void setUp() {
        jsonSerializer = new JSONSerializer<Booking>(Booking.class);
        objectMapper = new ObjectMapper();

        // Створення об'єкта Room
        room = new Room.RoomBuilder()
                .setRoomType("Deluxe")
                .setNumberOfBeds(2)
                .setRoomNumber(101)
                .setAmenities("Wi-Fi, TV, Minibar")
                .build();

        // Створення об'єкта Customer
        customer = new Customer("Іванов", "Петро", "AB123456", LocalDate.parse("1992-05-15")); // 1992-05-15

        // Створення об'єкта Booking
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(3); // +3 дні
        booking = new Booking(room, customer, startDate, endDate, true);
    }

    @Test
    void testSerialize() throws IOException {
        // Серіалізація об'єкта Booking у JSON
        String jsonString = jsonSerializer.serialize(booking);

        assertNotNull(jsonString);
        assertTrue(jsonString.contains("\"roomType\":\"Deluxe\""), "JSON має містити roomType 'Deluxe'");
        assertTrue(jsonString.contains("\"firstName\":\"Петро\""), "JSON має містити firstName 'Петро'");
        assertTrue(jsonString.contains("\"paid\":true"), "JSON має містити paid true");
        assertTrue(jsonString.contains("\"numberOfBeds\":2"), "JSON має містити numberOfBeds 2");
    }

}