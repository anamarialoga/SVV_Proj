package webserver.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.PrintStream;

public class ObjectFile {


    public static String foundFile(PrintStream os, File file)
    {
        String contentType = checkFile(file.toString());
        os.print("HTTP OK\n");
        os.print("Content-type:" +  contentType + "\n");

        if(contentType == null) return "Error when checking the file";
        return "Message sent to:" + os + "  --file: " + file + " --fileType: " + contentType;
    }

    public static File openFile(String filename)
    {
        File file = new File(filename);
        if (file.exists()) return file;
        if (filename.charAt(0) != '/') return file;
        return new File(filename.substring(1));
    }

    public static String sendResponse(PrintStream p, DataInputStream in, int arr)
    {
        try
        {
            byte[] buffer = new byte[arr];
            p.write(buffer, 0, arr);
            in.close();
        }
        catch (Exception e)  {
            System.out.println(e);
            return "Error when sending response to " + p;
        }

        return " " + p;
    }

    private static String checkFile(String fileExtension) {
        if(fileExtension.contains(".css"))
            return "css";
        if(fileExtension.contains(".html"))
            return "html";
        if(fileExtension.contains(".jpg"))
            return "jpg";
        if(fileExtension.contains(".txt"))
            return "txt";
        return null;
    }
}
