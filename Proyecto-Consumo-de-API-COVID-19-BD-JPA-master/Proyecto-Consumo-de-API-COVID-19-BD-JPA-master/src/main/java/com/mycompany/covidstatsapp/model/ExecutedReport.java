/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tatto
 */

package com.mycompany.covidstatsapp.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "executed_reports",
    uniqueConstraints = @UniqueConstraint(columnNames = {"execution_date", "country_iso"})
)
@Getter
@Setter
public class ExecutedReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "execution_date", nullable = false, length = 10)
    private String executionDate;  

    @Column(name = "country_iso", nullable = false, length = 10)
    private String countryIso;
}