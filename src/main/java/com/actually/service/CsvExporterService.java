package com.actually.service;

import org.springframework.stereotype.Service;

@Service
public interface CsvExporterService {

	void createOpenCsv();

	void createCommonCsv();

	void createJson();

}
