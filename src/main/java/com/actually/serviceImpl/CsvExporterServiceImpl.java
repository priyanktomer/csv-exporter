package com.actually.serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actually.dao.DataRepo;
import com.actually.model.DataModelOne;
import com.actually.service.CsvExporterService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class CsvExporterServiceImpl implements CsvExporterService{
	
	@Autowired
	DataRepo dataRepo;

	@Override
	public void createOpenCsv() {
		//Get Data From DB using Repository
		List<DataModelOne> dataList=dataRepo.findAll();
		//Create a File
		String filePath="src\\csv\\opencsv.csv";
		File csvFile=new File(filePath);
		csvFile.getParentFile().mkdirs();
		//Write to File
		try {
			Writer writer=new FileWriter(csvFile);
			StatefulBeanToCsvBuilder<DataModelOne> builder=new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<DataModelOne> beanWriter=builder.build();
			beanWriter.write(dataList);
			writer.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void createCommonCsv() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createJson() {
		// TODO Auto-generated method stub
		
	}

	private boolean writeCsvHeader(File file,boolean isAppend,DataModelOne data) {
		try {
			FileWriter fileWriter=new FileWriter(file);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
}