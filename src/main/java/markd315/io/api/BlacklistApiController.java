package markd315.io.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import markd315.io.Boot;
import markd315.io.model.Hash;
import markd315.io.model.QueryResponse;

import io.swagger.annotations.*;

import markd315.io.Base58;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

@Controller
public class BlacklistApiController implements BlacklistApi {

    public UserApiController userController = new UserApiController();
    private static Set<String> hashBlacklist;
    public static String hashPath;
    public static final String hashTestPath = "src/main/resources/test/hashTestBlacklist.json";
    public static final String hashProdPath = "src/main/resources/hashBlacklist.json";

    public ResponseEntity<Hash> addFile(@ApiParam(value = "Banned design to add" ,required=true )  @Valid @RequestBody java.io.File body) throws IOException {
        String contents = Boot.readFile(body.getPath(), Charset.defaultCharset());
            String sha1 = toSHA1(contents.getBytes());
            hashBlacklist.add(sha1);//Ban the user from generating documents in the future.
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(hashPath), userController.getUserBlacklist());
        return new ResponseEntity<Hash>(new Hash().digest(sha1), HttpStatus.OK);
    }

    public ResponseEntity<Hash> addHash(@ApiParam(value = "Banned design to add" ,required=true )  @Valid @RequestBody Hash body) throws IOException {
        String sha1 = body.getDigest();
        hashBlacklist.add(sha1);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(hashPath), userController.getUserBlacklist());
        return new ResponseEntity<Hash>(new Hash().digest(sha1), HttpStatus.OK);
    }

    public ResponseEntity<QueryResponse> fileNotOnBlacklist(@ApiParam(value = "Unsure design to test" ,required=true )  @Valid @RequestBody java.io.File file,
                                                            @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)" ) @RequestHeader(value="user", required=false) String user) throws IOException {
        // do some magic!
        String contents = Boot.readFile(file.getPath(), Charset.defaultCharset());
        String hash = toSHA1(contents.getBytes());
        return notOnBlacklist(hash, user);
    }

    public ResponseEntity<QueryResponse> notOnBlacklist(@ApiParam(value = "Unsure design to test",required=true ) @PathVariable("hash") String hash,
        @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)" ) @RequestHeader(value="user", required=false) String user) {
        if(user != null && !userController.isNotBanned(user).getBody().notBlocked()){
            return new ResponseEntity<QueryResponse>(new QueryResponse().notBlocked(false), HttpStatus.OK);
        }
        if (hashBlacklist.contains(hash)) {
            return new ResponseEntity<QueryResponse>(new QueryResponse().notBlocked(false), HttpStatus.OK);
        }
        return new ResponseEntity<QueryResponse>(new QueryResponse().notBlocked(true), HttpStatus.OK);
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

    public void setHashBlacklist(Set<String> readValue) {
        this.hashBlacklist = readValue;
    }

    public UserApiController getUserController() {
        return this.userController;
    }

    public void setUserController(UserApiController user){
        this.userController = user;
    }
}
