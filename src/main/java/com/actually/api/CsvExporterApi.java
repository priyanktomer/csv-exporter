package com.actually.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actually.service.CsvExporterService;

@RestController
public class CsvExporterApi {
	
	@Autowired
	CsvExporterService csvExporterService;

	
	@GetMapping("welcome")
	public String welcome() {
		return "Welcoming you.";
	}
	
	@GetMapping("/create-open-csv")
	public void createOpenCsv() {
		csvExporterService.createOpenCsv();
	}
	
	@GetMapping("/create-common-csv")
	public void createCommonCsv() {
		csvExporterService.createCommonCsv();
	}
	
	@GetMapping("/create-json")
	public void createJson() {
		csvExporterService.createJson();
	}
}
