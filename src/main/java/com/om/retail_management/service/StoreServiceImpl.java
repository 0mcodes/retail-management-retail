package com.om.retail_management.service;

import com.om.retail_management.dto.StoreRequestDto;
import com.om.retail_management.dto.StoreResponseDto;
import com.om.retail_management.entity.Store;
import com.om.retail_management.exception.DuplicateResourceException;
import com.om.retail_management.exception.StoreNotFoundException;
import com.om.retail_management.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    @Override
    public StoreResponseDto createStore(StoreRequestDto request) {

        if (storeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (storeRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        Store store = mapToEntity(request);

        Store savedStore = storeRepository.save(store);

        return mapToResponse(savedStore);
    }

    @Override
    public List<StoreResponseDto> getAllStores() {

        return storeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StoreResponseDto getStoreById(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() ->
                        new StoreNotFoundException("Store not found with id : " + id));

        return mapToResponse(store);
    }

    @Override
    public StoreResponseDto updateStore(Long id, StoreRequestDto request) {

        Store existingStore = storeRepository.findById(id)
                .orElseThrow(() ->
                        new StoreNotFoundException("Store not found with id : " + id));

        if (!existingStore.getEmail().equals(request.getEmail())
                && storeRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException("Email already exists.");
        }

        if (!existingStore.getPhoneNumber().equals(request.getPhoneNumber())
                && storeRepository.existsByPhoneNumber(request.getPhoneNumber())) {

            throw new DuplicateResourceException("Phone number already exists.");
        }

        existingStore.setName(request.getName());
        existingStore.setAddress(request.getAddress());
        existingStore.setCity(request.getCity());
        existingStore.setPhoneNumber(request.getPhoneNumber());
        existingStore.setEmail(request.getEmail());

        Store updatedStore = storeRepository.save(existingStore);

        return mapToResponse(updatedStore);
    }

    @Override
    public void deleteStore(Long id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() ->
                        new StoreNotFoundException("Store not found with id : " + id));

        store.setActive(false);

        storeRepository.save(store);
    }

    private Store mapToEntity(StoreRequestDto dto) {

        Store store = new Store();

        store.setName(dto.getName());
        store.setAddress(dto.getAddress());
        store.setCity(dto.getCity());
        store.setPhoneNumber(dto.getPhoneNumber());
        store.setEmail(dto.getEmail());

        return store;
    }

    private StoreResponseDto mapToResponse(Store store) {

        return new StoreResponseDto(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getCity(),
                store.getPhoneNumber(),
                store.getEmail(),
                store.getActive()
        );
    }

}
