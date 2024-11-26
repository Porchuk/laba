package lab_3.Serializers;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lab_3.Booking;
import lab_3.Customer;
import lab_3.Room;
import lab_3.Serializers.YAMLSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class YAMLSerializerTest {
    private YAMLSerializer<Booking> yamlSerializer;
    private Booking booking;
    private Room room;
    private Customer customer;

    @BeforeEach
    void setUp() {
        yamlSerializer = new YAMLSerializer<>(Booking.class);

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
        // Серіалізація об'єкта Booking у YAML
        String yamlString = yamlSerializer.serialize(booking);

        assertNotNull(yamlString);
        assertAll(() -> {
            assertTrue(yamlString.contains("roomType: \"Deluxe\""), "YAML має містити roomType 'Deluxe'");
            assertTrue(yamlString.contains("firstName: \"Петро\""), "YAML має містити firstName 'Петро'");
            assertTrue(yamlString.contains("paid: true"), "YAML має містити paid true");
            assertTrue(yamlString.contains("numberOfBeds: 2"), "YAML має містити numberOfBeds 2");
        });
    }

}
