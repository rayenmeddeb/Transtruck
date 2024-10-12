package com.example.demo.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity

public class Article {
	  
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String codeArticle;
	    private String Label;
	    private String type;
	    private int typeDeMarchandise;
	    private String typeDeRemorque;
	    private String unite;
	    private double quantiteMinimum;
	    private double prixUnitaire;
	    
	    private double vente;
	    private double achat;

}
