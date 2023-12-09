package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.ProFamilyModel;

public interface ProFamilyService {

    List<ProFamily> getAllProfesionalFamilies();

    ProFamily getProFamilyById(int id);

    ProFamily saveProFamily(ProFamily proFamily);

    int deleteProFamily(int id);

	ProFamily updateProFamily(ProFamily proFamily);
}
