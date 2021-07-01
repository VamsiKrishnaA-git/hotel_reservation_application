package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    static Map<String,Customer> customerMap = new HashMap<>();

    public static void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(customer.getEmail(), customer);

    }

    public static Customer getCustomer(String customerEmail) throws Exception {
        if(customerMap.containsKey(customerEmail)){
            return customerMap.get(customerEmail);
        }
        throw new Exception("No customer associated with this email");
    }

    public static Collection<Customer> getAllCustomers(){
        if(customerMap.isEmpty()){
            try {
                throw new Exception("The list is Empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customerMap.values();
    }
}
