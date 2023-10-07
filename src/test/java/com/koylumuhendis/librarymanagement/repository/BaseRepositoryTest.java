package com.koylumuhendis.librarymanagement.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles(value = "integration")
public abstract class BaseRepositoryTest {
}
