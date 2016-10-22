package com.main.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.main.servlet.SpeedServlet;

@Service
public class WriteFileService {

	public void store(int upspeedup,int upspeeddown,int downspeedup,int downspeeddown){
		java.net.URL url = SpeedServlet.class.getClassLoader().getResource("speed.txt");
		String fileName = url.getPath();  

	        try {
	            // Assume default encoding.
	            FileWriter fileWriter =
	                new FileWriter(fileName);

	            // Always wrap FileWriter in BufferedWriter.
	            BufferedWriter bufferedWriter =
	                new BufferedWriter(fileWriter);
	            System.out.println("----"+upspeedup);
	            // Note that write() does not automatically
	            // append a newline character.
	            bufferedWriter.write("11");
	            bufferedWriter.write(","+upspeedup);
	            bufferedWriter.write(","+upspeeddown);
	            bufferedWriter.write(","+downspeedup);
	            bufferedWriter.write(","+downspeeddown);
	            System.out.println("here");

	            // Always close files.
	            bufferedWriter.close();
	            fileWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error writing to file '"
	                + fileName + "'");
	            // Or we could just do this:
	            // ex.printStackTrace();
	        }
	}
}
