package com.merger.parser;

import java.io.IOException;
import java.util.List;

import com.merger.model.DirectoryRecord;

public interface RecordParser {

	List<DirectoryRecord> parse(String fileName) throws IOException;
}
