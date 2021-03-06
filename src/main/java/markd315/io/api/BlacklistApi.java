/**
 * NOTE: This class is auto generated by the io code generator program (unset).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package markd315.io.api;

import markd315.io.model.Hash;
import markd315.io.model.QueryResponse;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

@Api(value = "blacklist", description = "the blacklist API")
public interface BlacklistApi {

    @ApiOperation(value = "Add a new banned design (entire file hashed by server)", notes = "", response = Hash.class, authorizations = {
        @Authorization(value = "threeD_auth", scopes = {
            @AuthorizationScope(scope = "read:threeD", description = "query against blacklist"),
            @AuthorizationScope(scope = "write:threeD", description = "modify blacklists for users and designs")
            })
    }, tags={ "hash", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Added to list", response = Hash.class),
        @ApiResponse(code = 400, message = "Bad request", response = Void.class) })
    
    @RequestMapping(value = "/blacklist/file/add",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<Hash> addFile(@ApiParam(value = "Banned design to add", required = true) @Valid @RequestBody java.io.File body) throws IOException;


    @ApiOperation(value = "Add a new banned design", notes = "", response = Hash.class, authorizations = {
        @Authorization(value = "threeD_auth", scopes = {
            @AuthorizationScope(scope = "read:threeD", description = "query against blacklist"),
            @AuthorizationScope(scope = "write:threeD", description = "modify blacklists for users and designs")
            })
    }, tags={ "hash", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Added to list", response = Hash.class),
        @ApiResponse(code = 400, message = "Bad request", response = Void.class) })
    
    @RequestMapping(value = "/blacklist/add",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<Hash> addHash(@ApiParam(value = "Banned design to add", required = true) @Valid @RequestBody Hash body) throws IOException;


    @ApiOperation(value = "Test if a design is banned", notes = "", response = QueryResponse.class, authorizations = {
        @Authorization(value = "threeD_auth", scopes = {
            @AuthorizationScope(scope = "read:threeD", description = "query against blacklist")
            })
    }, tags={ "hash", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Blacklist decision in body", response = QueryResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = Void.class) })
    
    @RequestMapping(value = "/blacklist/file/query",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.POST)
    ResponseEntity<QueryResponse> fileNotOnBlacklist(@ApiParam(value = "Unsure design to test", required = true) @Valid @RequestBody java.io.File hash, @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)") @RequestHeader(value = "user", required = false) String user) throws IOException;


    @ApiOperation(value = "Test if a design is banned", notes = "", response = QueryResponse.class, authorizations = {
        @Authorization(value = "threeD_auth", scopes = {
            @AuthorizationScope(scope = "read:threeD", description = "query against blacklist")
            })
    }, tags={ "hash", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Blacklist decision in body", response = QueryResponse.class) })
    
    @RequestMapping(value = "/blacklist/{hash}",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" },
        method = RequestMethod.GET)
    ResponseEntity<QueryResponse> notOnBlacklist(@ApiParam(value = "Unsure design to test", required = true) @PathVariable("hash") String hash, @ApiParam(value = "Optional, the user responsible for requesting the design (could be banned if design is blasklisted)") @RequestHeader(value = "user", required = false) String user) throws IOException;

}
