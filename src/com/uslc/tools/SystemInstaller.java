package com.uslc.tools;

import java.io.File;

public class SystemInstaller {
	

	public static void main(String[] args) {
		String path = new File("") .getAbsolutePath();
		File classpathDir = new File(path + "/" + SystemDirectories.FONTS.toString());
		String osName = System.getProperty("os.name");
		int archType = Integer.parseInt( System.getProperty("sun.arch.data.model") );
		
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

}
