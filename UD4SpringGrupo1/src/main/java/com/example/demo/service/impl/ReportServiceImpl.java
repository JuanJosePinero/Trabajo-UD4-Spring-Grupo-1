package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ReportModel;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.ReportService;

@Configuration
@Service("reportService")
public class ReportServiceImpl implements ReportService{
	
	private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    
    private Report model2entity(ReportModel reportModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(reportModel, Report.class);
	}

	private ReportModel entity2model(Report report) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(report, ReportModel.class);
	}
	
	@Override
	public List<Report> findReportsByProFamily(String familyName) {
	    List<Report> reports = new ArrayList<>();

	    for (Report report : reportRepository.findAll()) {
	        Servicio servicio = report.getServicioId();
	        if (servicio != null && servicio.getProfesionalFamilyId() != null && servicio.getProfesionalFamilyId().getName().equals(familyName)) {
	            reports.add(report);
	        }
	    }
	    System.out.println(reports);
	    
	    return reports;
	}
	
	@Override
	public Report addReport(ReportModel reportModel) {
		Report report = model2entity(reportModel);
		report.setReport(reportModel.getReport());
		report.setServiceTime(reportModel.getServiceTime());
		report.setServicioId(reportModel.getServicioId());
		report.setStudentId(reportModel.getStudentId());
		return reportRepository.save(report);
	}


}
