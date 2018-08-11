package markd315.io.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import markd315.io.model.QueryResponse;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

@Controller
public class UserApiController implements UserApi {
    private Set<String> userBlacklist;
    public static final String userProdPath = "src/main/resources/userBlacklist.json";
    public static final String userTestPath = "src/main/resources/test/userTestBlacklist.json";
    public String userPath;

    public ResponseEntity<Void> banUser(@ApiParam(value = "Banned user to add",required=true ) @PathVariable("name") String name) throws IOException {
        userBlacklist.add(name);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userPath), userBlacklist);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<QueryResponse> isNotBanned(@ApiParam(value = "Query if user is banned",required=true ) @PathVariable("name") String name) {
        boolean result = !userBlacklist.contains(name);
        QueryResponse body = new QueryResponse();
        body.setNotBlocked(new Boolean(result));
        return new ResponseEntity<QueryResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<Void> unban(@ApiParam(value = "Unban this user",required=true ) @PathVariable("name") String name) throws IOException {
        if(!isNotBanned(name).getBody().notBlocked()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        userBlacklist.remove(name);//Ban the user from generating documents in the future.
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userPath), userBlacklist);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public void setUserBlacklist(Set<String> readValue) {
        this.userBlacklist = readValue;
    }

    public Set<String> getUserBlacklist() {
        return this.userBlacklist;
    }
}
