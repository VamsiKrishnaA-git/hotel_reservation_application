package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {
    public static List<IRoom> rooms = new ArrayList<>();
    public static Map<Customer, Reservation> reservationMap =  new HashMap<>();
    public static List<Reservation> reservations = new ArrayList<>();

    public ReservationService() {
    }

    public static void addRoom(IRoom room){
        // newroom = new Room(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId) throws Exception {
        for (IRoom each: rooms){
            if(each.getRoomNumber().equalsIgnoreCase(roomId)){
                return each;
            }
        }
        throw new Exception("Room Not found");
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<>();
        if (reservations.isEmpty()) {
            return rooms;
        } else {
            for (Reservation reservation : reservations) {
                if (!reservation.getCheckInDate().after(checkInDate) && !reservation.getCheckOutDate().before(checkOutDate)) {
                    for (IRoom room : rooms) {
                        if (!reservation.getRoom().equals(room)) {
                            availableRooms.add(room);
                        }
                    }
                }
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        List<Reservation> customerReservation = new ArrayList<>();
        for (Reservation each: reservations){
            if(each.getCustomer().equals(customer)){
                customerReservation.add(each);
            }
        }
        return customerReservation;
    }

    public static void printAllReservation(){
        if(!reservations.isEmpty()){
            System.out.println(reservations.toString());
        }

    }


}
