package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

  @InjectMocks
  TokenService tokenService;

  private User user;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    ReflectionTestUtils.setField(tokenService, "secret", "1234567");
    user = new User();
    user.setEmail("test@gmail.com");
    user.setPassword("123");
  }

  @Test
  public void shouldCreateToken() {

    String token = tokenService.generateToken(user);

    assertNotNull(token);
  }

  @Test
  public void shouldReturnTokenIfTokenIsValid() {

    String token = tokenService.generateToken(user);

    String subject = tokenService.validateToken(token);

    assertEquals(subject, "test@gmail.com");
  }

  @Test
  public void shouldReturnNullIfTokenIsNotValid() {

    String subject = tokenService.validateToken("fake.token");

    assertNull(subject);
  }

}