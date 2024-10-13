package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AddressRepository;
import com.example.model.AddressEntity;
import com.example.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<AddressEntity> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public AddressEntity createAddress(AddressEntity address) {
        return addressRepository.save(address);
    }

    @Override
    public AddressEntity updateAddress(Long id, AddressEntity addressDetails) {
        Optional<AddressEntity> address = addressRepository.findById(id);
        if (address.isPresent()) {
            AddressEntity existingAddress = address.get();
            existingAddress.setStreet(addressDetails.getStreet());
            existingAddress.setCity(addressDetails.getCity());
            existingAddress.setState(addressDetails.getState());
            existingAddress.setZipCode(addressDetails.getZipCode());
            existingAddress.setCountry(addressDetails.getCountry());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}