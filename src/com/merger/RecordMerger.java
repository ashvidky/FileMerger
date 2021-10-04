package com.merger;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.merger.model.DirectoryRecord;
import com.merger.parser.CsvRecordParser;
import com.merger.parser.ParserFactory;
import com.merger.parser.RecordParser;
import com.opencsv.CSVParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordMerger {

	public static final String FILENAME_COMBINED = "combined.csv";

	/**
	 * Entry point of this test.
	 *
	 * @param args command line arguments: first.html and second.csv.
	 * @throws Exception bad things had happened.
	 */
	public static void main(final String[] args) throws Exception {

		if (args.length == 0) {
			System.err.println("Usage: java com.merger.RecordMerger file1 [ file2 [...] ]");
			System.exit(1);
		}

		HashMap<Integer, DirectoryRecord> recordsMap = new HashMap<>();
		
		for (String fileName : args) {
			
			RecordParser parser = ParserFactory.getParser(fileName);
			List<DirectoryRecord> list = parser.parse(fileName);
			
			list.forEach( i -> {
				
//				log.info("Processing:{}", i);
				
				if (!recordsMap.containsKey(i.getId())) {
					recordsMap.put(i.getId(), i);
				}
				else {
					
					DirectoryRecord r = recordsMap.get(i.getId());
					DirectoryRecord merged = mergeRecordData(r, i);
					recordsMap.put(merged.getId(), merged);
				}
				
			});
		}
		
		log.info("**** Results ******");
		
		recordsMap.entrySet().forEach(e ->{

			log.info("Entry:{}", e);
		});
		
		CsvRecordParser.write(FILENAME_COMBINED, recordsMap.values());
	}

	

	private static DirectoryRecord mergeRecordData(DirectoryRecord r, DirectoryRecord i) {
		
		DirectoryRecord merged = new DirectoryRecord();
		merged.setId(r.getId());
		merged.setName(r.getName() != null ? r.getName() : i.getName());
		merged.setAddress(r.getAddress() != null ? r.getAddress() : i.getAddress());
		merged.setGender(r.getGender() != null ? r.getGender() : i.getGender());
		merged.setOccupation(r.getOccupation() != null ? r.getOccupation() : i.getOccupation());
		merged.setPhone(r.getPhone() != null ? r.getPhone() : i.getPhone());
		
		return merged;
	}
}
