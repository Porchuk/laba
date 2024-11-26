package lab_4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer.Builder builder;

    @BeforeEach
    void setUp() {
        builder = new Customer.Builder();
    }

    @Test
    void testValidCustomer() {
        assertDoesNotThrow(() -> {
            Customer customer = builder
                    .setLastName("Іванов")
                    .setFirstName("Петро")
                    .setIdDocument("AB123456")
                    .setBirthDate(LocalDate.of(1992, 5, 15))
                    .build();
            assertNotNull(customer);
            assertEquals("Іванов", customer.getLastName());
            assertEquals("Петро", customer.getFirstName());
            assertEquals("AB123456", customer.getIdDocument());
            assertEquals(LocalDate.of(1992, 5, 15), customer.getBirthDate());
        });
    }

    @Test
    void testInvalidLastName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setLastName("I") // Занадто коротке ім'я
                    .setFirstName("Петро")
                    .setIdDocument("AB123456")
                    .setBirthDate(LocalDate.of(1992, 5, 15))
                    .build();
        });
        assertTrue(exception.getMessage().contains("Last name must be between 2 and 50 characters"));
    }

    @Test
    void testInvalidFirstName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setLastName("Іванов")
                    .setFirstName("") // Пусте ім'я
                    .setIdDocument("AB123456")
                    .setBirthDate(LocalDate.of(1992, 5, 15))
                    .build();
        });
        assertTrue(exception.getMessage().contains("First name must be between 2 and 50 characters"));
    }

    @Test
    void testInvalidIdDocument() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setLastName("Іванов")
                    .setFirstName("Петро")
                    .setIdDocument("123") // Недостатньо символів
                    .setBirthDate(LocalDate.of(1992, 5, 15))
                    .build();
        });
        assertTrue(exception.getMessage().contains("ID Document must be 8-12 alphanumeric characters"));
    }

    @Test
    void testFutureBirthDate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setLastName("Іванов")
                    .setFirstName("Петро")
                    .setIdDocument("AB123456")
                    .setBirthDate(LocalDate.now().plusDays(1)) // Майбутня дата
                    .build();
        });
        assertTrue(exception.getMessage().contains("Birth date must be in the past"));
    }

    @Test
    void testNullValues() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.setLastName(null)
                    .setFirstName(null)
                    .setIdDocument(null)
                    .setBirthDate(null)
                    .build();
        });
        assertTrue(exception.getMessage().contains("Last name cannot be null"));
        assertTrue(exception.getMessage().contains("First name cannot be null"));
        assertTrue(exception.getMessage().contains("ID Document cannot be null"));
        assertTrue(exception.getMessage().contains("Birth date cannot be null"));
    }
}
