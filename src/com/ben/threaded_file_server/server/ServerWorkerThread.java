package com.ben.threaded_file_server.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorkerThread implements Runnable{

    private String directory;
    private Socket socket;

    public ServerWorkerThread(String directory, Socket socket) {
        this.directory = directory;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //  Receive file name
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            String file_name = scanner.nextLine();

            System.out.println("File \"" + file_name + "\" requested.");
            //  Scan for file / contents
            File file = new File(directory + file_name);
            String file_content = getFileContent(file);
            //  Create writer and send file content
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            printWriter.println(file_content);
            printWriter.flush();

            System.out.println("File sent, closing connection in 15 seconds...");

            Thread.sleep(15000);

            printWriter.close();
            socket.close();
        } catch (FileNotFoundException e) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);

                printWriter.println("Server was unable to find file requested...");
                printWriter.flush();
                System.out.println("Unable to find file, closing connection.");
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileContent(File file) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (stringBuilder.length() != 0)
                stringBuilder.append("\n");
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
