package com.test.usersapi.api;

import java.math.BigDecimal;
import com.test.usersapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.usersapi.operations.UsersOperationsImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-04T14:21:23.880Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final String LOG_TAG = "[USERS-API]";

    @Autowired
    private UsersOperationsImpl usersOp;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addNewUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        try {
            return usersOp.addNewUser(body);
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "user id to delete", required=true, schema=@Schema()) @PathVariable("id") BigDecimal id) {
        try {
            return usersOp.deleteUser(id.longValue());
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> readAllUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return usersOp.getAllUsers();
            } catch (Exception e) {
                log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<User> readUser(@Parameter(in = ParameterIn.PATH, description = "user id to extract", required=true, schema=@Schema()) @PathVariable("id") BigDecimal id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return usersOp.getUser(id.longValue());
            } catch (Exception e) {
                log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<User>> searchUser(@Parameter(in = ParameterIn.QUERY, description = "user name to search" ,schema=@Schema()) @Valid @RequestParam(value = "name", required = false) String name,@Parameter(in = ParameterIn.QUERY, description = "user surname to search" ,schema=@Schema()) @Valid @RequestParam(value = "surname", required = false) String surname) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return usersOp.searchUser(name, surname);
            } catch (Exception e) {
                log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        try {
            return usersOp.updateUser(body);
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
