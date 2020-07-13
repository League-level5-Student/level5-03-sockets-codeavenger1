package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;
/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	JButton button = new JButton("Send");
	JTextArea text = new JTextArea(25, 25);
	public static JLabel clientMessage;
	public static JLabel serverMessage;
	JPanel panel = new JPanel();

	Server server;
	Client client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Texts!", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			button.setVisible(true);
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e) -> {
				server.sendClick(text.getText());
			});
			add(panel);
			panel.add(button);
			panel.add(text);
			serverMessage = new JLabel("Responses:\n" + ChatApp.serverMessage);
			panel.add(serverMessage);
			button.setVisible(true);
			text.setSize(400, 200);
			serverMessage.setSize(400, 200);
			pack();
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();

		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			add(button);
			setVisible(true);
			button.addActionListener((e) -> {
				client.sendClick(text.getText());
			});
			add(panel);
			panel.add(button);
			panel.add(text);
			clientMessage = new JLabel("Responses:\n");
			panel.add(clientMessage);
			button.setVisible(true);
			setVisible(true);
			text.setSize(400, 300);
			pack();
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
