package com.smartera.storeapp.service;

import com.smartera.storeapp.entity.Customer;
import com.smartera.storeapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */
@Service
public class AuthService {
    @Autowired
    private CustomerRepository customerRepository;

    public boolean authenticateUser(String email, String password, String authorizationLevel) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getPassword().equals(password)) {
                System.out.println("Password is true : " + password);
                if (customer.getAuthorizationLevel().equals(authorizationLevel)) {
                    System.out.println("authorizationLevel is correct" + authorizationLevel);
                    return true;
                } else {
                    System.out.println("authorizationLevel is wrong: " + authorizationLevel
                            + " Entered AuthorizationLevel : " + customer.getAuthorizationLevel());
                }
            } else {
                System.out.println("Password wrong : " + password);
                return false;
            }
        }
        return false;
    }
}
