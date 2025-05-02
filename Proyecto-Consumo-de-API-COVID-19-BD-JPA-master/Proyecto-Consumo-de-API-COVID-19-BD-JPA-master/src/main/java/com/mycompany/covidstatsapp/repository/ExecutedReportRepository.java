/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.covidstatsapp.repository;

import com.mycompany.covidstatsapp.model.ExecutedReport;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExecutedReportRepository {

    private final EntityManager entityManager;

    public ExecutedReportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean hasExecuted(String executionDate, String countryIso) {
        String jpql = "SELECT COUNT(e) FROM ExecutedReport e WHERE e.executionDate = :date AND e.countryIso = :iso";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("date", executionDate);
        query.setParameter("iso", countryIso);
        Long count = query.getSingleResult();
        return count > 0;
    }

    public void save(ExecutedReport executedReport) {
        var tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(executedReport);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}