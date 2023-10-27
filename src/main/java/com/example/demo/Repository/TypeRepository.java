package com.example.demo.Repository;

import com.example.demo.Model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

    List<Type> findByNom(String nom);
}
