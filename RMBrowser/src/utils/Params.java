package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Params {
	
	public String hostname = null;
	public String serverUrl = null;
	public String username = null;
	public String password = null;
	public String db = null;
	public String project = null;
	
	public Params() 
	{
		Params r = null;
		try {
			r = GetServerParams();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.serverUrl = r.serverUrl;
		this.hostname = r.hostname;
		this.username = r.username;
		this.password = r.password;
		this.db = r.db;
		this.project = r.project;
		//db =  "http://stl-qa-rm02:8080/rtmBrowser";
		
	}
	
	public  Params GetServerParams () throws FileNotFoundException {
        XStream xs = new XStream(new DomDriver());
        FileInputStream fis = new FileInputStream("c:/config.xml");
        xs.alias("root", Params.class);
        Params r = (utils.Params)xs.fromXML(fis);
        return r;
    }
	
	public static List<Map<String,String>> readXML(String pathToxml)
	{
		List<Map<String,String>> ListParams = new ArrayList<Map<String,String>>();
		
		try
		{	
			Map<String,String> params = new HashMap<String,String>();
			
			File fXmlFile = new File(pathToxml);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList nodeList = doc.getElementsByTagName("Parameters");

	        for (int i = 0; i < nodeList.getLength(); i++) {                
	            Node node = nodeList.item(i);
	            NodeList ParamsList = node.getChildNodes();
	            for (int j = 0; j < ParamsList.getLength(); j++) {        
	            	Node param = ParamsList.item(j);
	            	if (param.getNodeType() == Node.ELEMENT_NODE) {
	            		
	            	params.put(param.getNodeName(), param.getTextContent());
	            	 System.out.println("param:  " +param.getNodeName()+ " = " + param.getTextContent());
	            	}
	            }
	            ListParams.add(params);
	                                  
	            
	        }
		}
	     catch (Exception e) {
		e.printStackTrace();
	    }
		return ListParams;
	  }
	
	public static Object[] readFile1(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		//List<String[]> lines = new ArrayList<String[]>();
		List<Object> lines = new ArrayList<Object>();
		
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			lines.add(line.split(","));
		}
		int size = lines.size();
		System.out.println(size);
		br.close();
		
			    
		return lines.toArray(new String[size]);
	}
	
		
	}


