package com.example.demo.Repository;

import com.example.demo.Model.Carte;
import com.example.demo.Model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

    List<Type> findByNom(String nom);

    @Query("SELECT t.nom FROM Type t")
    ArrayList<String> findNoms();


}
