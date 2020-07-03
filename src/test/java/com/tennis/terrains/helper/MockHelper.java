package com.tennis.terrains.helper;

import com.github.tomakehurst.wiremock.WireMockServer;

public class MockHelper {
    private static final int PORT = 8000;
    private static final String HOST = "localhost";

    private static WireMockServer server = new WireMockServer(PORT);


}
