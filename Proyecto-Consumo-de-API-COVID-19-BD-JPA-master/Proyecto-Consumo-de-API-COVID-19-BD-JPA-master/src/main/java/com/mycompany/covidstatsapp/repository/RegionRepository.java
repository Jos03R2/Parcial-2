package com.mycompany.covidstatsapp.repository;

import com.mycompany.covidstatsapp.model.Region;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.List;

public class RegionRepository {

    private final EntityManager entityManager;

    public RegionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Region region) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(region);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Region> findAll() {
        return entityManager.createQuery("SELECT r FROM Region r", Region.class).getResultList();
    }
  
    public boolean existsByIsoAndName(String iso, String name) {
    Region region = entityManager.createQuery(
        "SELECT r FROM Region r WHERE r.iso = :iso AND r.name = :name", Region.class)
        .setParameter("iso", iso)
        .setParameter("name", name)
        .getResultStream()
        .findFirst()
        .orElse(null);

    return region != null;
}
}