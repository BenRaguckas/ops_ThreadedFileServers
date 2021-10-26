package com.ben.example_connection.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SampleServer {

    public static void main(String[] args) throws IOException {
        Socket socket;
        ServerSocket serverSocket = new ServerSocket(2000);

        while (true) {
            System.out.println("Server waiting for connection...");
            socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            String input_line = scanner.nextLine();

            printWriter.println("\"Hello " + input_line + ".\" - The server greets you.");
            printWriter.close();
            socket.close();
        }
    }
}
