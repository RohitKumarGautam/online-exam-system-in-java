import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewResult extends JFrame {

	private JPanel contentPane;
	private JTextField viewText;
	ExamData db;
	Connection cn;
	Statement st;
	String sql;
	ResultSet rs;
	JLabel ename,std,emailLabel,questionAttemtedLabel,correctAttempts,wrongAttempts,totalMarks,achivedMarks;
	private JButton homeBtn;
	private JButton insertQuestionBtn;
	private JButton setupQuestionsBtn;
	public static void main(String[] args) {

					ViewResult frame = new ViewResult();
					frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public ViewResult() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 779, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainLabel = new JLabel("Result viewer");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel.setBounds(377, 0, 254, 29);
		contentPane.add(mainLabel);
		
		JButton viewBtn = new JButton("View");
		viewBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				 loadResult();
				}
			}
		});
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("calling");
				loadResult();
			}
		});
		viewBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		viewBtn.setBounds(245, 52, 126, 41);
		contentPane.add(viewBtn);
		
		viewText = new JTextField();
		viewText.setColumns(10);
		viewText.setBounds(0, 56, 238, 29);
		contentPane.add(viewText);
		
		ename = new JLabel("Exam Name");
		ename.setFont(new Font("Arial", Font.PLAIN, 15));
		ename.setBounds(10, 109, 228, 32);
		contentPane.add(ename);
		
		std = new JLabel("student name");
		std.setFont(new Font("Arial", Font.PLAIN, 15));
		std.setBounds(247, 109, 244, 32);
		contentPane.add(std);
		
		emailLabel = new JLabel("email");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		emailLabel.setBounds(10, 165, 196, 32);
		contentPane.add(emailLabel);
		
		questionAttemtedLabel = new JLabel("Question Attempted");
		questionAttemtedLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		questionAttemtedLabel.setBounds(247, 165, 228, 32);
		contentPane.add(questionAttemtedLabel);
		
		correctAttempts = new JLabel("correct attempts");
		correctAttempts.setFont(new Font("Arial", Font.PLAIN, 15));
		correctAttempts.setBounds(10, 215, 228, 32);
		contentPane.add(correctAttempts);
		
		wrongAttempts = new JLabel("worng attempts");
		wrongAttempts.setFont(new Font("Arial", Font.PLAIN, 15));
		wrongAttempts.setBounds(245, 219, 206, 32);
		contentPane.add(wrongAttempts);
		
		totalMarks = new JLabel("total marks");
		totalMarks.setFont(new Font("Arial", Font.PLAIN, 15));
		totalMarks.setBounds(10, 272, 225, 32);
		contentPane.add(totalMarks);
		
		achivedMarks = new JLabel("achived marks");
		achivedMarks.setFont(new Font("Arial", Font.PLAIN, 15));
		achivedMarks.setBounds(245, 270, 228, 32);
		contentPane.add(achivedMarks);
		
		homeBtn = new JButton("Logout");
		homeBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new Home().setVisible(true);
					    kill();
		           
		        }
			}
		});
		homeBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new Home().setVisible(true);
				kill();
			}
		});
		homeBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		homeBtn.setEnabled(true);
		homeBtn.setBounds(260, 0, 112, 33);
		contentPane.add(homeBtn);
		
		insertQuestionBtn = new JButton("Insert Questions");
		insertQuestionBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new InsertFrom().setVisible(true);
					    kill();
		           
		        }
			}
		});
		insertQuestionBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new InsertFrom().setVisible(true);
				kill();
			}
		});
		insertQuestionBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		insertQuestionBtn.setEnabled(true);
		insertQuestionBtn.setBounds(0, 0, 126, 32);
		contentPane.add(insertQuestionBtn);
		
		setupQuestionsBtn = new JButton("Setup Question");
		setupQuestionsBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new SetupExam().setVisible(true);
					    kill();
		           
		        }
			}
		});
		setupQuestionsBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new SetupExam().setVisible(true);
				kill();
			}
		});
		setupQuestionsBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		setupQuestionsBtn.setBounds(137, 0, 119, 32);
		contentPane.add(setupQuestionsBtn);
		fload();
	}
	public void fload()
	{
		try
        {
		 db=new ExamData();
		 cn=db.getConnection();
         st = cn.createStatement();
	    } 
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
	}
	public void loadResult()
	{
		try
        {
		 sql="select * from student where id="+viewText.getText()+"";
		 System.out.println(sql);
		 rs=st.executeQuery(sql);
		 //loading stuff into form;
		 if(rs.next())
		 {
			 ename.setText("Exam Name:"+rs.getString(2));//changes goes here ? ass :) here only its fine.... ;)
			 std.setText("Student Name:"+rs.getString(3));
			 emailLabel.setText("Email:"+rs.getString(4));
			 questionAttemtedLabel.setText("Question Attempted:"+rs.getString(5));
			 correctAttempts.setText("Correct Attempts:"+rs.getString(6));
			 wrongAttempts.setText("Wrong Attempts:"+rs.getString(7));
			 totalMarks.setText("Total Marks:"+rs.getString(8));
			 achivedMarks.setText("Achived Marks:"+rs.getString(9));
		 }
		 else
			 JOptionPane.showMessageDialog(null,"Student id not found...!");
	    } 
		catch (Exception ex)
        {
			JOptionPane.showMessageDialog(null,"Student id not found...!");
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
		
	}
	 public void kill()
	{
		this.dispose();
	}
}
