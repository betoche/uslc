package com.uslc.tools;

public enum SWTFilesDependency {
	LINUX_32("linux", 64, new SystemFile(SystemDirectory.SWT, "org.eclipse.swt.gtk.linux.x86_64_3.102.1.v20140206-1358.jar"));

	private String osName = "";
	private int archType = 0;
	private SystemFile systemFile = null;
	
	private SWTFilesDependency(String osName, int archType, SystemFile systemFile){
		this.osName = osName;
		this.archType = archType;
		this.systemFile = systemFile;
	}

	public String getOsName(){
		return osName;
	}

	public int getArchType(){
		return archType;
	}

	public SystemFile getSystemFile(){
		return systemFile;
	}

	public static SWTFilesDependency get( String osName, int archType ){
		SWTFilesDependency swt = null;
		for(SWTFilesDependency swtFile : SWTFilesDependency.values()){
			if( swtFile.getOsName().compareTo(osName)==0 && swtFile.getArchType() == archType ){
				swt = swtFile;
				break;
			}
		}
		return swt;
	}

	public static SWTFilesDependency getSwtFileForCurrentSystem(){
		SWTFilesDependency swt = null;

		String osName = System.getProperty("os.name").toLowerCase();
		int archType = Integer.parseInt( System.getProperty("sun.arch.data.model") );

		return get(osName, archType);
	}

	public String toString(){
		String text = "OS  : " + getOsName();
		text += "Arch: " + getArchType();
		text += "SWT File: " + getSystemFile().getFileName();
		return text;
	}
}