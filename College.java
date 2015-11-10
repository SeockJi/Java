package Project;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class College extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static double result;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					College frame = new College();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 College() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 286, 294);
		setTitle("등급 입력기");
		contentPane = new JPanel();
		contentPane.setBackground(Color.green);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("국어");
		lblNewLabel.setBounds(27, 26, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("영어");
		lblNewLabel_1.setBounds(27, 51, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("수학");
		lblNewLabel_2.setBounds(27, 76, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("탐구(1)");
		lblNewLabel_3.setBounds(27, 101, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("탐구(2)");
		lblNewLabel_4.setBounds(27, 126, 57, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("등급");
		lblNewLabel_5.setBounds(27, 190, 57, 15);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("--");
		lblNewLabel_6.setBounds(117, 190, 57, 15);
		contentPane.add(lblNewLabel_6);
		
		Choice College = new Choice();
		Label Col = new Label("College");
		College.add("yj");
		College.add("km");

		textField = new JTextField();
		textField.setBounds(117, 23, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(117, 48, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(117, 73, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(117, 98, 116, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(117, 123, 116, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		final JLabel lblNewLabel_7 = new JLabel("--");
		lblNewLabel_7.setBounds(117, 190, 100, 15);
		contentPane.add(lblNewLabel_7);

		JButton btnNewButton = new JButton("확인");
		JButton btnNewButton_2 = new JButton("검색");	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double jumsu1 = Double.parseDouble(textField.getText());
					double jumsu2 = Double.parseDouble(textField_1.getText());
					double jumsu3 = Double.parseDouble(textField_2.getText());
					double jumsu4 = Double.parseDouble(textField_3.getText());
					double jumsu5 = Double.parseDouble(textField_4.getText());
					double tot = jumsu1 + jumsu2 + jumsu3;
					double tot2 = (jumsu4 + jumsu5) / 2;
					double avr = (tot + tot2) / 4;
					result=avr;
					lblNewLabel_6.setText("" + avr);
				} catch (Exception e1) {
					lblNewLabel_5.setText("정수");
					lblNewLabel_7.setText("정수");
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.db_conn(result);
			}
			
		});
		
		btnNewButton.setBounds(136, 156, 97, 23);
		contentPane.add(btnNewButton);
		btnNewButton_2.setBounds(90,220,97,23);
		contentPane.add(btnNewButton_2);
	}
}