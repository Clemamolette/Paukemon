package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

    List<Pokemon> findByNom(String nom);
}
