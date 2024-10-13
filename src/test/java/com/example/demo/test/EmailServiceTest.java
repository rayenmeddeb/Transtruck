package com.example.demo.test;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.Service.EmailService;

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender; // Simuler l'envoi d'e-mails

    @InjectMocks
    private EmailService emailService; // Service à tester

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    @Test
    void testSendEmail() {
        // Données de test
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String text = "This is a test email.";

        // Appeler la méthode du service
        emailService.sendEmail(to, subject, text);

        // Vérifier que la méthode send a été appelée sur mailSender
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        // Vérifier que le message contient les bonnes informations
        // (tu peux aussi vérifier si le message a été correctement configuré)
        SimpleMailMessage messageArgument = captureArgument();
        assertEquals(to, messageArgument.getTo()[0]);
        assertEquals(subject, messageArgument.getSubject());
        assertEquals(text, messageArgument.getText());
    }

    private SimpleMailMessage captureArgument() {
        // Pour capturer l'argument envoyé à mailSender.send()
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());
        return messageCaptor.getValue();
    }
}
