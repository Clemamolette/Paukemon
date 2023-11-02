package com.example.demo.Repository;

import com.example.demo.Model.Carte;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MesCartesRepository extends JpaRepository<Carte, String>{
    @Query("SELECT c FROM Carte c WHERE c.acquired = true")
    ArrayList<Carte> findAcquired();
    @Query("SELECT c.quantity FROM Carte c WHERE c.id = :id")
    Integer findQuantityByID(
            @Param("id") String id
    );
    @Query("SELECT c.acquired FROM Carte c WHERE c.id = :id")
    Boolean findAcquiredByID(
            @Param("id") String id
    );
    @Query("SELECT DISTINCT c.type FROM Carte c")
    ArrayList<String> getTypes();
    @Query("SELECT c FROM Carte c WHERE c.serie = :serie AND c.rarity = :rarity")
    ArrayList<Carte> findCartesByRarityAndSet(
            @Param("rarity") String rarity,
            @Param("serie") String serie
    );
    @Query("SELECT c FROM Carte c ORDER BY c.id")
    ArrayList<Carte> orderByID();
    @Query("SELECT c FROM Carte c ORDER BY c.name")
    ArrayList<Carte> orderByName();
    @Query("SELECT c FROM Carte c ORDER BY c.hp")
    ArrayList<Carte> orderByHP();
    @Query("SELECT c FROM Carte c ORDER BY c.quantity DESC")
    ArrayList<Carte> orderByQuantity();
    @Modifying
    @Transactional
    @Query("UPDATE Carte c SET c.quantity = :quantity WHERE c.id = :id")
    void updateQuantityById(
            @Param("id") String id,
            @Param("quantity") Integer quantity
    );
    @Modifying
    @Transactional
    @Query("UPDATE Carte c SET c.acquired = true WHERE c.id = :id")
    void updateAcquired(
            @Param("id") String id
    );
}
