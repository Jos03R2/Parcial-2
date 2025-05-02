package com.mycompany.covidstatsapp.repository;

import com.mycompany.covidstatsapp.model.Report;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ReportRepository {

    private final EntityManager entityManager;

    public ReportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Report report) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(report);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Report> findAll() {
        return entityManager.createQuery("SELECT r FROM Report r", Report.class).getResultList();
    }
     
    public List<Report> findByDateAndIso(String date, String iso) {
    String jpql = "SELECT r FROM Report r WHERE r.date = :date AND r.iso = :iso";
    Query query = entityManager.createQuery(jpql, Report.class);
    query.setParameter("date", date);
    query.setParameter("iso", iso);

    return query.getResultList();
}

    
    public boolean existsByDateAndProvince(String date, String province) {
        String jpql = "SELECT COUNT(r) FROM Report r WHERE r.date = :date AND r.province = :province";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("date", date);
        query.setParameter("province", province);

        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

}