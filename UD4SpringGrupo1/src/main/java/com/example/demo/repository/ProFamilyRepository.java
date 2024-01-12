package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProFamily;

@Repository("proFamilyRepository")
public interface ProFamilyRepository extends JpaRepository<ProFamily, Serializable> {
    ProFamily findByName(String name);
    ProFamily findById(int proFamilyId);
    List<ProFamily> findAll();
}

