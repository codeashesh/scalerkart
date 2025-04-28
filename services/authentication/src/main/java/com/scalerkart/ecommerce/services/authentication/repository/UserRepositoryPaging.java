package com.scalerkart.ecommerce.services.authentication.repository;

import com.scalerkart.ecommerce.services.authentication.model.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryPaging extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT u FROM User u")
    List<User> findAll(Sort sort);
}
