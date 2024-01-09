package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Report;
import com.example.demo.model.ReportModel;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.ReportService;

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

}
