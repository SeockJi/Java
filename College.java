package Project;

import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class College extends JFrame {
   private JPanel panel;
   private JTextField textField[] = new JTextField[5];
   double result;
   String college = "yj";

   private MenuBar mb = new MenuBar();
   private Menu select = new Menu("Select");
   private CheckboxMenuItem sschool = new CheckboxMenuItem("school");
   private CheckboxMenuItem sdepartment = new CheckboxMenuItem("deaprtment");
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               College frame = new College();
               frame.setMenu();
               frame.one();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   void setMenu()
   {
      this.setMenuBar(mb);
      mb.add(select);
      select.add(sschool);
      select.add(sdepartment);
      this.setVisible(true);
      
   }

   void one() {
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 286, 324);
      setTitle("등급 입력기");
      panel = new JPanel();
      panel.setBackground(Color.green);
      panel.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(panel);
      panel.setLayout(null);

      // ///////////////////////////////////
      Choice school = new Choice();
      Label Col = new Label("College");
      school.addItem("yj");
      school.addItem("km");
      school.setBounds(117, 5, 116, 21);
      panel.add(school);
      // //////////////////////////////////

      JLabel lblNewLabel_0 = new JLabel("대학선택");
      lblNewLabel_0.setBounds(27, 5, 57, 15);
      panel.add(lblNewLabel_0);

      JLabel lblNewLabel_1 = new JLabel("국어");
      lblNewLabel_1.setBounds(27, 26, 57, 15);
      panel.add(lblNewLabel_1);

      JLabel lblNewLabel_2 = new JLabel("영어");
      lblNewLabel_2.setBounds(27, 51, 57, 15);
      panel.add(lblNewLabel_2);

      JLabel lblNewLabel_3 = new JLabel("수학");
      lblNewLabel_3.setBounds(27, 76, 57, 15);
      panel.add(lblNewLabel_3);

      JLabel lblNewLabel_4 = new JLabel("탐구(1)");
      lblNewLabel_4.setBounds(27, 101, 57, 15);
      panel.add(lblNewLabel_4);

      JLabel lblNewLabel_5 = new JLabel("탐구(2)");
      lblNewLabel_5.setBounds(27, 126, 57, 15);
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
            if (choice.equals("yj")) {
               college = "yj";
            } else if (choice.equals("km")) {
               college = "km";
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
            Main m = new Main();
            m.db_conn(result, college);
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
      
      this.setVisible(true);
   }
}