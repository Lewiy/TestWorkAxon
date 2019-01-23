package com.romanenko.lew.testworkaxon.Api;

import org.junit.Before;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class ApiTest {

    private MockWebServer server;

  @Before
    public void setUp () throws IOException {

        server = new MockWebServer();

      server.start();

      final Dispatcher dispatcher = new Dispatcher() {
          @Override
          public MockResponse dispatch(RecordedRequest request) throws
                  InterruptedException {
              String p = request.getPath();
              MockResponse response = new MockResponse().setResponseCode(200);
              if (p.matches("/board/all.*") ||
                      p.matches("/board/moderator/all.*")) {
                  return response.setBody(jsonReader.readString
                          ("json/bulletins.json"));
              } else if (p.equals("/board") ||
                      p.equals("/board/" + BULLETIN_ID)) {
                  return response.setBody(jsonReader.readString
                          ("json/ok.json"));
              return new MockResponse().setResponseCode(404);
          }
      };
      server.setDispatcher(dispatcher);
      HttpUrl baseUrl = server.url("/");
      service = ServiceCreator.createTestService(baseUrl.toString(),
              BulletinService.class);

  }
}
