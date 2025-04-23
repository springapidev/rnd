package com.alltimeschool.apikey.service;

import com.alltimeschool.apikey.entity.ClientDetails;
import com.alltimeschool.apikey.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;


    @Override
    public ClientDetails saveClientDetails(ClientDetails clientDetails) {
        return clientDetailsRepository.save(clientDetails);
    }

    @Override
    public Optional<ClientDetails> getClientDetailsById(String id) {
        Optional<ClientDetails> clientDetailsOptional = this.clientDetailsRepository.findById(id);
        return clientDetailsOptional;
    }

    @Override
    public Optional<ClientDetails> getClientDetailsByClientId(String clientId) {
        Optional<ClientDetails> clientDetailsOptional = this.clientDetailsRepository.findByClientId(clientId);
        return clientDetailsOptional;
    }

    @Override
    public List<ClientDetails> getAllClientDetails() {
        return this.clientDetailsRepository.findAll();
    }

    @Override
    public void deleteClientDetails(String id) {
        this.clientDetailsRepository.deleteById(id);
    }
}
