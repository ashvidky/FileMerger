package com.merger.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.merger.model.DirectoryRecord;

import lombok.extern.slf4j.Slf4j;


//@Slf4j
public class HtmlRecordParser  implements RecordParser{
	
	protected HtmlRecordParser() {}
	
	public List<DirectoryRecord> parse(String fileName) throws IOException{
		
		File file = new File(fileName);
		ArrayList<DirectoryRecord> records = new ArrayList<>();

		Document html;

		html = Jsoup.parse(file, null);
	
		Element table = html.getElementById("directory");

		//log.info(table.toString());

		Elements rows = table.select("tr");
		for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
			Element row = rows.get(i);
			Elements cols = row.select("td");

//			if (cols.size() != 4) {
//				log.error("Row format does not match! Skip the row.");
//				continue;
//			}

			
			Integer id = Integer.parseInt(cols.get(0).text());
			String name = cols.get(1).text();
			String address = cols.get(2).text();
			String phone = cols.get(3).text();

			DirectoryRecord record = new DirectoryRecord(id);
			record.setName(name);
			record.setAddress(address);
			record.setPhone(phone);
			
			records.add(record);
		}

//		log.debug("Records:{}", records);		

		return records;
	}
}
