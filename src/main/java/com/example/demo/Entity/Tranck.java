package com.example.demo.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity

public class Tranck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private Date departureDateTime;
    private Boolean depart;
    
    private Date loadingDateD;
    private Date loadingDateF;
  
 
    private Boolean chargement;
  
    private Date deliveryDateD;
    private Date deliveryDateF;
 
    private Boolean livraison;
    
    
    @OneToOne(mappedBy = "trancking")
    @JsonIgnore 
    private Ordre ordre;
}
