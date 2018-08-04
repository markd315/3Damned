package main.java;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ThreeDamned {

	private static Set<String> hashBlacklist;
	private static Set<String> userBlacklist;

	private static boolean isLoaded = false; 
	
	public static String toSHA1(byte[] toConvert) throws UnsupportedEncodingException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashed = md.digest(toConvert);
        return new String(DatatypeConverter.printBase64Binary(hashed));
    }

	public static boolean notOnBlacklist(String stl) throws IOException {
		if(!isLoaded) {
			loadList();
		}
		String contents = readFile(stl, Charset.defaultCharset());
		String sha1 = toSHA1(contents.getBytes());
		if(hashBlacklist.contains(sha1)) {
            return false;
		}
		return true;
	}

    public static boolean notOnBlacklist(String stl, String username) throws IOException {
        if(!isLoaded) {
            loadList();
        }
        String contents = readFile(stl, Charset.defaultCharset());
        String sha1 = toSHA1(contents.getBytes());
        if(userBlacklist.contains(username)){
            return false;
        }

        if (hashBlacklist.contains(sha1)) {
            userBlacklist.add(username);//Ban the user from generating documents in the future.
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("src/main/resources/userTestBlacklist.json"), userBlacklist);
        }
        return true;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		loadList();
		byte[] bytee = new byte[1];
		bytee[0] = 56;
		System.out.println(toSHA1(bytee));
	}
	
	
	private static void loadList(){
		if(isLoaded) {
			return;
		}
		//Loading blacklist into memory
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = readFile("src/main/resources/hashTestBlacklist.json", Charset.defaultCharset());
			hashBlacklist = mapper.readValue(json, new TypeReference<Set<String>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		isLoaded = true;
	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
}
