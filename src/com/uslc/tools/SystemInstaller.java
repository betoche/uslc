package com.uslc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SystemInstaller {
	
	public static void main(String[] args) {
		String osName = System.getProperty("os.name").toLowerCase();
		int archType = Integer.parseInt( System.getProperty("sun.arch.data.model") );
		String swtFilename = "org.eclipse.swt.gtk.linux.x86_64_3.102.1.v20140206-1358.jar";
		
		if( osName.compareTo("windows") == 0 ) {
			
		} else if(osName.compareTo("linux") == 0){
			
		} else if( osName.compareTo("mac") == 0 ){
			
		}
		File path = new File( new File("") .getAbsolutePath() );
		File classpathDir = new File(path.getAbsolutePath() + "/" + SystemDirectories.FONTS.toString());
		File buildXml = new File( path.getAbsolutePath() + "/build.xml" );
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(buildXml);
			doc.getDocumentElement().normalize();
			Element rootElement = doc.getDocumentElement();
			
			System.out.println( "Root Element:  " + doc.getDocumentElement().getNodeName());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		

		
		System.out.println( "Installing USLC-Apparel system in " + osName + " " + archType + " bits, please wait..." );
		
		System.out.println( classpathDir.getAbsolutePath() );
		for (File file : classpathDir.listFiles()) {
			if( file.isFile() ){
				System.out.println( "File: " + file.getName() );
			}else if( file.isDirectory() ){
				System.out.println( "Directory: " + file.getName() );
			}
		}
		
		
	}
	
	public enum SystemDirectories{
		CLASSPATH( "classpath" ), CONFIG( "config" ), FONTS( "fonts" ), IMAGES( "images" ), REPORTS( "reports" );
		private String directoryName = "";
		
		private SystemDirectories( String directoryName ){
			this.directoryName = directoryName;
		}

		public String toString(){
			return directoryName;
		}
	}

	public enum SystemLibrary {
		LINUX_32("linux", "32", "swt.jar");
		
		private String osName = "";
		private String archType = "";
		private String fileName = "";
		
		private SystemLibrary(String osName, String archType, String fileName){
			this.osName = osName;
			this.archType = archType;
			this.fileName = fileName;
		}
	}
}
