package com.yukta.repository;

import com.yukta.entity.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    private static final String NAME = "Usuario nome";
    private static final String EMAIL = "email@teste.com";
    private static final String PASSWORD = "12345678";
    private static final String ROLE = "ROLE_ADMIN";

    @Autowired
    UserRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        User u = getUserMock();
        User response = repository.save(u);
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(ROLE, response.getRole());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveInvalidUser() {
        User u = getUserMock();
        u.setEmail("");
        User response = repository.save(u);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveUserDuplicatedEmail() {
        User u = getUserMock();
        User response = repository.save(u);

        User ud = getUserMock();
        User responseD = repository.save(ud);
    }

    private User getUserMock() {
        User u = new User();
        u.setName(NAME);
        u.setEmail(EMAIL);
        u.setPassword(PASSWORD);
        u.setRole(ROLE);
        return u;
    }

}


