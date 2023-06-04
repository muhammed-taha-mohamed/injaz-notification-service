package com.trust.dao.custom.impl;

import com.trust.dao.custom.CustomerCustomRepository;
import com.trust.dto.customer.CustomerDTO;
import com.trust.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Boolean update(CustomerDTO dto) {
        Query query = new Query(Criteria.where("id").is(dto.getId()));
        Update update = new Update();

        if (dto.getName() != null)
            update.set("name", dto.getName());

        if (dto.getEmail() != null)
            update.set("email", dto.getEmail());

        if (dto.getPhoneNumber() != null)
            update.set("phoneNumber", dto.getPhoneNumber());

        if (dto.getImage() != null)
            update.set("image", dto.getImage());

        mongoTemplate.updateFirst(query, update, Customer.class);
        return true;
    }

}
