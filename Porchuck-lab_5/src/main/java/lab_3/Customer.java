package lab_3;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Клас, що представляє замовника.
 */
@Getter
public class Customer {
    private String lastName;
    private String firstName;
    private String idDocument;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public Customer(String lastName, String firstName, String idDocument, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.idDocument = idDocument;
        this.birthDate = birthDate;
    }

    /**
     * Метод для зручного відображення інформації про замовника.
     */
    @Override
    public String toString() {
        return "Customer [Last Name: " + lastName + ", First Name: " + firstName +
                ", ID Document: " + idDocument + ", Birth Date: " + birthDate + "]";
    }

    /**
     * Перевизначення методу equals для порівняння об'єктів.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(lastName, customer.lastName) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(idDocument, customer.idDocument) &&
                Objects.equals(birthDate, customer.birthDate);
    }

    /**
     * Перевизначення методу hashCode для генерації хеш-коду.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, idDocument, birthDate);
    }
}
