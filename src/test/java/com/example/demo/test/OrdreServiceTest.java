package com.example.demo.test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Entity.Ordre;
import com.example.demo.Entity.Statut;
import com.example.demo.Entity.Client;
import com.example.demo.Entity.OrderCounter;
import com.example.demo.Entity.Tranck;
import com.example.demo.Repository.OrderCounterRepository;
import com.example.demo.Repository.OrdreRepository;
import com.example.demo.Repository.TranckRepository;
import com.example.demo.Service.OrdreService;

import java.util.ArrayList;
import java.util.List;

class OrdreServiceTest {

    @Mock
    private OrdreRepository ordreRepository;

    @Mock
    private OrderCounterRepository orderCounterRepository;

    @Mock
    private TranckRepository tranckRepository;

    @InjectMocks
    private OrdreService ordreService;

    private Ordre ordre;
    private OrderCounter orderCounter;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialiser une instance d'Ordre
        ordre = new Ordre();
        ordre.setId(1L);
        ordre.setStatut(Statut.NON_CONFIRME);

        // Initialiser un compteur d'ordre
        orderCounter = new OrderCounter();
        orderCounter.setCurrentValue(0);
    }

    @Test
    void testFindAll() {
        List<Ordre> ordres = new ArrayList<>();
        ordres.add(ordre);

        when(ordreRepository.findAll()).thenReturn(ordres);

        List<Ordre> foundOrdres = ordreService.findAll();

        assertEquals(1, foundOrdres.size());
        assertEquals(ordre.getId(), foundOrdres.get(0).getId());

        verify(ordreRepository).findAll();
    }

    @Test
    void testFindById() {
        when(ordreRepository.findById(1L)).thenReturn(Optional.of(ordre));

        Optional<Ordre> foundOrdre = ordreService.findById(1L);

        assertTrue(foundOrdre.isPresent());
        assertEquals(ordre.getId(), foundOrdre.get().getId());

        verify(ordreRepository).findById(1L);
    }

    @Test
    void testSave() {
        when(orderCounterRepository.findAll()).thenReturn(List.of(orderCounter));
        when(tranckRepository.save(any(Tranck.class))).thenReturn(new Tranck());
        when(ordreRepository.save(any(Ordre.class))).thenReturn(ordre);

        Ordre savedOrdre = ordreService.save(ordre);

        assertNotNull(savedOrdre);
        assertEquals(Statut.NON_CONFIRME, savedOrdre.getStatut());

        verify(orderCounterRepository).findAll();
        verify(tranckRepository).save(any(Tranck.class));
        verify(ordreRepository).save(ordre);
    }

    @Test
    void testDeleteById() {
        doNothing().when(ordreRepository).deleteById(1L);

        ordreService.deleteById(1L);

        verify(ordreRepository).deleteById(1L);
    }

    @Test
    void testConfirmer() {
        when(ordreRepository.findById(1L)).thenReturn(Optional.of(ordre));
        when(ordreRepository.save(any(Ordre.class))).thenReturn(ordre);

        Ordre updatedOrdre = ordreService.confirmer(1L);

        assertNotNull(updatedOrdre);
        assertEquals(Statut.NON_PLANIFIE, updatedOrdre.getStatut());

        verify(ordreRepository).findById(1L);
        verify(ordreRepository).save(ordre);
    }

   
    @Test
    void testCountAllOrders() {
        when(ordreRepository.count()).thenReturn(10L);

        long count = ordreService.countAllOrders();

        assertEquals(10L, count);
        verify(ordreRepository).count();
    }

    // Ajoute d'autres tests pour les méthodes de comptage spécifiques si nécessaire.
}

