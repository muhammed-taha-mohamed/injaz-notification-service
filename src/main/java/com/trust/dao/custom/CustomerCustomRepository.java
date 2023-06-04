package com.trust.dao.custom;


import com.trust.dto.customer.CustomerDTO;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerCustomRepository {
     Boolean update(CustomerDTO dto);
}
