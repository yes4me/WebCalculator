/* ===========================================================================
Created:	2015/10/13 - https://github.com/yes4me/
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
SOURCE:		http://opencsv.sourceforge.net/apidocs/com/opencsv/CSVReader.html
=========================================================================== */

package lib;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class MyCSV {
	final char SEPARATOR	= ',';
	final char QUOTE_CHAR	= '\"';

	public List<String[]> read(String file, int lineSkipped) {
		CSVReader reader		= null;
		List<String[]> values	= null;
        try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(file), SEPARATOR, QUOTE_CHAR, lineSkipped);
            values = reader.readAll();
            //System.out.println("==>"+ values.get(2)[1] );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return values;
    }
	
	public static void main(String[] args) {
		//For testing purpose
		MyCSV myExcel		= new MyCSV();
		List<String[]> data	= myExcel.read("WebCalculator.csv", 0);
		String[][] result = MyCollection.convertStringArray(data);

		for (int i=0; i<result.length; i++)
		{
			for (int j=0; j<result[i].length; j++)
				System.out.print(result[i][j] +"#");
			System.out.println();
		}
	}
}