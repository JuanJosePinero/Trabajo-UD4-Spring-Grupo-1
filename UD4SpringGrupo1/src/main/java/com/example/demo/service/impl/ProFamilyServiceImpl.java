package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProFamily;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.service.ProFamilyService;

@Service
public class ProFamilyServiceImpl implements ProFamilyService {

    private final ProFamilyRepository proFamilyRepository;

    @Autowired
    public ProFamilyServiceImpl(ProFamilyRepository proFamilyRepository) {
        this.proFamilyRepository = proFamilyRepository;
    }

    @Override
    public List<ProFamily> getAllProfesionalFamilies() {
        return proFamilyRepository.findAll();
    }

    @Override
    public ProFamily getProFamilyById(int id) {
        Optional<ProFamily> optionalProFamily = proFamilyRepository.findById(id);
        return optionalProFamily.orElse(null);
    }

    @Override
    public ProFamily saveProFamily(ProFamily proFamily) {
        return proFamilyRepository.save(proFamily);
    }

    @Override
    public void deleteProFamily(int id) {
        proFamilyRepository.deleteById(id);
    }
}
