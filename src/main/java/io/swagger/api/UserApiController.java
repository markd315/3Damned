package io.swagger.api;

import io.swagger.model.QueryResponse;

import io.swagger.annotations.*;

import markd315.ThreeDamned;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

@Controller
public class UserApiController implements UserApi {



    public ResponseEntity<Void> banUser(@ApiParam(value = "Banned user to add",required=true ) @PathVariable("name") String name) throws IOException {
        ThreeDamned.banUser(name);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<QueryResponse> isBanned(@ApiParam(value = "Query if user is banned",required=true ) @PathVariable("name") String name) {
        boolean result = ThreeDamned.userBanned(name);
        QueryResponse body = new QueryResponse();
        body.setNotFlaggedForConcern(new Boolean(result));
        return new ResponseEntity<QueryResponse>(body, HttpStatus.OK);
    }

    public ResponseEntity<Void> unban(@ApiParam(value = "Unban this user",required=true ) @PathVariable("name") String name) throws IOException {
        if(!ThreeDamned.userBanned(name)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        ThreeDamned.banUser(name);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
