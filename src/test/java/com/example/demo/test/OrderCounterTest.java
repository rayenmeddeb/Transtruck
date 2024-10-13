package com.example.demo.test;



import org.junit.jupiter.api.Test;

import com.example.demo.Entity.OrderCounter;

import static org.junit.jupiter.api.Assertions.*;

public class OrderCounterTest {

    @Test
    public void testOrderCounter() {
        // Création d'une instance de OrderCounter
        OrderCounter orderCounter = new OrderCounter();

        // Vérifie que l'id est null par défaut
        assertNull(orderCounter.getId());

        // Définit l'ID
        orderCounter.setId(1L);
        assertEquals(1L, orderCounter.getId());

        // Vérifie la valeur actuelle
        assertEquals(0, orderCounter.getCurrentValue());

        // Définit une nouvelle valeur actuelle
        orderCounter.setCurrentValue(5);
        assertEquals(5, orderCounter.getCurrentValue());
    }
}

