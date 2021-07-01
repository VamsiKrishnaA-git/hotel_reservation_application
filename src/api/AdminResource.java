package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    public Customer getCustomer(String email){
        try {
            return CustomerService.getCustomer(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom each: rooms
             ) {
            ReservationService.addRoom(each);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return ReservationService.rooms;
    }

    public Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public void displayAllReservations(){
        ReservationService.printAllReservation();
    }
}
