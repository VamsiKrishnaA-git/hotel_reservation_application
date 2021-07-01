package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double roomPrice;
    private RoomType enumeration;

    public Room(String roomNumber, Double roomPrice, RoomType enumeration, Boolean isFree) {
        this.roomNumber = roomNumber;

        this.roomPrice = Double.parseDouble(String.valueOf(roomPrice));
        this.enumeration = enumeration;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public RoomType getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", enumeration=" + enumeration +
                '}';
    }
}
