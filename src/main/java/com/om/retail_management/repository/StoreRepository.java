package com.om.retail_management.repository;

import com.om.retail_management.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long>{

    Optional<Store> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Store> findByCity(String city);
}
