package com.example.demo.service;

import com.example.demo.entity.ProFamily;

import java.util.List;

public interface ProFamilyService {

    List<ProFamily> getAllProfesionalFamilies();

    ProFamily getProFamilyById(int id);

    ProFamily saveProFamily(ProFamily proFamily);

    void deleteProFamily(int id);
}
