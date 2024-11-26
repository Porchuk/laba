package lab_3.Serializers;

import lab_3.Booking;
import lab_3.Customer;
import lab_3.Room;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lab_3.Serializers.XMLSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class XMLSerializerTest {
    private XMLSerializer<Booking> xmlSerializer;
    private Booking booking;
    private Room room;
    private Customer customer;

    @BeforeEach
    void setUp() {
        xmlSerializer = new XMLSerializer<Booking>(Booking.class);

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
        // Серіалізація об'єкта Booking у XML
        String xmlString = xmlSerializer.serialize(booking);

        assertNotNull(xmlString);
        assertAll(() -> {
            assertTrue(xmlString.contains("<roomType>Deluxe</roomType>"), "XML має містити roomType 'Deluxe'");
            assertTrue(xmlString.contains("<firstName>Петро</firstName>"), "XML має містити firstName 'Петро'");
            assertTrue(xmlString.contains("<paid>true</paid>"), "XML має містити paid true");
            assertTrue(xmlString.contains("<numberOfBeds>2</numberOfBeds>"), "XML має містити numberOfBeds 2");
        });
    }

}
