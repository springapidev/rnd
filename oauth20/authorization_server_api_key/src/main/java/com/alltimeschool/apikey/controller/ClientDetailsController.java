package com.alltimeschool.apikey.controller;

import com.alltimeschool.apikey.entity.ClientDetails;
import com.alltimeschool.apikey.service.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;

    @Autowired
    public ClientDetailsController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    // Create or Update a ClientDetails
    @PostMapping
    public ResponseEntity<ClientDetails> createOrUpdateClientDetails(@RequestBody ClientDetails clientDetails) {
        ClientDetails savedClientDetails = clientDetailsService.saveClientDetails(clientDetails);
        return new ResponseEntity<>(savedClientDetails, HttpStatus.CREATED);
    }

    // Get ClientDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDetails> getClientDetailsById(@PathVariable String id) {
        Optional<ClientDetails> clientDetails = clientDetailsService.getClientDetailsById(id);
        return clientDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get ClientDetails by clientId
    @GetMapping("/clientId/{clientId}")
    public ResponseEntity<ClientDetails> getClientDetailsByClientId(@PathVariable String clientId) {
        Optional<ClientDetails> clientDetails = clientDetailsService.getClientDetailsByClientId(clientId);
        return clientDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all ClientDetails
    @GetMapping
    public List<ClientDetails> getAllClientDetails() {
        return clientDetailsService.getAllClientDetails();
    }

    // Delete a ClientDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientDetails(@PathVariable String id) {
        clientDetailsService.deleteClientDetails(id);
        return ResponseEntity.noContent().build();
    }
}
