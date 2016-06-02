package de.wdz.numeric.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHandler {

	private String file;

	public CSVHandler(String file) {
		this.setFile(file);
	}

	public double[][] read() {
		try {
			File file = new File(getFile());
			CSVParser csvParser = CSVParser.parse(file, Charset.forName("UTF-8"), CSVFormat.RFC4180);
			List<CSVRecord> records = csvParser.getRecords();

			// remove row with column names, danger !!!
			records.remove(0);

			int rows = records.size() - 1;
			int columns = records.get(0).size();
			double[][] matrix = new double[rows][columns];

			for (int i = 0; i < records.size() - 1; i++) {

				// System.out.println(records.get(i).get(0) + ":" +
				// records.get(i).get(1));

				Double value1 = Double.valueOf(records.get(i).get(0));
				Double value2 = Double.valueOf(records.get(i).get(1));

				matrix[i][0] = value1;
				matrix[i][1] = value2;
			}
			return matrix;
		} catch (IOException e) {
			System.err.println("error on read csv file");
			e.printStackTrace();
		}
		return null;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}