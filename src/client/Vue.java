package client;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Vue extends JFrame {
	private JButton button = new JButton("Valider");
	private JTextField firstname = new JTextField();
	private JTextField secondname = new JTextField();
	private JTextField age = new JTextField();
	private JPanel pan = new JPanel();
	public Vue(){
		this.setTitle("Client");
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		pan.setLayout(new GridLayout(3,3));
		pan.add(firstname);
		pan.add(secondname);
		pan.add(age);
		pan.add(button);
		this.setContentPane(pan);
		this.setVisible(true);
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public JTextField getFirstname() {
		return firstname;
	}
	public void setFirstname(JTextField firstname) {
		this.firstname = firstname;
	}
	public JTextField getSecondname() {
		return secondname;
	}
	public void setSecondname(JTextField secondname) {
		this.secondname = secondname;
	}
	public JTextField getAge() {
		return age;
	}
	public void setAge(JTextField age) {
		this.age = age;
	}
	public JPanel getPan() {
		return pan;
	}
	public void setPan(JPanel pan) {
		this.pan = pan;
	}
	
	
	
}