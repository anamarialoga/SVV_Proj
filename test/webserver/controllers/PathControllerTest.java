package webserver.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathControllerTest {

    PathController pathController;


    @Test
    //testing paths to files
    public void getPath() {
        assertEquals("Expecting fileToTest.txt", "src/main/java/html/TestServer/TestServer/fileToTest.txt", pathController.getPath("GET /TestServer/fileToTest.txt HTTP/1.1"));
        assertEquals("Expecting index.html", "src/main/java/html/index/index.html", pathController.getPath("GET / HTTP/1.1"));
        assertEquals("Expecting null", null, pathController.getPath("POST / HTTP/1.1"));
        assertEquals("Expecting styles.css", "src/main/java/html/styles.css", pathController.getPath("GET /styles.css HTTP/1.1"));
        assertEquals("Expecting someText.txt", "src/main/java/html/TestServer/TestServer/someText.txt", pathController.getPath("GET /TestServer/someText.txt HTTP/1.1"));
        assertEquals("Expecting base.html", "src/main/java/html/TestServer/TestServer/base.html", pathController.getPath("GET /TestServer/base.html HTTP/1.1"));
        assertEquals("Expecting relLink.html", "src/main/java/html/TestServer/TestServer/someText.txt", pathController.getPath("GET /TestServer/someText.txt HTTP/1.1"));
        assertEquals("Expecting someText.txt", "src/main/java/html/TestServer/TestServer/someText.txt", pathController.getPath("GET /TestServer/someText.txt HTTP/1.1"));
    }
}
