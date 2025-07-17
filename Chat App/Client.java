import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private String name;

    public Client() {
        frame = new JFrame("Chat");
        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        inputField = new JTextField(40);
        JButton sendButton = new JButton("Send");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sendButton.addActionListener(e -> send());
        inputField.addActionListener(e -> send());
    }

    private void send() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty()) {
            out.println(msg);
            inputField.setText("");
            if (msg.equalsIgnoreCase("/exit")) System.exit(0);
        }
    }

    private void connect() throws IOException {
        Socket socket = new Socket("localhost", 1234);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        name = JOptionPane.showInputDialog(frame, "Enter your name:");
        if (name == null || name.trim().isEmpty()) System.exit(0);
        out.println(name);

        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    chatArea.append(line + "\n");
                }
            } catch (IOException e) {
                chatArea.append("[Disconnected]\n");
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect();
    }
}
