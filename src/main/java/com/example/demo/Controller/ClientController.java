package com.example.demo.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Client;
import com.example.demo.Service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }
    
    
    
    @GetMapping("/email/{id}")
    public String afficheremail(@PathVariable String id) {
        return clientService.afficheremail(id);
    }
    
    
    @GetMapping("/telephone/{id}")
    public String affichertelephone(@PathVariable String id) {
        return clientService.affichertelephone(id);
    }
    
    

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }
    
    
    @GetMapping("/code/{id}")
    public Optional<Client> getClientBycode(@PathVariable String id) {
        return clientService.findbycode(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.updateClient(id, clientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }
    
    
    @GetMapping("/count")
    public long countOrders() {
        return clientService.countAllclients();
    }
}