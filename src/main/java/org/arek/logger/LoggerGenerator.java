package org.arek.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerGenerator {
	public static final	Logger LOGGER = Logger.getLogger(LoggerGenerator.class.getName());
	public static FileHandler FILE_HANDLER; 
	public static void main(String[] args) throws IOException {
	try {
		FILE_HANDLER = new FileHandler("/home/arkadiusz/Dokumenty/Logi/test.log");
		FILE_HANDLER.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(FILE_HANDLER);
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	int i = 0, j = 0;
	while(true) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.log(Level.WARNING, "warning! " + i);
		i++;
		if(i == 30) {
			File archiv = new File("/home/arkadiusz/Dokumenty/Logi/test" + j + ".log");
			File current = new File("/home/arkadiusz/Dokumenty/Logi/test.log");
			if(current.exists()) {
				if(archiv.exists()) {
					if(archiv.delete()) {
						Files.copy(current.toPath(), archiv.toPath());
					}
				} else {
					Files.copy(current.toPath(), archiv.toPath());
				}
							
			}
			
			FileWriter fw = new FileWriter(current);
			i = 0;
			j++;
		}
	}
	
	}

}
