package com.qa.opencart.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Testing {

	public Object[][] csvData() throws FileNotFoundException{
		
		String csvFile="./src/test/resources/testData/data.csv";
		CSVReader reader=new CSVReader(new FileReader(csvFile));
		
		List<String[]> rows=new ArrayList<String[]>();
		try {
		 rows = reader.readAll();
		} catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Object[][] data= new Object[rows.size()][];
		for(int i=0;i<rows.size();i++) {
			data[i]= rows.get(i);
		}	
		
		return data;
		
		
		
		
	}
	
	
	
}
