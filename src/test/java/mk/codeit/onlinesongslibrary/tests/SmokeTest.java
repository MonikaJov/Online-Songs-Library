package mk.codeit.onlinesongslibrary.tests;

import static org.assertj.core.api.Assertions.assertThat;

import mk.codeit.onlinesongslibrary.web.rest.ArtistRestController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

    @Autowired
    private ArtistRestController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}