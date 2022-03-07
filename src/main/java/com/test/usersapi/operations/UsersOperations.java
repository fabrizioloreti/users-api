package com.test.usersapi.operations;

import com.test.usersapi.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersOperations {

    public ResponseEntity<Void> addNewUser(User user) throws Exception;

    public ResponseEntity<Void> deleteUser(Long id) throws Exception;

    public ResponseEntity<List<User>> getAllUsers() throws Exception;

    public ResponseEntity<User> getUser(Long id) throws Exception;

    public ResponseEntity<Void> updateUser(User user) throws Exception;

    public ResponseEntity<List<User>> searchUser(String name, String surname) throws Exception;
}
