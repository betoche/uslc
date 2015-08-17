package com.uslc.tools;

import java.io.File;

public class SystemFile{
	private SystemDirectory systemDirectory = null;
	private String fileName = "";
	private File file = null;

	public SystemFile(SystemDirectory systemDirectory, String fileName){
		this.systemDirectory = systemDirectory;
		this.fileName = fileName;
	}

	public File getFile(){
		if( file == null ){
			file = new File(systemDirectory.getDirectory() + fileName);
		}
		return file;
	}
	public SystemDirectory getSystemDirectory() {
		return systemDirectory;
	}
	public String getFileName(){
		return fileName;
	}
}