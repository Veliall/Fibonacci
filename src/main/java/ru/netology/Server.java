package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(55555);

        try (Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("end")) {
                    break;
                }
                out.println(toCount(Integer.parseInt(line)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int toCount(int numb) {
        List<Integer> result = Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(numb)
                .map(n -> n[0])
                .collect(Collectors.toList());
        return result.get(numb - 1);
    }

}
