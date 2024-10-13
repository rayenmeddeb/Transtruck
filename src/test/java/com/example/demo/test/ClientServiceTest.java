package com.example.demo.test;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Entity.Client;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Service.ClientService;

import java.util.ArrayList;
import java.util.List;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository; // Simuler le repository

    @InjectMocks
    private ClientService clientService; // Service à tester

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks

        // Créer un client simulé
        client = new Client();
        client.setCode(1L);
        client.setCodeclient("C001");
        client.setEmail("client@example.com");
        client.setTelephone("0123456789");
        client.setNom("Client Test");
    }

    @Test
    void testFindAll() {
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        
        // Simuler le comportement du repository
        when(clientRepository.findAll()).thenReturn(clients);

        // Appeler la méthode du service
        List<Client> foundClients = clientService.findAll();

        // Vérifier le résultat
        assertEquals(1, foundClients.size());
        assertEquals("C001", foundClients.get(0).getCodeclient());

        // Vérifier que la méthode du repository a été appelée
        verify(clientRepository).findAll();
    }

    @Test
    void testFindByCode() {
        // Simuler le comportement du repository
        when(clientRepository.findByCodeclient("C001")).thenReturn(Optional.of(client));

        // Appeler la méthode du service
        Optional<Client> foundClient = clientService.findbycode("C001");

        // Vérifier le résultat
        assertTrue(foundClient.isPresent());
        assertEquals("client@example.com", foundClient.get().getEmail());

        // Vérifier que la méthode du repository a été appelée
        verify(clientRepository).findByCodeclient("C001");
    }

    @Test
    void testAfficherEmail() {
        // Simuler le comportement du repository
        when(clientRepository.findByCodeclient("C001")).thenReturn(Optional.of(client));

        // Appeler la méthode du service
        String email = clientService.afficheremail("C001");

        // Vérifier le résultat
        assertEquals("client@example.com", email);

        // Vérifier que la méthode du repository a été appelée
        verify(clientRepository).findByCodeclient("C001");
    }

    @Test
    void testSaveClient() {
        // Simuler la sauvegarde du client
        when(clientRepository.save(client)).thenReturn(client);

        // Appeler la méthode du service
        Client savedClient = clientService.save(client);

        // Vérifier que le client sauvegardé est correct
        assertNotNull(savedClient);
        assertEquals("C001", savedClient.getCodeclient());

        // Vérifier que la méthode du repository a été appelée
        verify(clientRepository).save(client);
    }

    @Test
    void testUpdateClient() {
        Client updatedClient = new Client();
        updatedClient.setNom("Client Updated");

        // Simuler la recherche d'un client
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        // Simuler la sauvegarde après modification
        when(clientRepository.save(client)).thenReturn(client);

        // Appeler la méthode du service pour mettre à jour
        Client result = clientService.updateClient(1L, updatedClient);

        // Vérifier que la mise à jour a bien été effectuée
        assertEquals("Client Updated", result.getNom());

        // Vérifier que les méthodes du repository ont été appelées
        verify(clientRepository).findById(1L);
        verify(clientRepository).save(client);
    }

    @Test
    void testDeleteClient() {
        // Appeler la méthode du service pour supprimer
        clientService.deleteById(1L);

        // Vérifier que la méthode du repository a été appelée
        verify(clientRepository).deleteById(1L);
    }
}

