package com.ben.threaded_file_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerApp {

    private static final String DIRECTORY = "resources/";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2000);

        while(true) {
            System.out.println("Server: Waiting for new connection...");

            Socket socket = serverSocket.accept();

            System.out.println("Connection accepted...");

            ServerWorkerThread worker = new ServerWorkerThread(DIRECTORY, socket);
            Thread worker_thread = new Thread(worker);
            worker_thread.start();
        }
    }
}
