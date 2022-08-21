package com.justin.search.service;

import com.justin.search.SearchApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends SearchApplicationTests {

    @Autowired
    private UserService userServiceTest;

    @Test
    public void createIndexTest() {
        userServiceTest.indexCreate();
    }
}
