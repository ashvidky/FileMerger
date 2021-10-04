package com.merger.parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.merger.model.DirectoryRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.extern.slf4j.Slf4j;


//@Slf4j
public class CsvRecordParser implements RecordParser{

	protected CsvRecordParser() {}
	
	public List<DirectoryRecord> parse(String fileName) throws IOException{
		
		FileReader fileReader = null;
		List<DirectoryRecord> values = new ArrayList<DirectoryRecord>();
		try {
			fileReader = new FileReader(fileName);

			values = new CsvToBeanBuilder(fileReader)
					.withType(DirectoryRecord.class).build().parse();
		}
		finally {
			if (fileReader != null)
				fileReader.close();
		}

//		values.stream().forEach(e ->{
//
//			log.info("Entry:{}", e);
//		});

		return values;
	}
	
	
	public static void write(String fileName, Collection<DirectoryRecord> records) throws Exception {
				
		Writer writer = null;
		try {
			writer = new FileWriter(fileName);
			StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
			beanToCsv.write(records.stream().sorted(Comparator.comparingInt(DirectoryRecord::getId)));
		}
		finally {
			if (writer != null)
				writer.close();
		}
		
		
	}
}
