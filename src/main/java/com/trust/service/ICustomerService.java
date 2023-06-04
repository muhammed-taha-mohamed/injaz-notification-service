package com.trust.service;

import com.trust.dto.LoginResponseDTO;
import com.trust.dto.VerifyLoginOtpDTO;
import com.trust.dto.customer.*;

public interface ICustomerService {
    CustomerDTO create (CreateCustomerDTO  dto);
    Boolean update (CustomerDTO dto);
    CustomerDTO findById (String id);
    Boolean delete (String id);
    Boolean sendOtp (CustomerLoginDTO dto);
    LoginResponseDTO verifyOtp(VerifyLoginOtpDTO dto);

}
