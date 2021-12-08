package webserver.utils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
        fileLength = 50;
    }


    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    @Test
    //passes if file exists
    public void fileExists() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10004);
        Socket clientSocket = serverSocket.accept();
        PrintStream p = new PrintStream(clientSocket.getOutputStream());

        File file = new File("src\\main\\java\\html\\TestServer\\PathToMoreLinks\\relLink.html");
        String expectedOutput = "Message sent to:" + p + "  --file: " + file + "  --fileType: " + "html" + "  --fileLength: " + file.length();
        assertEquals("Expected output to succeed when checking the file", expectedOutput, obj.foundFile(p,  (int) file.length(), file));

        File file2 = new File("src\\main\\java\\html\\TestServer\\PathToMoreLinks\\MoreLinks\\absLink.html");
        String expectedOutput2 = "Message sent to:" + p + "  --file: " + file2 + "  --fileType: " + "html" + "  --fileLength: " + file.length();
        assertEquals("Expected output to succeed when checking the file", expectedOutput2, obj.foundFile(p, (int) file.length(), file2));
    }

    @Test
    //passes if the file doesn't exist
    public void fileOpen() {
        assertEquals("Expected new file",obj.openFile("notExist"), new File("notExist"));
        assertEquals("Expected new file",obj.openFile("notExist"), new File("notExist"));
    }

    @SuppressFBWarnings("DM_DEFAULT_ENCODING")
    @Test
    //passes when receiving response
    public void response() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10005);
        Socket clientSocket = serverSocket.accept();
        PrintStream p = new PrintStream(clientSocket.getOutputStream());

        File file = new File("..\\svv-project\\src\\main\\java\\html\\index\\index.html");
        String expectedOutput = "Message sent to:" + p + "  --file: " + file + "  --fileType: " + "html" + "  --fileLength: " + file.length();
        assertEquals("Expected output: ", expectedOutput, obj.foundFile(p, (int) file.length(),  file));

        DataInputStream in = new DataInputStream(new FileInputStream(new File("src/main/java/html/index/index.html")));
        assertEquals("Expected output to succeed ", " "+ p, obj.sendResponse(p, in, (int) new File("..\\svv-project\\src\\main\\java\\html\\index\\index.html").length()));

        assertEquals("Expected output to fail ", "Error when sending response to " + p, obj.sendResponse(p, in, -1));
    }
}