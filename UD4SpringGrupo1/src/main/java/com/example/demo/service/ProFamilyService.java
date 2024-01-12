package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.ProFamilyModel;

public interface ProFamilyService {

    ProFamily saveProFamily(ProFamily proFamily);

    int deleteProFamily(int id);

	ProFamily updateProFamily(ProFamily proFamily);
	
	ProFamily addProFamily(ProFamilyModel proFamilyModel);
	
	ProFamily findById(int id);
	
	List<ProFamily>getAll();
}
