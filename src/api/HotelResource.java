package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private CustomerService customerService;
    private ReservationService reservationService;
    public HotelResource() {
    }

    public Customer getCustomer(String email){
        try {
             return CustomerService.getCustomer(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createACustomer(String email, String firstName, String lastName){
        try {
            CustomerService.addCustomer(email, firstName, lastName);
        }
        catch (Exception e){
            e.getLocalizedMessage();
        }
    }

    public IRoom getRoom(String roomNumber){
        try {
            return ReservationService.getARoom(roomNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        Customer customer = customerService.getCustomer(customerEmail);
        return ReservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) throws Exception {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkin, Date checkOut){
        return reservationService.findRooms(checkin, checkOut);
    }

}
