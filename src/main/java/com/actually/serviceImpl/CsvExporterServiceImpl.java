package com.actually.serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actually.dao.DataRepo;
import com.actually.model.DataModelOne;
import com.actually.service.CsvExporterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class CsvExporterServiceImpl implements CsvExporterService {

	@Autowired
	DataRepo dataRepo;

	@Override
	public void createOpenCsv() {
		// Get Data From DB using Repository
		List<DataModelOne> dataList = dataRepo.findAll();
		// Create a File
		String filePath = "src\\csv\\opencsv.csv";
		File csvFile = new File(filePath);
		csvFile.getParentFile().mkdirs();
		// Write to File
		try {
			Writer writer = new FileWriter(csvFile);
			StatefulBeanToCsvBuilder<DataModelOne> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<DataModelOne> beanWriter = builder.build();
			beanWriter.write(dataList);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createCommonCsv() {
		boolean isAppend = false;
		// Get Data From DB using Repository
		List<DataModelOne> dataList = dataRepo.findAll();
		// Create a File
		String filePath = "src\\csv\\commoncsv.csv";
		File csvFile = new File(filePath);
		csvFile.getParentFile().mkdirs();

		// Write headers to csv file
		List<String> headers = List.of("ID", "NAME");
		if (!isAppend) {
			writeCsvHeaderToFile(csvFile, isAppend, headers);
		}
		isAppend = true;
		for (DataModelOne data : dataList) {
			writeCsvDataToFile(csvFile, isAppend, data);
		}
	}

	@Override
	public void createJson() {
		boolean isAppend=false;
		List<DataModelOne> dataList = dataRepo.findAll();
		// Create a File
		String filePath = "src\\json\\file.json";
		File jsonFile = new File(filePath);
		jsonFile.getParentFile().mkdirs();
		ObjectMapper mapper=new ObjectMapper();
		try {
			String json=mapper.writeValueAsString(dataList);
			//json=json.replace("[","").replace("]", "");
			//String symbol=isAppend ? "," : "[";
			String symbol="";
			writeJsonToFile(jsonFile, isAppend, symbol+json);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}

	private boolean writeCsvHeaderToFile(File file, boolean isAppend, List<String> data) {
		try {
			FileWriter fileWriter = new FileWriter(file, isAppend);
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			csvPrinter.printRecord(data);
			fileWriter.flush();
			fileWriter.close();
			csvPrinter.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean writeCsvDataToFile(File file, boolean isAppend, DataModelOne data) {
		try {
			FileWriter fileWriter = new FileWriter(file, isAppend);
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			csvPrinter.printRecord(data.getId(), data.getName());
			fileWriter.flush();
			fileWriter.close();
			csvPrinter.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean writeJsonToFile(File file,boolean isAppend,String data) {
		try {
			FileWriter fileWriter = new FileWriter(file, isAppend);
			fileWriter.write(data);
			fileWriter.flush();
			fileWriter.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}