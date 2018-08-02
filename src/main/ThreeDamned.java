package main;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ThreeDamned {

	private String[] blacklist;
	
	public static String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}
	
	public boolean compareAgainstBlacklist(File stl) {
		return false;
		//TODO unimplemented
	}
	
	
	
	static {
		//TODO load blacklist into memory
		JSONObjectMapper mapper = new JSONObjectMapper();
	}
	
}
