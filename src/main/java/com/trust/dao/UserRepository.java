package com.trust.dao;

import com.trust.dto.PhoneNumber;
import com.trust.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByPhoneNumberAndPassword (PhoneNumber phoneNumber , String password);
    Optional<User> findByEmailAndPassword (String email , String password);
    Optional<User> findByPhoneNumberAndLoginOtp(PhoneNumber phoneNumber , String otp);
}
