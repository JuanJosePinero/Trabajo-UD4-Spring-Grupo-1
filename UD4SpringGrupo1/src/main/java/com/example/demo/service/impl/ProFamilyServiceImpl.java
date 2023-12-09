package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Student;
import com.example.demo.model.ProFamilyModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.service.ProFamilyService;

@Configuration
@Service("proFamilyService")
public class ProFamilyServiceImpl implements ProFamilyService {

    private final ProFamilyRepository proFamilyRepository;

    @Autowired
    public ProFamilyServiceImpl(ProFamilyRepository proFamilyRepository) {
        this.proFamilyRepository = proFamilyRepository;
    }
    
    private ProFamily model2entity(ProFamilyModel proFamilyModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(proFamilyModel, ProFamily.class);
	}

	private ProFamilyModel entity2model(ProFamily proFamily) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(proFamily, ProFamilyModel.class);
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
	public int deleteProFamily(int id) {
		Optional<ProFamily> optionalProFamily = proFamilyRepository.findById(id);

        if (optionalProFamily.isPresent()) {
        	ProFamily proFamily = optionalProFamily.get();
        	proFamily.setDeleted(1);
            proFamilyRepository.save(proFamily);
            return 1;
        } else
            return 0;
    }

    @Override
    public ProFamily updateProFamily(ProFamily proFamilyM) {
        ProFamily proFamily = proFamilyRepository.findById(proFamilyM.getId())
                .orElseThrow(() -> new RuntimeException("Profesional Family not found"));

        // Actualiza los campos necesarios, por ejemplo, el nombre
        proFamily.setName(proFamilyM.getName());

        // Guarda el ProFamily actualizado en el repositorio
        return proFamilyRepository.save(proFamily);
    }

	@Override
	public ProFamily addProFamily(ProFamilyModel proFamilyModel) {
		ProFamily proFamily = model2entity(proFamilyModel);
		proFamily.setName(proFamilyModel.getName());
		return proFamilyRepository.save(proFamily);
	}

}
