package com.example.demo.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Entity.Ordre;
import com.example.demo.Service.PlaFileService;

class PlaFileServiceTest {

    @InjectMocks
    private PlaFileService plaFileService;

    private Ordre ordre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Initialiser un Ordre avec des valeurs fictives
        ordre = new Ordre();
        ordre.setClient("Client Test");
        ordre.setNomclient("Nom Client Test");
        ordre.setSiteclient("Site Client Test");
        ordre.setIdedi("12345");
        ordre.setLivraisonNom("Livraison Test");
        ordre.setCodeclientliv("Client Liv");
        ordre.setLivraisonAdr1("Adresse de Livraison");
        ordre.setCodepostalliv("12345");
        ordre.setLivraisonVille("Ville Test");
        ordre.setOrderNumber("0000001");
        ordre.setDateSaisie(new Date());
        ordre.setLivraisonDate(new Date());
        ordre.setChargementDate(new Date());
        ordre.setNombrePalettes(5);
        ordre.setNombreColis(10);
        ordre.setVolume(100.0);
        ordre.setCommentaires(new HashSet<>(Arrays.asList("Commentaire 1", "Commentaire 2")));
        ordre.setCodeArticle("ART123");
    }

    @Test
    void testGeneratePlaFile() throws IOException {
        // Supprimer le fichier s'il existe déjà
        Path filePath = Path.of("C:/Users/rayen/Desktop/PLA/EDIDIVERS_" + plaFileService.formatDate(ordre.getDateSaisie()) + ".txt");
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        // Appeler la méthode pour générer le fichier
        plaFileService.generatePlaFile(ordre);

        // Vérifier que le fichier a été créé
        assertTrue(Files.exists(filePath));

        // Optionnel : Lire le contenu du fichier et vérifier des parties du texte
        String content = Files.readString(filePath);
        assertTrue(content.contains("Client Test"));
        assertTrue(content.contains("Nom Client Test"));
       
        

        // Nettoyage : supprimer le fichier après le test
        Files.delete(filePath);
    }
}
