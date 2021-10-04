package com.merger.parser;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class ParserFactory {

	private static HashMap<String, RecordParser> parsers;
	static {
		
		parsers = new HashMap<>();
		parsers.put("csv", new CsvRecordParser() );
		parsers.put("html", new HtmlRecordParser() );
		
	}
	
	public static RecordParser getParser(String fileName) {
		
		String fileExtension = getFileExtension(fileName);
//		log.info("file extension:{}", fileExtension);
		
		RecordParser recordParser = parsers.get(fileExtension);
		if (recordParser == null)
			throw new UnsupportedOperationException();
		
		return recordParser;
	}
	
	private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
