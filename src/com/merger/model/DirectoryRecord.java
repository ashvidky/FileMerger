package com.merger.model;

import com.opencsv.bean.CsvBindByName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DirectoryRecord {

	
	public DirectoryRecord(Integer id) {
		
		this.id = id;		
	}
	
	@EqualsAndHashCode.Include
	@CsvBindByName(column = "ID", required = true)
	private Integer id;
	
	@CsvBindByName(column = "Name", required = false)
	private String name;
	
	@CsvBindByName(column = "Address", required = false)
	private String address;
	
	@CsvBindByName(column = "Phone", required = false)
	private String phone;
	
	@CsvBindByName(column = "Gender", required = false)
	private String gender;
	
	@CsvBindByName(column = "Occupation", required = false)
	private String occupation;
	
}
