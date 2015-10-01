package skynet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Settings {
	
	public Map<String,String> settings;
	
	public Settings(String filename){
		settings = new HashMap<String,String>();
		try {
			for (String line : Files.readAllLines(Paths.get(filename))) {
				if(line.trim().startsWith("#"))
					continue;
				if(!(line.contains("=") || line.contains(":")))
					continue;
				String[] split = line.split("[=:]");
				settings.put(split[0].trim(), split[1].trim());
			}
		} catch (IOException e) {
			System.out.println("error reading configuration file");
		}
	}	
	
	public Settings(){
		this("config");	
	}

}
