import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

public class NewGame extends JFrame {

	private JTextField textField;
	private JLabel infoLabel;
	private JButton confirmBut;

	public NewGame() {
		initialize();
	}

	private void initialize() {

		getContentPane().setLayout(null);

		infoLabel = new JLabel("Name your new save");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(150, 20, 150, 30);
		getContentPane().add(infoLabel);

		confirmBut = new JButton("Confirm");
		confirmBut.setBounds(317, 134, 107, 37);
		getContentPane().add(confirmBut);
		confirmBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String temp = textField.getText();
				ArrayList<String> list;
				SaveAndRead sr = new SaveAndRead();
				
				try {
					list = sr.readSaves("Saves");
				} catch (Exception e) {
					list = new ArrayList<String>();
					e.printStackTrace();
				}
				
				if (list.contains(temp)) {
					
				}
				else {
					list.add(temp);
					
					try {
						sr.saveSaves(list, "Saves");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Creating new save was successful");
				}
			}
		});

		textField = new JTextField();
		textField.setBounds(33, 134, 241, 37);
		getContentPane().add(textField);
		textField.setColumns(10);

		// Frame settings
		setLocation(800, 400);
		setSize(450, 250);
		setResizable(false);
		setTitle("New Game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
