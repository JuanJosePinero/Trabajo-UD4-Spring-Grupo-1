package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Report;
import com.example.demo.model.ReportModel;

public interface ReportService {

	List<Report> findReportsByProFamily(String familyName);

	Report addReport(ReportModel reportModel);

}
