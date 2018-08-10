package markd315;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ThreeDamned{

	private static Set<String> hashBlacklist;
	private static Set<String> userBlacklist;
	private static boolean isLoaded = false;
    public static final String userProdPath = "src/main/resources/userBlacklist.json";
    public static final String hashProdPath = "src/main/resources/hashBlacklist.json";
    public static final String userTestPath = "src/main/resources/test/userTestBlacklist.json";
    public static final String hashTestPath = "src/main/resources/test/hashTestBlacklist.json";
    public static final boolean testMode = true;
    private static String hashPath;
    private static String userPath;

    public static boolean notOnBlacklist(String stl) throws IOException {
        loadListsIfNot();
        String contents = readFile(stl, Charset.defaultCharset());
        String sha1 = toSHA1(contents.getBytes());
        if(hashBlacklist.contains(sha1)) {
            return false;
        }
        return true;
    }

    public static void banUser(String username) throws IOException {
        userBlacklist.add(username);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userPath), userBlacklist);
    }
    public static void unbanUser(String username) throws IOException {
        userBlacklist.remove(username);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userPath), userBlacklist);
    }


    public static boolean notOnBlacklist(String stl, String username) throws IOException {
        loadListsIfNot();
        String contents = readFile(stl, StandardCharsets.US_ASCII);
        String sha1 = toSHA1(contents.getBytes());
        if(userBanned(username)){
            banUser(username);
            return false;
        }

        if (hashBlacklist.contains(sha1)) {

            return false;
        }
        return true;
    }

    public static boolean userBanned(String username) {
        return userBlacklist.contains(username);
    }

    public static void addToBlacklist(String stl) throws IOException {
        loadListsIfNot();
        String contents = readFile(stl, Charset.defaultCharset());
        String sha1 = toSHA1(contents.getBytes());
        hashBlacklist.add(sha1);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(hashPath), userBlacklist);
    }

    public static void addToBlacklist(File stl) throws IOException {
        addToBlacklist(readFile(stl.getPath(), Charset.defaultCharset()));
    }

	private static String toSHA1(byte[] toConvert){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashed = md.digest(toConvert);
        return Base58.encode(hashed);
    }

	private static void loadListsIfNot(){
        if(isLoaded) {
            return;
        }
        if(testMode){
		    hashPath = hashTestPath;
            userPath = userTestPath;
        }else{
            hashPath = hashProdPath;
            userPath = userProdPath;
        }

		//Loading blacklists into memory
		ObjectMapper mapper = new ObjectMapper();
		String json;
		try {
			json = readFile(hashPath, Charset.defaultCharset());
			hashBlacklist = mapper.readValue(json, new TypeReference<Set<String>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
            json = readFile(userPath, Charset.defaultCharset());
            userBlacklist = mapper.readValue(json, new TypeReference<Set<String>>(){});
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
