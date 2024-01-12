package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ReportModel;

public interface ReportService {

	List<Report> findReportsByServiceProFamily(String servicioProFamily);

	Report addReport(ReportModel reportModel);

}
