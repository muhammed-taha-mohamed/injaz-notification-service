package com.trust.mapper;

import com.trust.dto.customer.CreateCustomerDTO;
import com.trust.dto.customer.CustomerDTO;
import com.trust.model.Customer;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring" )
public interface CustomerMapper {

    Customer toEntity(CreateCustomerDTO dto);
    CustomerDTO toDto (Customer customer);


}
