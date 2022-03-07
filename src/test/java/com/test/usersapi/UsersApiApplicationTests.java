package com.test.usersapi;

import com.test.usersapi.model.Users;
import com.test.usersapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UsersApiApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void testSave() {

        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fak@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);
        assertThat(_users).isNotNull();

        repository.deleteById(_users.getId());
        Optional<Users> _usersDel = repository.findById(_users.getId());
        assertThat(_usersDel).isNotPresent();
    }

    @Test
    void testFindById() {

        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fake@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);

        Optional<Users> _usersById = repository.findById(_users.getId());
        assertThat(_usersById).isPresent();

        repository.deleteById(_users.getId());
        Optional<Users> _usersDel = repository.findById(_users.getId());
        assertThat(_usersDel).isNotPresent();
    }

    @Test
    void testFindAll() {

        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fake@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);

        List<Users> _list = repository.findAll();
        assertThat(_list).hasSizeGreaterThan(0);

        repository.deleteById(_users.getId());
        Optional<Users> _usersDel = repository.findById(_users.getId());
        assertThat(_usersDel).isNotPresent();
    }

    @Test
    void testUpdate() {
        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fak@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);
        assertThat(_users).isNotNull();

        users.setName("Test Fake");
        _users = repository.save(users);

        Optional<Users> _usersUpdate = repository.findById(_users.getId());
        assertThat(_usersUpdate).get().extracting(Users::getName).isEqualTo("Test Fake");

        repository.deleteById(_usersUpdate.get().getId());
        Optional<Users> _usersDel = repository.findById(_usersUpdate.get().getId());
        assertThat(_usersDel).isNotPresent();
    }

    @Test
    void testFindByName() {
        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fak@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);

        List<Users> _list = repository.findByNameOrSurname("Test", null);
        assertThat(_list).extracting(Users::getName).containsOnly("Test");

        repository.deleteById(_users.getId());
        Optional<Users> _usersDel = repository.findById(_users.getId());
        assertThat(_usersDel).isNotPresent();
    }

    @Test
    void testFindBySurname() {
        Users users = new Users();
        users.setName("Test");
        users.setSurname("TestSurname");
        users.setEmail("fak@mail.com");
        users.setPassword("fakepwd");
        users.setAddress("via fake");
        users.setCity("faketown");
        users.setZipcode("00fake");
        users.setCountry("fakecountry");

        Users _users = repository.save(users);

        List<Users> _list = repository.findByNameOrSurname(null, "TestSurname");
        assertThat(_list).extracting(Users::getSurname).containsOnly("TestSurname");

        repository.deleteById(_users.getId());
        Optional<Users> _usersDel = repository.findById(_users.getId());
        assertThat(_usersDel).isNotPresent();
    }
}
