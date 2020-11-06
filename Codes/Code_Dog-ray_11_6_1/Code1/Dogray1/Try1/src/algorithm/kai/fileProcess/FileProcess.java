package algorithm.kai.fileProcess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcess {
	
	static int nameTemp = 0;

	public static void writeFileWithIntMatrix(int[][] data, String name1) {
		
		String fileName = "someThing" + "_" + nameTemp;
		fileName = name1 + "_" + fileName;
		String fileType = "txt";
		String fileNameAll = fileName + "." + fileType;

		nameTemp += 1;

	    FileOutputStream outputStream = null;
	    try {
	        File file = new File("src/someFiles/" + fileNameAll);

	        while(file.exists()){
	        	
	        	fileName = "someThing" + "_" + nameTemp;
	        	fileType = "txt";
	        	fileNameAll = fileName + "." + fileType;
	        	
	        	file = new File("src/someFiles/" + fileNameAll);
	        	
	        	nameTemp += 1;
	        }

	        file.createNewFile();

	        FileWriter fileWritter = new FileWriter(file.getAbsoluteFile());

	        BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

	        for(int i = 0; i < data.length; i ++) {
	        	for(int j = 0; j < data[i].length; j ++) {

	        		bufferedWriter.write(data[i][j] + " ");

	        	}

	        	bufferedWriter.write("\n");
	        }
	        
	        bufferedWriter.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	

}
