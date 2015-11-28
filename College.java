package Project;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;
import java.util.Vector;

public class College extends JFrame {
	JPanel panel  = new JPanel();
	JPanel panel2 = new JPanel(); 
	JPanel panel3 = new JPanel();
	JPanel up 	  = new JPanel();
	JPanel safe   = new JPanel();
	JPanel down   = new JPanel();
	JTextField textField[] = new JTextField[5];
	double result;
	String college = "Konkuk";
	String depart  = "Computer";
	
	
	JMenuBar mb 			 = new JMenuBar();
	JMenu menu 		 	 = new JMenu("Select");
	JMenuItem school 	 = new JMenuItem("school");
	JMenuItem department = new JMenuItem("deaprtment");
	JMenuItem home		 = new JMenuItem("home");
	JMenuItem exit        = new JMenuItem("exit");
	
	ImageIcon icon;
	College()
	{
		mb.add(menu);
		menu.add(school);
		school.addActionListener(new action());
		menu.add(department);
		department.addActionListener(new action());
		menu.add(home);
		home.addActionListener(new action());
		menu.add(exit);
		setJMenuBar(mb);
		setVisible(true);
	}
	
	class action implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == school)
			{
				col_one();
			}
			if(e.getSource() == department){
				dep_one();
			}
			if(e.getSource() == home)
			{
				home();
			}
			if(e.getSource() == exit)
			{
				setVisible(false);
			}
		}	
	}
	
	void col_one() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("대학");
		setBounds(500,100,286,324);
		panel.setBackground(Color.green);
		add(panel);
		panel.setLayout(null);

		// ///////////////////////////////////
		Choice school = new Choice();
		JLabel Col = new JLabel("대학선택");
		school.addItem("Konkuk");
		school.addItem("Yeungin");
		school.addItem("Keimyung");
		school.addItem("Kyungpook");
		school.setBounds(117, 5, 116, 21);
		panel.add(school);
		// //////////////////////////////////

		JLabel lblNewLabel_0 = new JLabel("대학선택");
		lblNewLabel_0.setBounds(27, 10, 57, 15);
		panel.add(lblNewLabel_0);

		JLabel lblNewLabel_1 = new JLabel("국어");
		lblNewLabel_1.setBounds(27, 35, 57, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("영어");
		lblNewLabel_2.setBounds(27, 60, 57, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("수학");
		lblNewLabel_3.setBounds(27, 86, 57, 15);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("탐구(1)");
		lblNewLabel_4.setBounds(27, 111, 57, 15);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("탐구(2)");
		lblNewLabel_5.setBounds(27, 136, 57, 15);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("등급");
		lblNewLabel_6.setBounds(27, 190, 57, 15);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("--");
		lblNewLabel_7.setBounds(117, 190, 57, 15);
		panel.add(lblNewLabel_7);

		textField[0] = new JTextField();
		textField[0].setBounds(117, 33, 116, 21);
		panel.add(textField[0]);
		textField[0].setColumns(10);

		textField[1] = new JTextField();
		textField[1].setBounds(117, 58, 116, 21);
		panel.add(textField[1]);
		textField[1].setColumns(10);

		textField[2] = new JTextField();
		textField[2].setBounds(117, 83, 116, 21);
		panel.add(textField[2]);
		textField[2].setColumns(10);

		textField[3] = new JTextField();
		textField[3].setBounds(117, 108, 116, 21);
		panel.add(textField[3]);
		textField[3].setColumns(10);

		textField[4] = new JTextField();
		textField[4].setBounds(117, 133, 116, 21);
		panel.add(textField[4]);
		textField[4].setColumns(10);

		final JLabel lblNewLabel_8 = new JLabel("--");
		lblNewLabel_8.setBounds(117, 2000, 100, 15);
		panel.add(lblNewLabel_8);

		JButton btnNewButton = new JButton("확인");
		JButton btnNewButton_2 = new JButton("검색");
		JButton btnNewButton_3 = new JButton("초기화");

		school.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = e.getItem().toString();
				if (choice.equals("Computer")) {
					college = "Yeungin";
				} else if (choice.equals("계명대학교")){
					college = "km";
				} else if (choice.equals("경북대학교")){
					college = "kb";
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double jumsu1 = Float.parseFloat(textField[0].getText());
					double jumsu2 = Float.parseFloat(textField[1].getText());
					double jumsu3 = Float.parseFloat(textField[2].getText());
					double jumsu4 = Float.parseFloat(textField[3].getText());
					double jumsu5 = Float.parseFloat(textField[4].getText());
					double tot = jumsu1 + jumsu2 + jumsu3;
					double tot2 = (jumsu4 + jumsu5) / 2;
					double avr = (tot + tot2) / 4;
					result = avr;
					lblNewLabel_7.setText("" + avr);
				} catch (Exception e1) {
					lblNewLabel_8.setText("숫자로 써주세요");
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				college_db m = new college_db();
				Vector[] vt_result;
				vt_result=m.db_conn(result, college);
				col_rst(vt_result);
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int iCount = 0; iCount < 5; iCount++) {
					textField[iCount].setText("");
				}
				lblNewLabel_7.setText("--");
				lblNewLabel_6.setText("--");
			}
		});
	
		btnNewButton.setBounds(136, 166, 97, 23);
		panel.add(btnNewButton);
		btnNewButton_2.setBounds(150, 230, 97, 23);
		panel.add(btnNewButton_2);
		btnNewButton_3.setBounds(10, 230, 97, 23);
		panel.add(btnNewButton_3);
		
		up.setVisible(false);
		safe.setVisible(false);
		down.setVisible(false);
		panel3.setVisible(false);
		panel2.setVisible(false);
		panel.setVisible(true);
		
	}
	
	void dep_one(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 286, 324);
		setTitle("학과");
		panel2.setBackground(Color.yellow);
		add(panel2);
		panel2.setLayout(null);
		
		Choice department = new Choice();
		JLabel Col = new JLabel("학과");
		department.addItem("Computer");
		department.addItem("nursing");
		department.setBounds(117, 5, 116, 21);
		panel2.add(department);
		
		JLabel lblNewLabel_0 = new JLabel("학과선택");
		lblNewLabel_0.setBounds(27, 10, 57, 15);
		panel2.add(lblNewLabel_0);

		JLabel lblNewLabel_1 = new JLabel("국어");
		lblNewLabel_1.setBounds(27, 35, 57, 15);
		panel2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("영어");
		lblNewLabel_2.setBounds(27, 60, 57, 15);
		panel2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("수학");
		lblNewLabel_3.setBounds(27, 86, 57, 15);
		panel2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("탐구(1)");
		lblNewLabel_4.setBounds(27, 111, 57, 15);
		panel2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("탐구(2)");
		lblNewLabel_5.setBounds(27, 136, 57, 15);
		panel2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("등급");
		lblNewLabel_6.setBounds(27, 190, 57, 15);
		panel2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("--");
		lblNewLabel_7.setBounds(117, 190, 57, 15);
		panel2.add(lblNewLabel_7);

		textField[0] = new JTextField();
		textField[0].setBounds(117, 33, 116, 21);
		panel2.add(textField[0]);
		textField[0].setColumns(10);

		textField[1] = new JTextField();
		textField[1].setBounds(117, 58, 116, 21);
		panel2.add(textField[1]);
		textField[1].setColumns(10);

		textField[2] = new JTextField();
		textField[2].setBounds(117, 83, 116, 21);
		panel2.add(textField[2]);
		textField[2].setColumns(10);

		textField[3] = new JTextField();
		textField[3].setBounds(117, 108, 116, 21);
		panel2.add(textField[3]);
		textField[3].setColumns(10);

		textField[4] = new JTextField();
		textField[4].setBounds(117, 133, 116, 21);
		panel2.add(textField[4]);
		textField[4].setColumns(10);

		final JLabel lblNewLabel_8 = new JLabel("--");
		lblNewLabel_8.setBounds(117, 2000, 100, 15);
		panel2.add(lblNewLabel_8);

		JButton btnNewButton = new JButton("확인");
		JButton btnNewButton_2 = new JButton("검색");
		JButton btnNewButton_3 = new JButton("초기화");

		school.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String choice = e.getItem().toString();
				if (choice.equals("computer")) {
					depart = "computer";
				} else if (choice.equals("nursing")){
					depart = "nursing";
				} else if (choice.equals("kb")){
					depart = "kb";
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double jumsu1 = Float.parseFloat(textField[0].getText());
					double jumsu2 = Float.parseFloat(textField[1].getText());
					double jumsu3 = Float.parseFloat(textField[2].getText());
					double jumsu4 = Float.parseFloat(textField[3].getText());
					double jumsu5 = Float.parseFloat(textField[4].getText());
					double tot = jumsu1 + jumsu2 + jumsu3;
					double tot2 = (jumsu4 + jumsu5) / 2;
					double avr = (tot + tot2) / 4;
					result = avr;
					lblNewLabel_7.setText("" + avr);
				} catch (Exception e1) {
					lblNewLabel_8.setText("숫자로 써주세요");
				}
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				department_db m = new department_db();
				Vector[] vt_result;
				vt_result=m.db_conn(result, depart);
				dpt_rst(vt_result);
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int iCount = 0; iCount < 5; iCount++) {
					textField[iCount].setText("");
				}
				lblNewLabel_7.setText("--");
				lblNewLabel_6.setText("--");
			}
		});
	
		btnNewButton.setBounds(136, 166, 97, 23);
		panel2.add(btnNewButton);
		btnNewButton_2.setBounds(150, 230, 97, 23);
		panel2.add(btnNewButton_2);
		btnNewButton_3.setBounds(10, 230, 97, 23);
		panel2.add(btnNewButton_3);
		
		up.setVisible(false);
		safe.setVisible(false);
		down.setVisible(false);
		panel3.setVisible(false);
		panel.setVisible(false);
		
		panel2.setVisible(true);
	}
	
	void home()
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500,100,600,760);
		setTitle("you Serch College");	
		add(panel3);
		
		icon = new ImageIcon("C:\\Users\\석영\\workspace\\Jsy_Projcect\\src\\img\\home.png");
		JLabel label = new JLabel(icon);
		panel3.add(label);
		label.setBounds(0, 0, 500, 500);
		label.setLayout(null);
		panel.setVisible(false);
		panel2.setVisible(false);
		up.setVisible(false);
		safe.setVisible(false);
		down.setVisible(false);
		panel3.setVisible(true);
	}
	
	void col_rst(Vector[] vt_result){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 900, 700);
		setTitle("갈수있는 학과");
	
		up.setBackground(Color.PINK);
		safe.setBackground(Color.GREEN);
		down.setBackground(Color.ORANGE);
		setLayout(null);
		
		up.setLayout(null);
		up.setBounds(0, 0, 300, 700);
		add(up);
		JLabel up_title = new JLabel("상향");
		up_title.setLayout(null);
		up_title.setBounds(100,10,50,50);
		up.add(up_title);
		

		safe.setLayout(null);
		safe.setBounds(300,0,300,700);
		add(safe);
		JLabel safe_title = new JLabel("평균");
		safe_title.setLayout(null);
		safe_title.setBounds(100, 10, 50, 50);
		safe.add(safe_title);
		
		
		down.setLayout(null);		
		down.setBounds(600, 0, 300, 700);
		add(down);
		JLabel down_title = new JLabel("하향");
		down_title.setLayout(null);
		down_title.setBounds(100, 10, 50, 50);
		down.add(down_title);
		
		JLabel[] one = new JLabel[vt_result[0].size()];
		JLabel[] two = new JLabel[vt_result[1].size()];
		JLabel[] thr = new JLabel[vt_result[2].size()];

		int x=0;
		for(int i=0; i<vt_result[0].size(); i++)
		{
			one[i]= new JLabel((String) vt_result[0].get(i));
			one[i].setLayout(null);
			one[i].setBounds(50, 50+x, 200, 30);
			x=x+30;
		}
		int y=0;
		for(int i=0; i<vt_result[1].size(); i++)
		{
			two[i]= new JLabel((String) vt_result[1].get(i));
			two[i].setLayout(null);
			two[i].setBounds(50, 50+y, 200, 30);
			y=y+30;
		}
		int z=0;
		for(int i=0; i<vt_result[2].size(); i++)
		{
			thr[i]= new JLabel((String) vt_result[2].get(i));
			thr[i].setLayout(null);
			thr[i].setBounds(50, 50+z, 200, 30);
			z=z+30;
		}
		
		for(int i=0; i<one.length; i++)
		{
			up.add(one[i]);
		}
		for(int i=0; i<two.length; i++)
		{
			safe.add(two[i]);
		}
		for(int i=0; i<thr.length; i++)
		{
			down.add(thr[i]);
		}
		
		System.out.println(vt_result[0]);
		System.out.println(vt_result[1]);
		System.out.println(vt_result[2]);
		
		
		panel.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		
		up.setVisible(true);
		safe.setVisible(true);
		down.setVisible(true);

	}
	void dpt_rst(Vector[] vt_result){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 900, 700);
		setTitle("갈수있는 대학");
	
	}
}