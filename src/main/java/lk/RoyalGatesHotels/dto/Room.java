package lk.RoyalGatesHotels.dto;

public class Room {
    private String room_number;
    private String roomType;
    private String status;
    private double price;

    public Room(String roomNumber, String roomType, String status, double price) {

        this.room_number = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
    }


    public String getRoomNumber() {
        return room_number;
    }

    public void setRoomNumber(String roomNumber) {
        this.room_number = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + room_number + '\'' +
                ", roomType='" + roomType + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
