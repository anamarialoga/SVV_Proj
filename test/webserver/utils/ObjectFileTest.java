package webserver.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ObjectFileTest {

    private ObjectFile obj;
    private int fileLength;
    @Before
    public void setServer() {
        obj = new ObjectFile();
        fileLength = 30;
    }


    @Test
    //passes if file exists
    public void fileExists() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10004);
        Socket clientSocket = serverSocket.accept();
        PrintStream p = new PrintStream(clientSocket.getOutputStream());
        System.out.println("Opening http://localhost:10004/ ...");

        File file = new File("src\\main\\java\\html\\TestServer\\PathToMoreLinks\\relLink.html");
        String expectedOutput = "Message sent to:" + p + "  --file: " + file + " --fileType: " + "html";
        assertEquals("Expected output to succeed when checking the file", expectedOutput, obj.foundFile(p,  file));

        File file2 = new File("src\\main\\java\\html\\TestServer\\PathToMoreLinks\\MoreLinks\\absLink.html");
        String expectedOutput2 = "Message sent to:" + p + "  --file: " + file2 + " --fileType: " + "html";
        assertEquals("Expected output to succeed when checking the file", expectedOutput2, obj.foundFile(p, file2));
    }

    @Test
    //passes if the file doesn't exist
    public void fileOpen() {
        assertEquals("Expected new file",obj.OpenFile("notExist"), new File("notExist"));
        assertEquals("Expected new file",obj.OpenFile("notExist"), new File("notExist"));
    }

    @Test
    //passes when receiving response
    public void response() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10005);
        Socket clientSocket = serverSocket.accept();
        PrintStream p = new PrintStream(clientSocket.getOutputStream());
        System.out.println("OPEN BROWSER: http://localhost:10005/");

        File file = new File("..\\svv-project\\src\\main\\java\\html\\index\\index.html");
        String expectedOutput = "Message sent to:" + p + "  --file: " + file + " --fileType: " + "html";
        assertEquals("Expected output: ", expectedOutput, obj.foundFile(p,  file));

        DataInputStream in = new DataInputStream(new FileInputStream(new File("src/main/java/html/index/index.html")));
        assertEquals("Expected output to succeed ", " "+ p, obj.SendResponse(p, in, (int) new File("..\\svv-project\\src\\main\\java\\html\\index\\index.html").length()));

        assertEquals("Expected output to fail ", "Error when sending response to " + p, obj.SendResponse(p, in, -1));
    }
}