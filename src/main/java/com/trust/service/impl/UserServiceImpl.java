package com.trust.service.impl;

import com.trust.dao.UserRepository;

import com.trust.dto.LoginDTO;
import com.trust.dto.customer.CustomerLoginDTO;
import com.trust.dto.VerifyLoginOtpDTO;
import com.trust.error.exceptions.AbstractTrustException;
import com.trust.model.User;
import com.trust.util.ErrorMessagesUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.trust.util.PasswordUtil.encrypt;


@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final ErrorMessagesUtil errorMessagesUtil;

    /**
     * Creates a new User object in the UserRepository.
     * @param user The User object to create.
     * @return The created User object.
     * @throws AbstractTrustException if an error occurs during the creation process.
     */
    public User create(User user) {
        try {
            log.info("Start creating a user...");
            user.setPassword(encrypt(user.getPassword()));
            return userRepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new AbstractTrustException(errorMessagesUtil.getErrorMessage("email.already.exists"));
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Updates the User object in the UserRepository.
     * @param user The User object to update.
     * @return true if the update is successful, false otherwise.
     * @throws AbstractTrustException if an error occurs during the update process.
     */
    public Boolean update(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Deletes the User object in the UserRepository.
     * @param id The User id to delete.
     * @return true if to delete is successful, false otherwise.
     * @throws AbstractTrustException if an error occurs during the delete process.
     */
    public Boolean delete(String id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    /**
     * Loads a User object from the UserRepository based on the provided phone number and password.
     * @param user The CustomerLoginDTO object containing the phone number and password.
     * @return An Optional containing the User object if found, or an empty Optional if not found.
     * @throws AbstractTrustException if an error occurs during the loading process.
     */
    public Optional<User> getByPhoneNumberAndPassword(CustomerLoginDTO user) {
        try {
            String encryptedPassword = encrypt(user.getPassword());
            return userRepository.findByPhoneNumberAndPassword(user.getPhoneNumber(), encryptedPassword);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

    /**
     * Loads a User object from the UserRepository based on the provided email and password.
     * @param dto The LoginDTO object containing the email and password.
     * @return An Optional containing the User object if found, or an empty Optional if not found.
     * @throws AbstractTrustException if an error occurs during the loading process.
     */
    public Optional<User> getByEmailAndPassword(LoginDTO dto) {
        try {
            String encryptedPassword = encrypt(dto.getPassword());
            return userRepository.findByEmailAndPassword(dto.getEmail(), encryptedPassword);
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }


    /**
     * Verifies the OTP provided by the customer and returns the corresponding User object if the OTP is valid.
     * If the OTP is invalid or not found, returns null.
     * @param dto The VerifyCustomerOtpDTO object containing the phone number and OTP to verify.
     * @return The User object if the OTP is valid, otherwise null.
     * @throws AbstractTrustException if an error occurs during the verification process.
     */
    public User verifyOtp(VerifyLoginOtpDTO dto) {
        try {
            Optional<User> userOptional = userRepository.findByPhoneNumberAndLoginOtp(dto.getPhoneNumber(), dto.getOtp());

            // If the user is found, update the login OTP and save the user
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setLoginOtp(null);
                userRepository.save(user);
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new AbstractTrustException(e.getMessage());
        }
    }

}
