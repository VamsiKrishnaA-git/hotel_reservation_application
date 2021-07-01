package model;

import java.util.Date;

public class Driver {
    public static void main(String args[]){
        Customer customer = new Customer("john", "cena", "myemail@gmail.com");
        System.out.println(customer);
        FreeRoom room = new FreeRoom("121", RoomType.SINGLE);
        System.out.println(room.getEnumeration());
        System.out.println(room.getRoomNumber());
        System.out.println(room.getRoomPrice());
        System.out.println(room.getRoomType());
        System.out.println(room.getClass());

        Reservation reservation = new Reservation(customer,room,new Date(),new Date());
        System.out.println(reservation.getCheckInDate());
        System.out.println(reservation.getCheckOutDate());
        System.out.println(reservation.getCustomer());
        System.out.println(reservation.getRoom());
        System.out.println(room.toString());

    }
}
