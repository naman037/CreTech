import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {
    private static final int PORT = 1234;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server started on port " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket).start();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                name = in.readLine();

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                broadcast("[Server] " + name + " has joined the chat.");

                String msg;
                while ((msg = in.readLine()) != null) {
                    if (msg.equalsIgnoreCase("/exit")) break;

                    String time = new SimpleDateFormat("HH:mm").format(new Date());
                    broadcast("[" + time + "] " + name + ": " + msg);
                }
            } catch (IOException e) {
                System.out.println("Error with client " + name);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {}
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                broadcast("[Server] " + name + " has left the chat.");
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
