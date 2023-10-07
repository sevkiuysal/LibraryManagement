package com.koylumuhendis.librarymanagement.api;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@AutoConfigureMockMvc
@ActiveProfiles(value = "integration")
@SpringBootTest
public abstract class BaseControllerTest {
}
