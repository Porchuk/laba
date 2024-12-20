package lab_4;

import lombok.Getter;

import java.util.Objects;

/**
 * Клас, що представляє номер готелю.
 */
@Getter
public class Room {
    private String roomType;
    private int numberOfBeds;
    private int roomNumber;
    private String amenities;

    private Room(RoomBuilder builder) {
        this.roomType = builder.roomType;
        this.numberOfBeds = builder.numberOfBeds;
        this.roomNumber = builder.roomNumber;
        this.amenities = builder.amenities;
    }

    /**
     * Метод для зручного відображення інформації про номер.
     */
    @Override
    public String toString() {
        return "Room [Type: " + roomType + ", Number of Beds: " + numberOfBeds + ", Room Number: " + roomNumber + ", Amenities: " + amenities + "]";
    }

    /**
     * Перевизначення методу equals для порівняння об'єктів.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numberOfBeds == room.numberOfBeds &&
                roomNumber == room.roomNumber &&
                Objects.equals(roomType, room.roomType) &&
                Objects.equals(amenities, room.amenities);
    }

    /**
     * Перевизначення методу hashCode для генерації хеш-коду.
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomType, numberOfBeds, roomNumber, amenities);
    }

    /**
     * Патерн Builder для класу Room.
     */
    public static class RoomBuilder {
        private String roomType;
        private int numberOfBeds;
        private int roomNumber;
        private String amenities;

        public RoomBuilder setRoomType(String roomType) {
            this.roomType = roomType;
            return this;
        }

        public RoomBuilder setNumberOfBeds(int numberOfBeds) {
            this.numberOfBeds = numberOfBeds;
            return this;
        }

        public RoomBuilder setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public RoomBuilder setAmenities(String amenities) {
            this.amenities = amenities;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
