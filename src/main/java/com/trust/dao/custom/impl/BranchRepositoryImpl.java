package com.trust.dao.custom.impl;

import com.trust.dao.custom.BranchCustomRepository;
import com.trust.dao.custom.CustomerCustomRepository;
import com.trust.dto.branch.BranchDTO;
import com.trust.dto.branch.BranchFilterDTO;
import com.trust.dto.customer.CustomerDTO;
import com.trust.mapper.BranchMapper;
import com.trust.model.Branch;
import com.trust.model.Customer;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchRepositoryImpl implements BranchCustomRepository {
    private final MongoTemplate mongoTemplate;
    private final BranchMapper branchMapper;

    @Override
    public Boolean update(BranchDTO dto) {
        Query query = new Query(Criteria.where("id").is(dto.getId()));
        Update update = new Update();

        if (dto.getName() != null)
            update.set("name", dto.getName());

        if (dto.getNameAr() != null)
            update.set("nameAr", dto.getNameAr());

        if (dto.getAddress() != null)
            update.set("address", dto.getAddress());

        if (dto.getActive() != null)
            update.set("active", dto.getActive());


        mongoTemplate.updateFirst(query, update, Branch.class);
        return true;
    }

    @Override
    public Page<BranchDTO> filter(BranchFilterDTO dto) {
        Query query = new Query();
        Criteria criteria = Criteria.where("brand.$id").is(new ObjectId(dto.getBrandId()));

        if (dto.getName() != null) {
            criteria = criteria.and("name").regex(dto.getName(), "i"); // "i" for case-insensitive matching
        }

        if (dto.getNameAr() != null) {
            criteria = criteria.and("nameAr").regex(dto.getNameAr(), "i"); // "i" for case-insensitive matching
        }

        if (dto.getActive() != null) {
            criteria = criteria.and("active").is(dto.getActive());
        }

        query.addCriteria(criteria);

        Pageable pageRequest =PageRequest.of(dto.getPage(),dto.getSize());
        query.with(pageRequest);

        List<BranchDTO> branches = branchMapper.toDtoList(mongoTemplate.find(query, Branch.class));
        return new PageImpl<>(branches, pageRequest,branches.size());
    }

}
