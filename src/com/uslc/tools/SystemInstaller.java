package com.uslc.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SystemInstaller {
	private SWTFilesDependency swtFile = null;
	private AntFileBuilder antFileBuilder = null;
	private List<SystemFile> commonDependencies = null;

	private AntFileBuilder getAntFileBuilder(){
		if( antFileBuilder == null ){
			SystemFile buildFile = new SystemFile(SystemDirectory.ROOT, "build.xml");
			List<SystemFile> filesToDelete = new ArrayList<SystemFile>();
			for( SystemFile file : SystemDirectory.CLASSPATH.listFiles() ){
				if( !getCommonDependencies().contains(file) ){
					filesToDelete.add(file);
				}
			}

			antFileBuilder = new AntFileBuilder(buildFile, filesToDelete);
		}
		return antFileBuilder;
	}

	private SWTFilesDependency getSwtFile(){
		if( swtFile == null ){
			swtFile = SWTFilesDependency.getSwtFileForCurrentSystem();
		}
		return swtFile;
	}
	private List<SystemFile> getCommonDependencies(){
		if( commonDependencies == null ){
			commonDependencies = new ArrayList<SystemFile>();
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "avalon-framework-4.2.0.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "barcode4j.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "com.ibm.icu_50.1.1.v201304230130.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "commons-beanutils-1.7.0.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "commons-collections-3.2.1.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "commons-digester-1.7.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "commons-logging.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "dom4j-1.6.1.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "iText-2.1.7.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "jasperreports-3.7.5.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "jasperreports-3.7.6.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "joda-time-1.6.2.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "log4j.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "mysql-connector-java-5.1.30-bin.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.core.commands_3.6.100.v20130515-1857.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.equinox.common_3.6.200.v20130402-1505.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.equinox.registry_3.5.301.v20130717-1549.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.jface_3.9.1.v20130725-1141.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.osgi_3.9.1.v20140110-1610.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.swt_3.102.1.v20140206-1334.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.swt.gtk.linux.x86_64_3.102.1.v20140206-1358.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.swt.win32.win32.x86_64_3.102.1.v20130827-2048.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.text_3.5.300.v20130515-1451.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.ui.forms_3.6.1.v20130822-1117.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "org.eclipse.ui.workbench_3.105.2.v20140211-1711.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "poi-3.9-20121203.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "poi-ooxml-3.9-20121203.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "poi-ooxml-schemas-3.9-20121203.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "spring-beans.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "swtjasperviewer-1.2.0.jar"));
			commonDependencies.add(new SystemFile(SystemDirectory.CLASSPATH, "xmlbeans-2.3.0.jar"));
		}
		return commonDependencies;
	}

	public static void main(String[] args) {
		File path = new File( new File("") .getAbsolutePath() );
		File classpathDir = new File(path.getAbsolutePath() + "/" + SystemDirectory.FONTS.toString());
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
		

		
		
		System.out.println( classpathDir.getAbsolutePath() );
		for (File file : classpathDir.listFiles()) {
			if( file.isFile() ){
				System.out.println( "File: " + file.getName() );
			}else if( file.isDirectory() ){
				System.out.println( "Directory: " + file.getName() );
			}
		}
		
		
	}

	public boolean exeAntFile(){
		boolean success = false;

		

		return success;
	}
}
