package com.romanenko.lew.testworkaxon.Api;


import org.junit.Before;

public class BaseServiceTest {
    public JsonReader jsonReader;

    @Before
    public void setUp() throws Exception {
        jsonReader = new JsonReader();
    }

    public String getToken() {
        return "bearer token";
    }
}
