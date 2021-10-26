package com.ben.threaded_file_server.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReadFileClient {

    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = InetAddress.getByName("localhost");
        System.out.println("Connecting to \"" + inetAddress.getHostAddress() + "\"");
        Socket socket = new Socket(inetAddress, 2000);
        String file_name = "secret.txt";

        //  Request file from server
        System.out.println("Requesting file \"" + file_name + "\" from server...");
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(file_name);
        printWriter.flush();

        //  Print file content
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
