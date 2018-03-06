import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField stdNameText;
	private JTextField emailText;
	JButton submitBtn;
	ExamData db;
	Connection cn;
	Statement st;
	String sql,examName,ans4;
	ResultSet rs;
	JComboBox<String> examCombo;
	JPanel minor,main;
	int timecounter=60,ss=60;
	long idg,eidg,qidg;
	JLabel timeLabel,gg;
	Random random;
	JLabel questionLabel;
	JRadioButton ans4Radio,ans3Radio,ans2Radio,ans1Radio;
	long score=0,questionAttempted=0,correctAttempts=0,wrongAttempts=0,totalMarks=0,achivedMarks=0,correctAns=-1;
	long totalQuestions,negtiveMarks,marksForEach,currentQno=0;
	ButtonGroup Fgroup;
	Timer timer;
	private JButton admin;
	boolean chk=false;
	boolean firstIncheck=true;
	boolean rchk=false;
	JLabel mainLabel2,mainLabel;


	public static void main(String[] args) {

					Home frame = new Home();
					frame.setVisible(true);
	}


	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 790, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		main = new JPanel();
		main.setBounds(0, 0, 764, 405);
		contentPane.add(main);
		main.setLayout(null);
		
		mainLabel = new JLabel("Home");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel.setBounds(172, 28, 283, 29);
		main.add(mainLabel);
		
		JLabel examLabel = new JLabel("Exam Name");
		examLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		examLabel.setBounds(10, 66, 162, 32);
		main.add(examLabel);
		
		examCombo = new JComboBox<String>();
		examCombo.setBounds(214, 67, 238, 32);
		main.add(examCombo);
		
		JLabel stdNameLabel = new JLabel("Student Name");
		stdNameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		stdNameLabel.setBounds(10, 110, 163, 32);
		main.add(stdNameLabel);
		
		stdNameText = new JTextField();
		stdNameText.setColumns(10);
		stdNameText.setBounds(214, 116, 238, 29);
		main.add(stdNameText);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		emailLabel.setBounds(10, 170, 181, 32);
		main.add(emailLabel);
		
		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(214, 173, 238, 29);
		main.add(emailText);
		
		submitBtn = new JButton("Start Exam");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submitBtn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					if(onSubmit())
					{
					 setTime();
					 displayInst();
					 main.setVisible(false);
					 minor.setVisible(true);
					 loadThis();
					 //displayInst();
					 startTime();
					 //progress();
					}
		           
		        }	
			}
		});
		submitBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(onSubmit())
				{
				 setTime();
				 displayInst();
				 main.setVisible(false);
				 minor.setVisible(true);
				 loadThis();
				 //displayInst();
				 startTime();
				 //progress();
				}
			}
		});
		submitBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		submitBtn.setBounds(203, 238, 126, 41);
		main.add(submitBtn);
		
		admin = new JButton("Admin");
		admin.setForeground(Color.DARK_GRAY);
		admin.addKeyListener(new KeyAdapter() {
	
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					new Admin().setVisible(true);
					kill();
		           
		        }
			}
		});
		admin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Admin().setVisible(true);
				kill();
				
			}
		});
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		admin.setFont(new Font("Arial", Font.PLAIN, 12));
		admin.setBounds(0, 3, 126, 65);
		main.add(admin);
		
		JLabel lbldev = new JLabel("This project is devloped by Rohit Kumar Gautam B.tech student of nitk ");
		lbldev.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lbldev.setBounds(136, 3, 425, 14);
		main.add(lbldev);
		//main.setVisible(false);
		
		minor = new JPanel();
		minor.setBounds(0, 0, 764, 405);
		contentPane.add(minor);
		minor.setLayout(null);
		
		mainLabel2 = new JLabel("home");
		mainLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		mainLabel2.setBounds(0, 0, 764, 42);
		minor.add(mainLabel2);
		
		questionLabel = new JLabel("Question ?");//,
		questionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		questionLabel.setBounds(11, 53, 617, 32);
		minor.add(questionLabel);
		
		ans1Radio = new JRadioButton("ans ");
		ans1Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans1Radio.setBounds(20, 92, 534, 41);
		minor.add(ans1Radio);
		
		ans2Radio = new JRadioButton("");
		ans2Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans2Radio.setBounds(20, 136, 534, 37);
		minor.add(ans2Radio);
		
		ans3Radio = new JRadioButton("");
		ans3Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans3Radio.setBounds(20, 176, 534, 41);
		minor.add(ans3Radio);
		
		ans4Radio = new JRadioButton("");
		ans4Radio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		ans4Radio.setBounds(20, 220, 534, 42);
		minor.add(ans4Radio);
		
		Fgroup=new ButtonGroup();
		Fgroup.add(ans1Radio);
		Fgroup.add(ans2Radio);
		Fgroup.add(ans3Radio);
		Fgroup.add(ans4Radio);
		
		JButton button = new JButton("Submit");
		button.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(rchk)
					progress();
					loadThis();
					
		           
		        }	
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(rchk)
				progress();
				loadThis();
				
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 12));
		button.setBounds(232, 286, 126, 41);
		minor.add(button);
		
		JButton clearButton = new JButton("Clear ans");
		clearButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					Fgroup.clearSelection();
		           
		        }
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fgroup.clearSelection();
			}
		});
		clearButton.setFont(new Font("Arial", Font.PLAIN, 12));
		clearButton.setBounds(46, 286, 126, 41);
		minor.add(clearButton);
		
		timeLabel = new JLabel("mm:ss");
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		timeLabel.setBounds(667, 53, 60, 32);
		minor.add(timeLabel);
		
		gg = new JLabel("mm:ss");
		gg.setFont(new Font("Arial", Font.PLAIN, 15));
		gg.setBounds(667, 53, 60, 32);
		minor.add(timeLabel);
		
		JButton skipButton = new JButton("Skip");
		skipButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					firstIncheck=true;
					loadThis();
		           
		        }
			}
		});
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstIncheck=true;
				loadThis();
			}
		});
		skipButton.setFont(new Font("Arial", Font.PLAIN, 12));
		skipButton.setBounds(398, 286, 126, 41);
		minor.add(skipButton);
		minor.setVisible(false);
		random=new Random();
		fload();
	}
	public void fload()  
	{
		//form load//
		score=questionAttempted=correctAttempts=wrongAttempts=totalMarks=achivedMarks=0;correctAns=-1;
		totalQuestions=negtiveMarks=marksForEach=currentQno=0;ss=60;chk=false;
		try
        {
		 db=new ExamData();
		 cn=db.getConnection();
         st = cn.createStatement();
         ResultSet rs = st.executeQuery("Select exam_name from  exam_setup order by exam_name");
         examCombo.removeAllItems();
         examCombo.addItem("");
         stdNameText.setText("");//test();
         emailText.setText("");
         while (rs.next())
            {
        	 examCombo.addItem(rs.getString(1));
            }
	    } 
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
	}//end of fload();
	public boolean onSubmit()
	{
		try
		{
			String msg;
			if(examCombo.getSelectedItem().toString().equals(""))
			{
			 	msg="Please Enter The Exam Type...!";
			 	examCombo.requestFocusInWindow();
			 	JOptionPane.showMessageDialog(null,msg);
			 	return false;
			 	
			}
			else if(stdNameText.getText().equals(""))
			{
				msg="Please Enter Student Name...!";
				stdNameText.requestFocusInWindow();
				JOptionPane.showMessageDialog(null,msg);
			 	return false;
			}
			else if(emailText.getText().equals(""))
			{
				msg="Please Enter Email Addrs";
				emailText.requestFocusInWindow();
				JOptionPane.showMessageDialog(null,msg);
			 	return false;
			}
			else
		    {
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		        Pattern p =Pattern.compile(ePattern);
		        Matcher m = p.matcher(emailText.getText());
		        boolean bm = m.matches();
		        if(bm == false)
		        {
		        	msg="Please Enter a valid Email Addrs";
					emailText.requestFocusInWindow();
					JOptionPane.showMessageDialog(null,msg);
				 	return false;

		        }
		     }
			
			//long id;
			st=cn.createStatement();
			sql="Select id from student order by id DESC limit 1";
			rs=st.executeQuery(sql);
			if(rs.next())
				idg=rs.getLong(1)+1;
			else idg=1;
			
			//st.executeUpdate(sql);	
			JOptionPane.showMessageDialog(null,stdNameText.getText().toUpperCase()+"! \nplease note your id for further use\nid="+idg);
			mainLabel2.setText(stdNameText.getText()+" id:"+idg);
			insLoad();
			
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }
		return true;
	}
	public void insLoad()
	{
		try
		{
			long tmp = 0;
			sql="delete * from buffer";
			st.executeUpdate(sql);
			sql="select * from exam_setup where exam_name='"+examCombo.getSelectedItem().toString().toUpperCase()+"'";
			System.out.println(sql);
			rs=st.executeQuery(sql);
			if(rs.next())
			{
			 tmp=rs.getLong(2);
			 eidg=rs.getLong(1);
			 marksForEach=rs.getLong(4);
			 negtiveMarks=rs.getLong(5);
			 totalQuestions=rs.getLong(6);
			 examName=rs.getString(7);
			 totalMarks=marksForEach*totalQuestions;
			 System.out.println(eidg+" current exam details:->Time:"+rs.getLong(3)+"Marks for each q:"+marksForEach+"negtive marks:"+negtiveMarks+"total questions:"+totalQuestions+"exam name: "+examName);
			}
			sql="select id from exam_questions where c_id="+tmp+"";
			System.out.println(sql);
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				sql="insert into buffer(id)values("+rs.getLong(1)+")";
				System.out.println(sql);
				st.executeUpdate(sql);
			}
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }	
	}
	public void startTime()
	{
		
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	if(timecounter>0 || ss>0)
	            	{
	            	 if(ss>0)
	            	 {
	            		 ss--;
	            	 }
	            	 else
	            	 {
	            		 ss=60;
	            		 timecounter--;
	            	 }
	            	}
	            	else
	            	{
	            		ss=0;
	            		if(!chk)
	            		{
		                 JOptionPane.showMessageDialog(null,"time up");
		                 finalResults();
		                 homePanel();
	            		}
	            		else
	            		{
	            		 finalResults();
	            		 homePanel();
	            		}
		                 timer.stop();
		          
	            	}
	            	
	            	 String s=Integer.toString(timecounter);
	            	 String p=Integer.toString(ss);
	                 timeLabel.setText(s+":"+p);
	            	  
	            }
	        };
		timer = new Timer(1000, timerListener);
        timer.setInitialDelay(0);
        timer.start();
	}
	public void setTime()
	{
		try
		{
			sql="select time from exam_setup where id="+eidg+"";
			System.out.println(sql);
			rs=st.executeQuery(sql);
			if(rs.next())
			timecounter=rs.getInt(1)-1;
			
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }	
		
	}
	public void test()
	{
		
	}
	public void loadThis()
	{
		try
		{
			if(totalQuestions==currentQno)
			{
				//finalResults();
				timecounter=0;
				ss=0;
				chk=true;//time up hisab kitab
				return;
			}
			int totalRec=0,ch=0;
			int randomNumber=0;
			
			if(firstIncheck)
			{
				firstIncheck=false;	
				System.out.println("this is first call");
			}
			else
			{ 
				rchk=false;
				if(ans1Radio.isSelected())
				 rchk=true;
				if(ans2Radio.isSelected())
					rchk=true;
				if(ans3Radio.isSelected())
					rchk=true;
				if(ans4Radio.isSelected())
					rchk=true;
				if(!rchk)
				{
					JOptionPane.showMessageDialog(null,"Please select an answer...!");
					return;
				}
			}
			Fgroup.clearSelection();
			sql="Select COUNT(id) from buffer";
			rs=st.executeQuery(sql);
			if(rs.next())
			{
				totalRec=rs.getInt(1);
				System.out.println("stuff i got-->"+totalRec);
			}
			if(totalRec<=1)
			{
				randomNumber=1;
			}
			else
			randomNumber = random.nextInt(totalRec- 1) + 1;
			System.out.println(randomNumber);
			//loading the current generated stuff//
			sql="select * from buffer";
			rs=st.executeQuery(sql);
			int lop=1;
			while(lop<=randomNumber)
			{
				rs.next();
				lop++;
			}
//			sql="SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY id) AS Rno,* buffer )T WHERE T.Rno = "+randomNumber+"";
//			rs=st.executeQuery(sql);
				ch=rs.getInt(1);
			sql="select * from exam_questions where id="+ch+"";
			rs=st.executeQuery(sql);
			if(rs.next())
			{
				currentQno++;
				qidg=rs.getLong(1);
				correctAns=rs.getLong(8);
				questionLabel.setText(currentQno+"."+rs.getString(3));
				ans1Radio.setText(rs.getString(4));
				ans2Radio.setText(rs.getString(5));
				ans3Radio.setText(rs.getString(6));
				ans4Radio.setText(rs.getString(7));
				ans4=rs.getString(7);
				System.out.println("genrated question number"+randomNumber);
				System.out.println("correct ans in progress"+correctAns);
			}
			sql="delete * from buffer where id="+ch+"";
			st.executeUpdate(sql);
		}
		catch(Exception ex)
		{
			System.err.println("Exception");
			System.err.println(ex.getMessage());
		}
	}
	
	public void progress()//calculating marks and stuff
	{	
		try
		{		
			if(ans1Radio.isSelected() && correctAns==1)
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 1");
			}
			else if(ans2Radio.isSelected() && correctAns==2)
			{
				 score=score+marksForEach;	
				 correctAttempts++;
				 System.out.println("my ans 2");
			}
			else if(ans3Radio.isSelected() && correctAns==3)
			{
				 score=score+marksForEach;
				 correctAttempts++;
				 System.out.println("my ans 3");
			}
			else if(ans4Radio.isSelected() && correctAns==4)
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 4");
			}
			else if(ans4Radio.isSelected() && correctAns==5 && ans4.equals("ALL OF ABOVE"))
			{
			 score=score+marksForEach;
			 correctAttempts++;
			 System.out.println("my ans 5");
			}
			else if(ans4Radio.isSelected() && correctAns==6 && ans4.equals("NONE OF ABOVE"))
			{
				 score=score+marksForEach;
				 correctAttempts++;
				 System.out.println("my ans 6");
			}
			else
			{
				wrongAttempts++;
				score=score-negtiveMarks;
			}
			System.out.println("score ="+score);
			questionAttempted++;	
		}
		catch(Exception ex)
		{
			System.err.println("Exception");
			System.err.println(ex.getMessage());
		}
	}
	public void finalResults()
	{
		String msg="Result!\n1.score="+score+"\n2.question attempted="+questionAttempted+"\n3.correct attempted="+correctAttempts+"\n4.wrong attempted="+wrongAttempts+"\n5.total marks="+totalMarks+"";
		JOptionPane.showMessageDialog(null,msg);
		System.out.print(msg);

		try
		{
			sql="insert into student values("+idg+",'"+examCombo.getSelectedItem().toString().toUpperCase()+"','"+stdNameText.getText().toUpperCase()+"','"+emailText.getText().toUpperCase()+"',"+questionAttempted+","+correctAttempts+","+wrongAttempts+","+totalMarks+","+score+")";
			System.out.println(sql);
			st.executeUpdate(sql);
			
		}
		
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }	
	}
	public void displayInst()
	{    String msg="Important Instructions*\n1.You will have "+(timecounter+1)+" minutes\n2.There will be "+totalQuestions+" question\n3.Every question is mandatory\n4.You will get "+marksForEach+" Mark['s] for each correct attempt\n5."+negtiveMarks+" mark['s] will be deducted for each wrong attempt";
		 JOptionPane.showMessageDialog(null,msg);
	}
	public void homePanel()
	{
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Click \"Yes\" to take another test...!","Delete",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
		{
		  minor.setVisible(false);
		  main.setVisible(true);
		  fload();
		}
		else
			System.exit(0);
	}
	public void kill()
	{
		this.dispose();
	}
}
