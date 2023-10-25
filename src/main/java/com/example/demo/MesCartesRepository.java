package com.example.demo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesCartesRepository extends JpaRepository<Carte, String>{

    List<Pokemon> findByName(String nom);

    @Query ("SELECT id FROM Carte")
    List<String> findIDS();
    @Query("SELECT c.quantity FROM Carte c WHERE c.id = :id")
    Integer findQuantityByID(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Carte c SET c.quantity = :quantity WHERE c.id = :id")
    void updateQuantityById(@Param("id") String id, @Param("quantity") Integer quantity);
}
