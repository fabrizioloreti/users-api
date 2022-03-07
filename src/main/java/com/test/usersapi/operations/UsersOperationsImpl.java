package com.test.usersapi.operations;

import com.test.usersapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UsersOperationsImpl implements UsersOperations {

    @Autowired
    private JdbcTemplate jdbc;

    private String LOCATION_CREATE_VALUE = "/users/%d";

    private final String LOG_TAG = "[USERS-API-OPERATION]";

    private String SQL_USER_ID_SEQ = "select nextval('s_users')";
    private String SQL_INSERT_USER = "insert into users(id, name, surname, email, password, address, city, zipcode, country) "
            + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String SQL_DELETE_USER = "delete from users where id = ?";
    private String SQL_GET_USER = "select id, name, surname, email, password, address, city, zipcode, country from users where id = ?";
    private String SQL_GET_USERS = "select id, name, surname, email, password, address, city, zipcode, country from users";

    /**
     * Create a new user
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseEntity<Void> addNewUser(User user) throws Exception {
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.CREATED);

        Long id = jdbc.queryForObject(SQL_USER_ID_SEQ, Long.class);

        if(id != null) {

            int rows = jdbc.update(SQL_INSERT_USER
            ,id
            , user.getName()
            , user.getSurname()
            , user.getEmail()
            , user.getPassword()
            , user.getAddress()
            , user.getCity()
            , user.getZipcode()
            , user.getCountry()
            );

            if (rows > 0) {
                log.info(String.format("%s - user added [%d]", LOG_TAG, id));
            }

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(URI.create(String.format(LOCATION_CREATE_VALUE, id)));
            response = new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
        }

        return response;
    }

    /**
     * Delete a single user
     * @param id
     * @return
     * @throws Exception
     */
    public ResponseEntity<Void> deleteUser(Long id) throws Exception {
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        int rows = jdbc.update(SQL_DELETE_USER, id);
        if (rows > 0) {
            log.info(String.format("%s - user deleted [%d]", LOG_TAG, id));
        }

        return response;
    }

    /**
     * Get all users
     * @return
     * @throws Exception
     */
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        ResponseEntity<List<User>> response = null;

        return getUsers(SQL_GET_USERS, null);
    }

    /**
     * Get a single user
     * @param id
     * @return
     * @throws Exception
     */
    public ResponseEntity<User> getUser(Long id) throws Exception {
        ResponseEntity<User> response = null;

        try {
            Optional<User> user = jdbc.queryForObject(SQL_GET_USER,
                    (rs, rowNum) -> Optional.of(mapUserResult(rs)),
                    new Object[]{id});

            response = new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            log.info(String.format("%s - No record found in database for id %d", LOG_TAG, id));
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    /**
     * Update an existing user
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseEntity<Void> updateUser(User user) throws Exception {
        ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

        if(user.getId() == null)
            response = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        String sql = "update users set ";
        String set = "";
        String where = " where id = ?";
        List<Object> obj = new ArrayList<>();
        if(user.getAddress() != null) {
            set += "address = ?";
            obj.add(user.getAddress());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getCity() != null) {
            set += "city = ?";
            obj.add(user.getCity());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getCountry() != null) {
            set += "country = ?";
            obj.add(user.getCountry());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getZipcode() != null) {
            set += "zipcode = ?";
            obj.add(user.getZipcode());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getName() != null) {
            set += "name = ?";
            obj.add(user.getName());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getSurname() != null) {
            set += "surname = ?";
            obj.add(user.getSurname());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getEmail() != null) {
            set += "email = ?";
            obj.add(user.getEmail());
        }
        if(!set.equalsIgnoreCase("")) set += ",";
        if(user.getPassword() != null) {
            set += "password = ?";
            obj.add(user.getPassword());
        }

        obj.add(user.getId());
        int rows = jdbc.update(sql + set + where, obj.toArray());
        if (rows > 0) {
            log.info(String.format("%s - user updated [%d]", LOG_TAG, user.getId().longValue()));
        }

        return response;
    }

    /**
     * search users from name and/or surname
     * @param name
     * @param surname
     * @return
     * @throws Exception
     */
    public ResponseEntity<List<User>> searchUser(String name, String surname) throws Exception {
        ResponseEntity<List<User>> response = new ResponseEntity<>(HttpStatus.OK);

        Object[] params = null;
        String where = null;
        if(name != null) {
            where = " name = ?";
            params = new Object[] {name};
        }

        if(surname != null) {
            if(where != null) {
                where += " AND surname = ?";
                params = new Object[] {name, surname};
            }
            else {
                where = " surname = ?";
                params = new Object[] {surname};
            }
        }

        return getUsers(SQL_GET_USERS + ((where!=null)?" where " + where: ""), params);
    }

    private User mapUserResult(final ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getBigDecimal("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setCity(rs.getString("city"));
        user.setZipcode(rs.getString("zipcode"));
        user.setCountry(rs.getString("country"));
        return user;
    }

    private ResponseEntity<List<User>> getUsers(String sql, Object[] params) {

        List<User> users = jdbc.query(sql,
                (rs, rowNum) -> mapUserResult(rs),
                params
        );

        if(users == null || users.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
