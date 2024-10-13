package com.example.demo.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Entity.Article;
import com.example.demo.Repository.ArticleRepository;
import com.example.demo.Service.ArticleService;

class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository; // Simuler le repository

    @InjectMocks
    private ArticleService articleService; // Service à tester

    private Article article;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks

        // Créer un article simulé
        article = new Article();
        article.setId(1L);
        article.setCodeArticle("A001");
        article.setPrixUnitaire(50.0);
    }

    @Test
    void testFindById() {
        // Simuler le comportement du repository
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        // Appeler la méthode du service
        Optional<Article> foundArticle = articleService.findById(1L);

        // Vérifier le résultat
        assertTrue(foundArticle.isPresent());
        assertEquals("A001", foundArticle.get().getCodeArticle());
        assertEquals(50.0, foundArticle.get().getPrixUnitaire());

        // Vérifier que la méthode du repository a été appelée
        verify(articleRepository).findById(1L);
    }

    @Test
    void testSaveArticle() {
        // Simuler la sauvegarde de l'article
        when(articleRepository.save(article)).thenReturn(article);

        // Appeler la méthode du service
        Article savedArticle = articleService.save(article);

        // Vérifier que l'article sauvegardé est correct
        assertNotNull(savedArticle);
        assertEquals(1L, savedArticle.getId());
        assertEquals("A001", savedArticle.getCodeArticle());

        // Vérifier que la méthode du repository a été appelée
        verify(articleRepository).save(article);
    }

    @Test
    void testUpdateArticle() {
        Article updatedArticle = new Article();
        updatedArticle.setPrixUnitaire(100.0);

        // Simuler la recherche d'article
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        // Simuler la sauvegarde après modification
        when(articleRepository.save(article)).thenReturn(article);

        // Appeler la méthode du service pour mettre à jour
        Article result = articleService.updateArticle(1L, updatedArticle);

        // Vérifier que la mise à jour a bien été effectuée
        assertEquals(100.0, result.getPrixUnitaire());

        // Vérifier que les méthodes du repository ont été appelées
        verify(articleRepository).findById(1L);
        verify(articleRepository).save(article);
    }
}

