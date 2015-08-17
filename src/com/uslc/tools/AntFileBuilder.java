package com.uslc.tools;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AntFileBuilder {
	private SystemFile buildFile = null;
	private List<SystemFile> filesToDelete = null;
	private Document doc = null;

	public AntFileBuilder( SystemFile buildFile, List<SystemFile> filesToDelete ){
		this.buildFile = buildFile;
		this.filesToDelete = filesToDelete;


		DocumentBuilderFactory dbFac = null;
		DocumentBuilder builder = null;

		try{
			dbFac = DocumentBuilderFactory.newInstance();
			builder = dbFac.newDocumentBuilder();
			xmlFile = builder.newDocument();

			Element rootElement = xmlFile.createElement("project");
			rootElement.setAttribute("name", "UslcInstaller");
			rootElement.setAttribute("default", "CompileProject");
			xmlFile.appendChild(rootElement);

			

			Element removeFiles = xmlFile.createElement("target");
			removeFiles.setAttribute("name", "RemoveFiles");
			
		}catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public boolean makeFile(){
		boolean success = false;


		return success;
	}
	public Document getDocument(){
		DocumentBuilderFactory dbFac = null;
		DocumentBuilder builder = null;

		try{
			dbFac = DocumentBuilderFactory.newInstance();
			builder = dbFac.newDocumentBuilder();
			doc = builder.newDocument();

			Element rootElement = doc.createElement("project");
			rootElement.setAttribute("name", "UslcInstaller");
			rootElement.setAttribute("default", "CompileProject");
			doc.appendChild(rootElement);
		}catch(ParserConfigurationException e) {
			e.printStackTrace();
		}

		return doc;
	}
}
