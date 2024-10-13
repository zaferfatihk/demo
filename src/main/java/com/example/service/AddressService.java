package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.AddressEntity;

public interface AddressService {
    List<AddressEntity> getAllAddresses();
    Optional<AddressEntity> getAddressById(Long id);
    AddressEntity createAddress(AddressEntity address);
    AddressEntity updateAddress(Long id, AddressEntity addressDetails);
    void deleteAddress(Long id);
}