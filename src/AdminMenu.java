import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.ReservationService.rooms;

public class AdminMenu {

    public static void startActions(){
        boolean isrun = true;
        try(Scanner scanner = new Scanner(System.in)) {
            while (isrun) {
                try {
                    System.out.println("1. See ALl Customers");
                    System.out.println("2. See ALl Rooms");
                    System.out.println("3. See ALl Reservations");
                    System.out.println("4. Add a Room");
                    System.out.println("5. Back to Main Menu");
                    int choice = Integer.parseInt(scanner.nextLine());
                    if(choice == 1){
                        AdminResource adminResource = new AdminResource();
                        for (Customer each:adminResource.getAllCustomers()
                             ) {
                            System.out.println(each.toString());
                        }
                    }
                    else if (choice == 2){
                        //AdminResource adminResource = new AdminResource(customerService, reservationService);
                        for (IRoom each:rooms
                             ) {
                                System.out.println(each.toString());
                        }
                    }
                    else if (choice == 3){
                        AdminResource adminResource = new AdminResource();
                        adminResource.displayAllReservations();
                    }
                    else if (choice == 4){
                        AdminResource adminResource = new AdminResource();
                        String addRoom;
                        Integer type;
                        do {
                            RoomType roomType = null;
                            Boolean isFree = true;
                            //scanner.nextLine();
                            System.out.println("Enter room number:");
                            String roomNumber = scanner.nextLine();
                            System.out.println("Enter price per night:");
                            Double roomPrice = scanner.nextDouble();
                            do {
                                System.out.println("Enter room type: 1 - Single bed, 2 - Double bed");
                                type = scanner.nextInt();
                                if (type == 1) {
                                    roomType = RoomType.SINGLE;
                                } else if (type == 2) {
                                    roomType = RoomType.DOUBLE;
                                } else {
                                    System.out.println("Invalid input");
                                }
                            } while (type != 1 && type != 2);
                            IRoom room = new Room(roomNumber, roomPrice, roomType, isFree);
                            List<IRoom> rooms = new ArrayList<>();
                            rooms.add(room);
                            adminResource.addRoom(rooms);
                            do {
                                System.out.println("Would you like to add another room? y/n");
                                addRoom = scanner.nextLine().toLowerCase().trim();
                            } while (!addRoom.equals("y") && !addRoom.equals("n"));
                        } while (addRoom.equals("y"));


                    }
                    else if (choice == 5){
                        isrun = false;
                        MainMenu.startActions();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
