package com.om.retail_management.service;

import com.om.retail_management.dto.StoreResponseDto;
import com.om.retail_management.dto.StoreRequestDto;

import java.util.List;

public interface StoreService {

    StoreResponseDto createStore(StoreRequestDto request);

    List<StoreResponseDto> getAllStores();

    StoreResponseDto getStoreById(Long id);

    StoreResponseDto updateStore(Long id, StoreRequestDto request);

    void deleteStore(Long id);
}
