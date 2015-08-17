package com.uslc.tools;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public enum SystemDirectory{
	ROOT( "" ),CLASSPATH( "classpath" ), CONFIG( "config" ), FONTS( "fonts" ), IMAGES( "images" ), REPORTS( "reports" ), SWT( "swt" );
	private String directoryName = "";

	private SystemDirectory( String directoryName ){
		this.directoryName = getSystemPath() + "/" + directoryName + "/";
	}

	public String getSystemPath(){
		File tmp = new File( "" );
		return tmp.getAbsolutePath();
	}
	public String getDirectory(){
		return directoryName;
	}
	public String toString(){
		return directoryName;
	}
	public List<SystemFile> listFiles(){
		List<SystemFile> systemFiles = new ArrayList<SystemFile>();

		File tmp = new File(getDirectory());

		String[] files = tmp.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isFile();
			}
		});

		for( String f : files ){
			systemFiles.add(new SystemFile(this, f));
		}

		return systemFiles;
	}
}
