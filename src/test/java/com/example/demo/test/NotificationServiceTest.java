package com.example.demo.test;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Entity.Notification;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Service.NotificationService;

import java.util.ArrayList;
import java.util.List;

class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository; // Simuler le repository

    @InjectMocks
    private NotificationService notificationService; // Service à tester

    private Notification notification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks

        // Créer une notification simulée
        notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Test Notification");
        notification.setType("INFO");
        notification.setRead(false);
    }

    @Test
    void testGetAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification);

        // Simuler le comportement du repository
        when(notificationRepository.findAll()).thenReturn(notifications);

        // Appeler la méthode du service
        List<Notification> foundNotifications = notificationService.getAllNotifications();

        // Vérifier le résultat
        assertEquals(1, foundNotifications.size());
        assertEquals("Test Notification", foundNotifications.get(0).getMessage());

        // Vérifier que la méthode du repository a été appelée
        verify(notificationRepository).findAll();
    }

    @Test
    void testGetNotificationById() {
        // Simuler le comportement du repository
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        // Appeler la méthode du service
        Optional<Notification> foundNotification = notificationService.getNotificationById(1L);

        // Vérifier le résultat
        assertTrue(foundNotification.isPresent());
        assertEquals("Test Notification", foundNotification.get().getMessage());

        // Vérifier que la méthode du repository a été appelée
        verify(notificationRepository).findById(1L);
    }

    @Test
    void testCreateNotification() {
        // Simuler la création d'une notification
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Appeler la méthode du service
        Notification createdNotification = notificationService.createNotification(notification);

        // Vérifier que la notification créée est correcte
        assertNotNull(createdNotification);
        assertEquals("Test Notification", createdNotification.getMessage());

        // Vérifier que la méthode du repository a été appelée
        verify(notificationRepository).save(notification);
    }

    @Test
    void testUpdateNotification() {
        Notification updatedNotification = new Notification();
        updatedNotification.setMessage("Updated Notification");
        updatedNotification.setType("WARNING");
        updatedNotification.setRead(true);

        // Simuler le comportement du repository
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Appeler la méthode du service pour mettre à jour
        Notification result = notificationService.updateNotification(1L, updatedNotification);

        // Vérifier que la mise à jour a bien été effectuée
        assertNotNull(result);
        assertEquals("Updated Notification", result.getMessage());

        // Vérifier que les méthodes du repository ont été appelées
        verify(notificationRepository).findById(1L);
        verify(notificationRepository).save(notification);
    }

    @Test
    void testDeleteNotification() {
        // Simuler le comportement du repository
        when(notificationRepository.existsById(1L)).thenReturn(true);

        // Appeler la méthode du service pour supprimer
        boolean deleted = notificationService.deleteNotification(1L);

        // Vérifier que la suppression a été effectuée
        assertTrue(deleted);

        // Vérifier que la méthode du repository a été appelée
        verify(notificationRepository).deleteById(1L);
    }

    @Test
    void testDeleteNotificationNotFound() {
        // Simuler le comportement du repository
        when(notificationRepository.existsById(1L)).thenReturn(false);

        // Appeler la méthode du service pour supprimer
        boolean deleted = notificationService.deleteNotification(1L);

        // Vérifier que la suppression a échoué
        assertFalse(deleted);

        // Vérifier que la méthode du repository n'a pas été appelée
        verify(notificationRepository, never()).deleteById(1L);
    }
}
