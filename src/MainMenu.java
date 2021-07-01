import api.HotelResource;
import model.IRoom;
import model.Reservation;
import service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    public static HotelResource hotelResource = new HotelResource();
    public static CustomerService customerService = new CustomerService();

    public static void startActions() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            try {
                System.out.println("Welcome to Hotel Reservation Application");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                System.out.println("Please select a number for the menu option");
                int user_ch = Integer.parseInt(scanner.next());
                int choice = user_ch;
                if (choice == 1) {
                    System.out.println("Please Enter CheckIn Date (mm-dd-yyyy)");
                    //int cin = Integer.parseInt(scanner.nextLine());
                    boolean booked = false;
                    Date cin = dateFormatter(scanner.next());
                    System.out.println("Please Enter CheckOut Date (MM-dd-yyyy)");
                    Date cout = dateFormatter(scanner.next());
                    Collection<IRoom> availbleRooms = hotelResource.findARoom(cin, cout);
                    if (availbleRooms.isEmpty()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(cin);
                        calendar.add(Calendar.DAY_OF_MONTH, 7);
                        cin = calendar.getTime();
                        calendar.setTime(cout);
                        calendar.add(Calendar.DAY_OF_MONTH, 7);
                        cout = calendar.getTime();
                        availbleRooms = hotelResource.findARoom(cin, cout);
                        if (!availbleRooms.isEmpty()) {
                            for (IRoom each : availbleRooms) {
                                System.out.println(each.toString());
                            }
                        }
                        System.out.println("Please choose desired room number");
                        int user_ch_room = Integer.parseInt(scanner.nextLine());
                        System.out.println("Please enter your Email");
                        String cus_email = scanner.nextLine();
                        try {
                            while (!Pattern.compile("^(.+)@(.+).com$").matcher(cus_email).matches()) {
                                System.out.println("Please Re-Enter your Email");
                                cus_email = scanner.nextLine();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        for (IRoom each : availbleRooms
                        ) {
                            if (String.valueOf(user_ch_room).equals(each.getRoomNumber())) {
                                hotelResource.bookARoom(cus_email, each, cin, cout);
                                System.out.println("Reservation Successful");
                                booked = true;
                            }
                        }
                        if (!booked) {
                            System.out.println("Please try again... Make sure you have an account");
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("Please Enter your Email");
                    String cus_email = scanner.next();
                    try {
                        while (!Pattern.compile("^(.+)@(.+).com$").matcher(cus_email).matches()) {
                            System.out.println("Please Re-Enter your Email");
                            cus_email = scanner.next();
                        }
                        for (Reservation each : hotelResource.getCustomersReservations(cus_email)
                        ) {
                            System.out.println(each.toString());
                        }
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }
                } else if (choice == 3) {
                    HotelResource hotelResource = new HotelResource();
                    System.out.println("Enter your First Name");
                    String c_fname = scanner.next();
                    System.out.println("Enter your Last Name");
                    String c_lname = scanner.next();
                    System.out.println("Enter your E-mail");
                    String c_email = scanner.next();
                    hotelResource.createACustomer(c_email, c_fname, c_lname);
                    //run = false;
                } else if (choice == 4) {
                    AdminMenu.startActions();
                } else if (choice == 5) {
                    run = false;
                    //break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Date dateFormatter(String date) {
        Date checkOut = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        try {
            checkOut = dateFormat.parse(date);
            System.out.println(checkOut);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return checkOut;
    }

    public static void main(String[] args) {
        MainMenu.startActions();
    }
}
