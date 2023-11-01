package com.example.demo.Repository;

import com.example.demo.Model.Carte;
import com.example.demo.Model.Pokemon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    @Query("SELECT c FROM Carte c WHERE c.id LIKE %:set% AND c.rarity = :rarity")
    ArrayList<Carte> findCartesByRarityAndSet(
            @Param("rarity") String rarity,
            @Param("set") String set
    );
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
