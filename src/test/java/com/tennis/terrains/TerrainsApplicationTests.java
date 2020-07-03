package com.tennis.terrains;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = "h2")
class TerrainsApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    private static final int PORT = 8000;
    private static final String HOST = "localhost";
    private static WireMockServer server = new WireMockServer(PORT);


    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void getTerrain() throws Exception {
        getTarifByNames();
        mockMvc.perform(get("/terrains/t2"))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void getTerrains() throws Exception {
        mockMvc.perform(get("/terrains"))
                .andDo(print())
                .andExpect(status().is(200));
    }

    /**
     * wiremock tutorial
     * https://www.youtube.com/watch?v=UU05kp24d_k
     * <p>
     * dynamic port
     * https://www.petrikainulainen.net/programming/testing/wiremock-tutorial-configuration/
     * <p>
     * another possibility is to use swagger contract dependency
     */

    private void getTarifByNames() {
        server.start();
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200);
        WireMock.configureFor(HOST, PORT);
        WireMock.stubFor(
                WireMock.get("http://localhost:8000/tarifs/tarifs?names=tarif7")
                        .willReturn(aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("mockFiles/tarifs.json")));


    }

}

