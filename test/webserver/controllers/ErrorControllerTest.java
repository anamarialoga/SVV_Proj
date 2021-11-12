package webserver.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.mock;

public class ErrorControllerTest {

    private ErrorController errorController;

    @Test
    public void ErrorHeader() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket clientSocket = serverSocket.accept();
        PrintStream p = new PrintStream(clientSocket.getOutputStream());

        System.out.println("Opening http://localhost:10000/ ...");
        String errMess = "ERROR";
        assertEquals("Expected error output: ", "Message sent to:"+ p+ " --message: "+ errMess ,errorController.ErrorHeader(p, errMess));
    }
}
