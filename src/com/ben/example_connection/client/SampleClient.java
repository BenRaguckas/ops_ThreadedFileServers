package com.ben.example_connection.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SampleClient {

    public static void main(String[] args) throws Exception{

        InetAddress inetAddress = InetAddress.getByName("localhost");
        Socket socket = new Socket(inetAddress, 2000);

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream);

        System.out.println("InputStream class: " + inputStream.getClass());

        printWriter.println("Ben");
        printWriter.flush();

        String inputLine = scanner.nextLine();
        System.out.println("Server: " + inputLine);
    }
}
