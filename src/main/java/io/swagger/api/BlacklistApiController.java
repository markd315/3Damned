package io.swagger.api;

import io.swagger.model.Hash;
import io.swagger.model.QueryResponse;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

@Controller
public class BlacklistApiController implements BlacklistApi {



    public ResponseEntity<Hash> addFile(@ApiParam(value = "Banned design to add" ,required=true )  @Valid @RequestBody java.io.File body) {
        // do some magic!
        return new ResponseEntity<Hash>(HttpStatus.OK);
    }

    public ResponseEntity<Hash> addHash(@ApiParam(value = "Banned design to add" ,required=true )  @Valid @RequestBody Hash body) {
        // do some magic!
        return new ResponseEntity<Hash>(HttpStatus.OK);
    }

    public ResponseEntity<QueryResponse> fileNotOnBlacklist(@ApiParam(value = "Unsure design to test" ,required=true )  @Valid @RequestBody java.io.File hash,
        @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)" ) @RequestHeader(value="user", required=false) String user) {
        // do some magic!
        return new ResponseEntity<QueryResponse>(HttpStatus.OK);
    }

    public ResponseEntity<QueryResponse> notOnBlacklist(@ApiParam(value = "Unsure design to test",required=true ) @PathVariable("hash") String hash,
        @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)" ) @RequestHeader(value="user", required=false) String user) {
        // do some magic!
        return new ResponseEntity<QueryResponse>(HttpStatus.OK);
    }

}
