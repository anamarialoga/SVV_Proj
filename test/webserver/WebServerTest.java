package webserver;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.shape.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import webserver.controllers.ErrorController;
import webserver.controllers.ErrorControllerTest;
import webserver.controllers.PathController;
import webserver.utils.ObjectFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static gui.WebServerGUI.SERVER_MAINTENANCE;
import static gui.WebServerGUI.SERVER_ROOT_WEB;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class WebServerTest {

    private ErrorController errorControllerMock =mock(ErrorController.class);;
    private PathController pathControllerMock = mock(PathController.class);;
    private ObjectFile objectFileMock = mock(ObjectFile.class);;
    WebServer webServer = null;

    @Test
    public void testServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10001);
        Socket clientSocket = serverSocket.accept();
        webServer = new WebServer(clientSocket,  SERVER_ROOT_WEB, SERVER_MAINTENANCE);
    }

    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    @Test
    //passes if the server is in maintenance
    public void testMaintenance() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10002);
        Socket clientSocket = serverSocket.accept();
        webServer = new WebServer(clientSocket,  SERVER_ROOT_WEB, SERVER_MAINTENANCE);

        String path = "..\\svv-project\\src\\main\\java\\html\\maintenance\\index.html";
        File file = new File(path);
        assertEquals("Expected correct path for the file", file, objectFileMock.openFile(path));

        String errMessage = "ERROR";
        PrintStream p = new PrintStream(clientSocket.getOutputStream());
        assertEquals("Expected error output: ", "Message sent to:" + p + " --ErrorMessage: " + errMessage ,errorControllerMock.errorHeader(p, errMessage));

        String expectedOutput = "Message sent to:" + p + "  --file: " + file + "  --fileType: " + "html" + "  --fileLength: " + file.length();
        assertEquals("Expected output to succeed when checking the file", expectedOutput, objectFileMock.foundFile(p, (int) file.length(), file));

        webServer.maintenanceServer();
    }

    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    @Test
    //passes if the server is running
    public void testRun() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10003);
        Socket clientSocket = serverSocket.accept();
        webServer = new WebServer(clientSocket, SERVER_ROOT_WEB, SERVER_MAINTENANCE);
        assertEquals("Expected correct path", "src/main/java/html/index/index.html", pathControllerMock.getPath("GET / HTTP/1.1"));

        String path = "src/main/java/html/index/index.html";
        File file = new File(path);
        assertEquals("Expected correct path for the file", file, objectFileMock.openFile(path));

        String errMessage = "ERROR";
        PrintStream p = new PrintStream(clientSocket.getOutputStream());
        assertEquals("Expected error output: ", "Message sent to:" + p + " --ErrorMessage: " + errMessage ,errorControllerMock.errorHeader(p, errMessage));

        String expectedOutput = "Message sent to:" + p + "  --file: " + file + "  --fileType: " + "html" + "  --fileLength: " + file.length();
        assertEquals("Expected output to succeed when checking the file", expectedOutput, objectFileMock.foundFile(p,(int) file.length(), file));

        webServer.run();
    }
}