package com.test.usersapi.api;

import java.math.BigDecimal;
import com.test.usersapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.usersapi.model.Users;
import com.test.usersapi.operations.UsersOperations;
import com.test.usersapi.operations.UsersOperationsImpl;
import com.test.usersapi.repository.UserRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-04T14:21:23.880Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final String LOG_TAG = "[USERS-API]";

    private String LOCATION_CREATE_VALUE = "/users/%d";

    @Autowired
    private UsersOperations usersOp;

    @Autowired
    private UserRepository userRepo;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addNewUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        try {
            Users _user = userRepo.save(Users.mapUser(body));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(URI.create(String.format(LOCATION_CREATE_VALUE, _user.getId())));
            ResponseEntity<Void> response = new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
            return response;
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "user id to delete", required=true, schema=@Schema()) @PathVariable("id") BigDecimal id) {
        try {
            userRepo.deleteById(id.longValue());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<User>> readAllUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Users> list = userRepo.findAll();
                List<User> listOut = new ArrayList<>();
                if(list != null && list.size() > 0) {
                    list.stream().forEach(x -> {listOut.add(Users.mapUsers(x));});
                    return new ResponseEntity<>(listOut, HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
                Optional<Users> _user = userRepo.findById(id.longValue());
                if(_user.isPresent())
                    return new ResponseEntity<>(Users.mapUsers(_user.get()), HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
                List<Users> list = userRepo.findByNameOrSurname(name, surname);
                List<User> listOut = new ArrayList<>();
                if(list != null && list.size() > 0) {
                    list.stream().forEach(x -> {listOut.add(Users.mapUsers(x));});
                    return new ResponseEntity<>(listOut, HttpStatus.OK);
                }
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        try {
            Optional<Users> _user = userRepo.findById(body.getId().longValue());
            if(_user.isPresent()) {

                if(body.getName() != null)
                    _user.get().setName(body.getName());
                if(body.getSurname() != null)
                    _user.get().setSurname(body.getSurname());
                if(body.getEmail() != null)
                    _user.get().setEmail(body.getEmail());
                if(body.getPassword() != null)
                    _user.get().setPassword(body.getPassword());
                if(body.getAddress() != null)
                    _user.get().setAddress(body.getAddress());
                if(body.getCity() != null)
                    _user.get().setCity(body.getCity());
                if(body.getZipcode() != null)
                    _user.get().setZipcode(body.getZipcode());
                if(body.getCountry() != null)
                    _user.get().setCountry(body.getCountry());

                userRepo.save(_user.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(String.format("%s - %s", LOG_TAG, e.getMessage()), e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
