package dataProviders;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.management.RuntimeErrorException;

public class ConfigFileReader {
	
	private static final Path currentRelativePath = Paths.get("");
	private static final String currentPath = currentRelativePath.toAbsolutePath().toString();
	private static final String propertyFilePath = currentPath+"\\src\\test\\resources\\configs\\Configuration.properties";
	
	private static Properties properties;
	
	public ConfigFileReader(){
		try {
			BufferedReader readerProperties = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(readerProperties);
				readerProperties.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("ARQUIVO (Configuration.properties) N�O ENCONTRADO NO CAMINHO ESPECIFICADO: " + propertyFilePath);
		}		
	}
	
	//AMBIENTE
	public static String environment(String environment) {
		new ConfigFileReader();
		String attribute = properties.getProperty("url_"+environment+"_fv");
		if (attribute!= null) {
			return attribute;
		} else {
			throw new RuntimeErrorException(null, "ATRIBUTO (url_"+environment+"_fv) N�O DEFINIDO NO ARQUIVO Configuration.properties");
		}
	}
	
	//USU�RIO V�LIDO
	public static String user(String profile,String position) {
		new ConfigFileReader();
		String attribute = properties.getProperty(profile+"_"+position);
		if (attribute!= null) { 
			return attribute;
		} else {
			throw new RuntimeErrorException(null, "ATRIBUTO ("+profile+"_"+position+") N�O DEFINIDO NO ARQUIVO Configuration.properties");
		}
	}
	
	//SENHA
	public static String password(String profile, String position) {
		new ConfigFileReader();
		String attribute = properties.getProperty("senha_"+profile+"_"+position);
		if (attribute!= null) { 
			return attribute;
		} else {
			throw new RuntimeErrorException(null, "ATRIBUTO (senha_"+profile+"_"+position+") N�O DEFINIDO NO ARQUIVO Configuration.properties");
		}
	}
		
}