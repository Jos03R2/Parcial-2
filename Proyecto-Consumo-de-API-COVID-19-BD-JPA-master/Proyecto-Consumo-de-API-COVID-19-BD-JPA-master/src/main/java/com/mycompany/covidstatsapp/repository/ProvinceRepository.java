package com.mycompany.covidstatsapp.repository;

import com.mycompany.covidstatsapp.model.Province;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProvinceRepository {

    private final EntityManager entityManager;

    public ProvinceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Province province) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(province);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Province> findAll() {
        return entityManager.createQuery("SELECT p FROM Province p", Province.class).getResultList();
    }

    
    public boolean existsByIsoAndProvince(String iso, String province) {
        String jpql = "SELECT COUNT(p) FROM Province p WHERE p.iso = :iso AND p.province = :province";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("iso", iso);
        query.setParameter("province", province);
        
        Long count = query.getSingleResult();
        return count > 0;
    }
}