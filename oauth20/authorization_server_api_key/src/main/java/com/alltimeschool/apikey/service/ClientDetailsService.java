package com.alltimeschool.apikey.service;


import com.alltimeschool.apikey.entity.ClientDetails;
import com.alltimeschool.apikey.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


    public interface ClientDetailsService {



        // Create or Update a ClientDetails
        public ClientDetails saveClientDetails(ClientDetails clientDetails);

        // Get a ClientDetails by ID
        public Optional<ClientDetails> getClientDetailsById(String id);

        // Get a ClientDetails by clientId
        public Optional<ClientDetails> getClientDetailsByClientId(String clientId);

        // Get all ClientDetails
        public List<ClientDetails> getAllClientDetails();

        // Delete a ClientDetails by ID
        public void deleteClientDetails(String id);
    }


