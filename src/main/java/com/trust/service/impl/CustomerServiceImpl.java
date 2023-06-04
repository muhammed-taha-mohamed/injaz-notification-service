package com.trust.service.impl;

import com.trust.config.security.JwtTokenProvider;
import com.trust.dao.CustomerRepository;
import com.trust.dao.custom.CustomerCustomRepository;
import com.trust.dto.GenerateTokenDto;
import com.trust.dto.LoginResponseDTO;
import com.trust.dto.VerifyLoginOtpDTO;
import com.trust.dto.customer.*;
import com.trust.enummeration.Role;
import com.trust.error.exceptions.AbstractTrustException;
import com.trust.mapper.CustomerMapper;
import com.trust.model.Customer;
import com.trust.model.User;
import com.trust.service.ICustomerService;
import com.trust.util.ErrorMessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.trust.util.OtpUtil.generateLogin;
import static com.trust.util.UserKeyUtil.*;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerCustomRepository customRepository;
    private final UserServiceImpl userService;
    private final CustomerMapper customerMapper;
    private final NotificationServiceImpl notifyService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ErrorMessagesUtil errorMessagesUtil;


    /**
     * Creates a new customer based on the provided CreateCustomerDTO object.
     *
     * @param dto The CreateCustomerDTO object containing the customer information.
     * @return The created CustomerDTO object.
     * @throws AbstractTrustException if an error occurs during the creation process.
     */
    @Override
    public CustomerDTO create(CreateCustomerDTO dto) {
        try {
            log.info("Start creating a new customer...");
            Customer customer = customerMapper.toEntity(dto);
            customer.setCustomerKey(generateCustomerKey(dto.getName()));

            // Create a user for the login process
            User user = userService.create(User.builder()
                    .email(customer.getEmail())
                    .role(Role.CUSTOMER)
                    .password(dto.getPassword())
                    .name(dto.getName())
                    .phoneNumber(dto.getPhoneNumber())
                    .active(true)
                    .image(customer.getImage())
                    .build());

            customer.setUser(user);
            log.info("Customer is created...");
            return customerMapper.toDto(customerRepository.save(customer));
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    @Override
    public Boolean update(CustomerDTO dto) {
        try {
            return customRepository.update(dto);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Sends an OTP (One-Time Password) to the user for login verification.
     *
     * @param dto The CustomerLoginDTO object containing the user information.
     * @return true if the OTP is sent successfully, false otherwise.
     * @throws AbstractTrustException if an error occurs during the OTP sending process.
     */
    @Override
    public Boolean sendOtp(CustomerLoginDTO dto) {
        try {
            Optional<User> optionalUser = userService.getByPhoneNumberAndPassword(dto);

            // Check if the user is present and has the role of a customer
            if (optionalUser.isPresent() && optionalUser.get().getRole() == Role.CUSTOMER) {
                User user = optionalUser.get();
                String otp = generateLogin();
                user.setLoginOtp(otp);
                userService.update(user);
                notifyService.sendSimpleMessage(user.getEmail(), "Trust app login", "Use this code to login: " + otp);
                return true;
            } else {
                throw new IllegalArgumentException(errorMessagesUtil.getErrorMessage("password.or.user.not.valid"));
            }
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Verifies the OTP provided by the customer and generates a login token if the OTP is valid.
     *
     * @param dto The VerifyCustomerOtpDTO object containing the phone number and OTP to verify.
     * @return The CustomerLoginResponseDTO object with the login token and user information.
     * @throws AbstractTrustException if an error occurs during the verification process.
     */
    @Override
    public LoginResponseDTO verifyOtp(VerifyLoginOtpDTO dto) {
        try {
            User user = userService.verifyOtp(dto);

            // If the user is found, generate a login token
            if (user != null) {
                Optional<Customer> customerOptional = customerRepository.findByUser_id(user.getId());
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    String token = jwtTokenProvider.generateToken(GenerateTokenDto.builder()
                            .username(user.getName())
                            .userKey(customer.getCustomerKey())
                            .userId(user.getId())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .build());
                    return LoginResponseDTO.builder()
                            .token(token)
                            .id(customer.getId())
                            .image(user.getImage())
                            .name(user.getName())
                            .email(user.getEmail())
                            .image(user.getImage())
                            .build();
                }
            }
            // If the user is not found or the OTP is invalid, throw an exception
            throw new IllegalArgumentException(errorMessagesUtil.getErrorMessage("otp.not.valid"));
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Finds a customer by the provided ID.
     *
     * @param id The ID of the customer to find.
     * @return The CustomerDTO object if found.
     * @throws EntityNotFoundException if the customer with the given ID is not found.
     * @throws AbstractTrustException  if an error occurs during the search process.
     */
    @Override
    public CustomerDTO findById(String id) {
        try {
            return customerMapper.toDto(customerRepository.findById(id)
                    .orElseThrow(() -> new AbstractTrustException("customer not found!")));
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    /**
     * Deletes a customer based on the provided ID.
     * If the customer exists, it will also delete the associated user.
     *
     * @param id The ID of the customer to be deleted.
     * @return true if the customer is deleted successfully, false otherwise.
     * @throws AbstractTrustException if an error occurs during the deletion process.
     */
    @Override
    public Boolean delete(String id) {
        try {
            // Check if the customer exists
            Optional<Customer> customerOptional = customerRepository.findById(id);
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                // Delete the associated user
                userService.delete(customer.getUser().getId());
                log.info("User with ID " + customer.getUser().getId() + " deleted successfully.");
            }
            // Delete the customer
            customerRepository.deleteById(id);
            log.info("Customer with ID " + id + " deleted successfully.");
            return true;
        } catch (Exception e) {
            log.error("An error occurred while deleting customer with ID " + id + ": " + e.getMessage());
            throw new AbstractTrustException(e.getMessage());
        }
    }


}
