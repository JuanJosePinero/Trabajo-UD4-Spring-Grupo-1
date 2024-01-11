package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Report;

public interface ReportService {

	List<Report> findReportsByProFamily(String familyName);

}
