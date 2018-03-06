import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InsertFrom extends JFrame {
	private JPanel contentPane;
	private JTextField ans1Text;
	private JTextField ans2Text;
	private JTextField ans3Text;
	private JTextField ans4Text;
	JComboBox<String> catCombo;
	JTextArea QuestionText;
	JScrollPane sp,listScroll;
	ExamData db;
	Connection cn;
	JRadioButton ans1Radio,ans2Radio,ans3Radio,ans4Radio,ansAllRadio,ansNoneRadio;
	ButtonGroup Fgroup,Sgroup;
	JButton submitBtn;
    JList<String> list;
    JList<Long> tmplist;
	String[] data;
	DefaultListModel<String> myList;
	DefaultListModel<Long> tmpid;
	private JLabel countLabel;
	private JLabel optionsLabel;
	private JLabel aLabel;
	private JLabel bLabel;
	private JLabel cLabel;
	private JLabel dLabel;
	private JLabel totalLabel;
	private JButton setupExamBtn;
	private JButton viewResultbtn;
	private JButton homeBtn;

	public static void main(String[] args) {
		
					InsertFrom frame = new InsertFrom();
					frame.setVisible(true);	
	
	}

	public InsertFrom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1075, 777);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mainLabel = new JLabel("Insert Questions");
		mainLabel.setBounds(529, 5, 196, 29);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		contentPane.add(mainLabel);
		
		JLabel catLabel = new JLabel("1.Category");
		catLabel.setBounds(5, 66, 119, 32);
		catLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(catLabel);
		
		catCombo = new JComboBox<String>();
		catCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			if (arg0.getStateChange() == ItemEvent.SELECTED) 
			{
				String t="";
				if(!catCombo.getSelectedItem().toString().equals(t))
				{
					listLoad();
				}
			}
		  }
		});
		catCombo.setBounds(146, 67, 208, 32);
		contentPane.add(catCombo);
		
		JLabel questionLabel = new JLabel("2.Question");
		questionLabel.setBounds(5, 139, 119, 32);
		questionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(questionLabel);
		
		QuestionText = new JTextArea();
		QuestionText.setTabSize(5);
		QuestionText.setLineWrap(true);
		QuestionText.setWrapStyleWord(true);
		QuestionText.setRows(10);
		QuestionText.setColumns(10);
		QuestionText.setBounds(1,1,213,184);
        contentPane.add(QuestionText);
        sp = new JScrollPane(QuestionText);
        sp.setBounds(146, 143, 353, 78);
        contentPane.add(sp);
    
		
		JLabel answerLabel = new JLabel("Answer");
		answerLabel.setBounds(48, 233, 213, 32);
		answerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		answerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(answerLabel);
		
		JLabel opetion1Label = new JLabel("Option 1");//Wahhh
		opetion1Label.setBounds(10, 290, 90, 32);
		opetion1Label.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(opetion1Label);
		
		JLabel opetion2Label = new JLabel("Option 2");
		opetion2Label.setBounds(10, 341, 90, 32);
		opetion2Label.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(opetion2Label);
		
		JLabel opetion3Label = new JLabel("Option 3");
		opetion3Label.setBounds(10, 393, 90, 32);
		opetion3Label.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(opetion3Label);
		
		JLabel opetion4Label = new JLabel("Option 4");
		opetion4Label.setBounds(10, 449, 90, 32);
		opetion4Label.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(opetion4Label);
		
		ans1Text = new JTextField();
		ans1Text.setBounds(146, 297, 238, 29);
		contentPane.add(ans1Text);
		ans1Text.setColumns(10);
		
		ans2Text = new JTextField();
		ans2Text.setBounds(146, 348, 238, 29);
		ans2Text.setColumns(10);
		contentPane.add(ans2Text);
		
		ans3Text = new JTextField();
		ans3Text.setBounds(146, 400, 238, 29);
		ans3Text.setColumns(10);
		contentPane.add(ans3Text);
		
		ans4Text = new JTextField();
		ans4Text.setBounds(146, 456, 238, 29);
		ans4Text.setColumns(10);
		contentPane.add(ans4Text);
		
		ans1Radio = new JRadioButton("");
		ans1Radio.setBounds(390, 297, 21, 26);
		ans1Radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sgroup.clearSelection();   //any thing to check...??
				if(!ans4Text.isEnabled())
					ans4Text.setEnabled(true);
				
			}
		});
		contentPane.add(ans1Radio);
		
		ans2Radio = new JRadioButton("");
		ans2Radio.setBounds(391, 348, 21, 26);
		ans2Radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sgroup.clearSelection();// here also
				if(!ans4Text.isEnabled())
					ans4Text.setEnabled(true);
				
			}
		});
		contentPane.add(ans2Radio);
		ans3Radio = new JRadioButton("");
		ans3Radio.setBounds(390, 399, 21, 26);
		ans3Radio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sgroup.clearSelection();
				if(!ans4Text.isEnabled())
					ans4Text.setEnabled(true);
			}
		});
		contentPane.add(ans3Radio);
	    ans4Radio = new JRadioButton("");
	    ans4Radio.setBounds(390, 455, 21, 26);
	    ans4Radio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Sgroup.clearSelection();
	    		ans4Text.setEnabled(true);
	    		if(ans4Text.getText().equals("NONE OF ABOVE") || ans4Text.getText().equals("ALL OF ABOVE"))
				ans4Text.setText("");	
	    	}
	    });
		contentPane.add(ans4Radio);
		
		JLabel ansAllLabel = new JLabel("All of above");
		ansAllLabel.setBounds(121, 512, 114, 32);
		ansAllLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ansAllLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(ansAllLabel);
		
		ansAllRadio = new JRadioButton("");
		ansAllRadio.setBounds(240, 512, 21, 26);
		ansAllRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fgroup.clearSelection();
				ans4Text.setEnabled(false);
				ans4Text.setText("ALL OF ABOVE");	
			}
		});
		contentPane.add(ansAllRadio);
		
		JLabel ansNoneLabel = new JLabel("None of these");
		ansNoneLabel.setBounds(271, 512, 114, 32);
		ansNoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ansNoneLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(ansNoneLabel);
		
	    ansNoneRadio = new JRadioButton("");
	    ansNoneRadio.setBounds(390, 512, 21, 26);
	    ansNoneRadio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Fgroup.clearSelection();
	    		ans4Text.setEnabled(false);
				ans4Text.setText("NONE OF ABOVE");	
	    	}
	    });
		contentPane.add(ansNoneRadio);
		//creating group of radio buttons
		Fgroup=new ButtonGroup();
		Sgroup=new ButtonGroup();
		//*****************************//
		 //four simple ans group//
		 Fgroup.add(ans1Radio);
		 Fgroup.add(ans2Radio);
		 Fgroup.add(ans3Radio);
		 Fgroup.add(ans4Radio);
		 Fgroup.add(ansAllRadio);
		 Fgroup.add(ansNoneRadio);
		 
		 submitBtn = new JButton("Submit");
		 submitBtn.addKeyListener(new KeyAdapter() {
		 	 
		 	public void keyPressed(KeyEvent e) {
		 		if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			if(insertQuestion())
			 		{
			 			clear();
			 		}
		           
		        }
		 	}
		 });
		 submitBtn.setBounds(194, 572, 126, 41);
		 submitBtn.addMouseListener(new MouseAdapter() {
		 	 
		 	public void mouseClicked(MouseEvent arg0) {
		 		if(insertQuestion())
		 		{
		 			clear();
		 		}
		 	}
		 });
		 submitBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		 contentPane.add(submitBtn);
		 
		 myList = new DefaultListModel<String>();
		 tmpid=new DefaultListModel<Long>();
		 
		 tmplist=new JList<Long>();
		 tmplist.setVisible(false);
		 
		 JButton delete_btn = new JButton("Delete");
		 delete_btn.addKeyListener(new KeyAdapter() {
		 	 
		 	public void keyPressed(KeyEvent e) 
		 	{
		 		if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 		 deleteListItem();
				}
		 	}
		 });
		 delete_btn.addMouseListener(new MouseAdapter() {
		 	 
		 	public void mouseClicked(MouseEvent arg0) 
		 	{
		 		deleteListItem();
		 	}
		 });
		 delete_btn.setFont(new Font("Arial", Font.PLAIN, 12));
		 delete_btn.setBounds(509, 582, 126, 41);
		 contentPane.add(delete_btn);
		 
		 countLabel = new JLabel("");
		 countLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 countLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 countLabel.setBounds(512, 107, 308, 32);
		 contentPane.add(countLabel);
		 
		 optionsLabel = new JLabel("Options");
		 optionsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 optionsLabel.setBounds(807, 139, 551, 32);
		 contentPane.add(optionsLabel);
		 
		 aLabel = new JLabel("1");
		 aLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 aLabel.setBounds(807, 182, 119, 32);
		 contentPane.add(aLabel);
		 
		 bLabel = new JLabel("2");
		 bLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 bLabel.setBounds(807, 220, 119, 32);
		 contentPane.add(bLabel);
		 
		 cLabel = new JLabel("3");
		 cLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 cLabel.setBounds(807, 252, 119, 32);
		 contentPane.add(cLabel);
		 
		 dLabel = new JLabel("4");
		 dLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 dLabel.setBounds(807, 285, 119, 32);
		 contentPane.add(dLabel);
		 
		 totalLabel = new JLabel("");
		 totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 totalLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		 totalLabel.setBounds(508, 69, 308, 32);
		 contentPane.add(totalLabel);
		 list = new JList<String>();
		 list.addListSelectionListener(new ListSelectionListener() {
		 	public void valueChanged(ListSelectionEvent arg0) {
		 		if(arg0.getValueIsAdjusting()){
		 			displayQuestion();
		 			System.out.println("me");
		 		}
		 	}
		 });
		 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 list.setFont(new Font("Arial Black", Font.PLAIN, 12));
		 list.setBounds(509, 139, 271, 438);
		 contentPane.add(list);
		 listScroll = new JScrollPane(list);
	     listScroll.setBounds(509, 139, 271, 438);
	     contentPane.add(listScroll);
		 
		 setupExamBtn = new JButton("Setup Question");
		 setupExamBtn.addKeyListener(new KeyAdapter() {
		 	 
		 	public void keyPressed(KeyEvent e) {
		 		if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new SetupExam().setVisible(true);
					    kill();
		           
		        }
		 	}
		 });
		 setupExamBtn.addMouseListener(new MouseAdapter() {
		 	 
		 	public void mouseClicked(MouseEvent arg0) {
		 		 new SetupExam().setVisible(true);
				    kill();

		 	}
		 });
		 setupExamBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		 setupExamBtn.setBounds(5, 5, 119, 32);
		 contentPane.add(setupExamBtn);
		 
		 viewResultbtn = new JButton("View Result");
		 viewResultbtn.addKeyListener(new KeyAdapter() {
		 	 
		 	public void keyPressed(KeyEvent e) {
		 		if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
		 			 new ViewResult().setVisible(true);
					    kill();
		           
		        }
		 	}
		 });
		 viewResultbtn.addMouseListener(new MouseAdapter() {
		 	 
		 	public void mouseClicked(MouseEvent e) {
		 		new ViewResult().setVisible(true);
		 		kill();
		 	}
		 });
		 viewResultbtn.setFont(new Font("Arial", Font.PLAIN, 12));
		 viewResultbtn.setEnabled(true);
		 viewResultbtn.setBounds(141, 5, 112, 33);
		 contentPane.add(viewResultbtn);
		 
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
		 	 
		 	public void mousePressed(MouseEvent e) {
		 		new Home().setVisible(true);
		 		kill();
		 	}
		 });
		 homeBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		 homeBtn.setEnabled(true);
		 homeBtn.setBounds(276, 6, 112, 33);
		 contentPane.add(homeBtn);
		/////////////////////////
		formLoad();
		//contentPane.setVisible(false);
	}
	public void loadCategory()
	{
		ResultSet rs=null;
		Statement st;
		try
		{
			db=new ExamData();
			cn=db.getConnection();
	        st = cn.createStatement();
	        rs = st.executeQuery("Select c_name from  category order by c_name");
	        catCombo.removeAllItems();
	        catCombo.addItem("");
            while (rs.next())
	        {
            	catCombo.addItem(rs.getString(1));
	        }
            
            
		}
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
		
		
	}
	public void formLoad()
	{
		loadCategory();
	}
	public boolean insertQuestion()
	{
		String sql=null;
		ResultSet rs=null;
		Statement st;
		long cId=0,id=0,myans=0;
	  if(!onSubmitValiation())
	  {
		try
		{
			db=new ExamData();
			cn=db.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery("Select c_id from  category where c_name='"+((catCombo.getSelectedItem()).toString()).toUpperCase()+"'");
			if(rs.next())
				cId=rs.getLong(1);
			rs=st.executeQuery("select id from exam_questions order by id DESC limit 1 ");
			if(rs.next())
				id=rs.getLong(1)+1;
			else
				id=1;
			//////answer thing////////////////////
			int ans=0;
			if(ans1Radio.isSelected())
			{
				ans=1;
			}
			else if(ans2Radio.isSelected())
			{
				ans=2;
			}
			else if(ans3Radio.isSelected())
			{
				ans=3;
			}
			else if(ans4Radio.isSelected())
			{
				ans=4;
			}
			else if(ansAllRadio.isSelected())
			{
				ans=5;
			}
			else if(ansNoneRadio.isSelected())
			{
				ans=6;
			}
			
			sql="INSERT INTO exam_questions (ID, c_id, question, opetion_1, opetion_2, opetion_3, opetion_4, ans)values("+id+","+cId+",'"+QuestionText.getText().toUpperCase()+"','"+ans1Text.getText().toUpperCase()+"','"+ans2Text.getText().toUpperCase()+"','"+ans3Text.getText().toUpperCase()+"','"+ans4Text.getText().toUpperCase()+"',"+ans+")";
			System.out.println(sql);
			st.executeUpdate(sql);
		    listLoad();
		    
		    
		}
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
		return true;
	  }
	  return false;
	}	
	public void listLoad()
	{
		ResultSet rs=null;
		Statement st;
		long id=0;
		try
		{
			aLabel.setText("");
			bLabel.setText("");
			cLabel.setText("");
			dLabel.setText("");
			myList.clear();
			tmpid.clear();
		    st = cn.createStatement();
		    rs = st.executeQuery("Select c_id from  category where c_name='"+((catCombo.getSelectedItem()).toString()).toUpperCase()+"'");
		    if(rs.next())
		    	id=rs.getLong(1);
	        rs = st.executeQuery("Select question,id from  exam_questions where c_id="+id+" order by id");
	        list.removeAll();
	        int count=0;
	        while (rs.next())
	        {
	        	myList.addElement(rs.getString(1));
	        	tmpid.addElement((rs.getLong(2)));
	        	count++;
		    }
	        countLabel.setText("questions in current category: "+count);
	        list.setModel(myList);
	        tmplist.setModel(tmpid);
	        rs = st.executeQuery("Select COUNT(id) from exam_questions");
	        String t="";
	        if(rs.next())
	        t=rs.getString(1);
	        totalLabel.setText("Total quuestion:"+t);
	        
	            
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }
			
	}
	public void deleteListItem()
	{
	  if(catCombo.getSelectedItem().equals(""))
	  {
		  JOptionPane.showMessageDialog(null, "Select Category");
		  return;
	  }
	  else if(list.isSelectionEmpty())
	  {
		  JOptionPane.showMessageDialog(null, "Select Question from list");
	      return;     
	  }
	  if(!catCombo.getSelectedItem().equals("") && !list.isSelectionEmpty())
	  {
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog (null, "are you sure","Delete",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
		{
		ResultSet rs=null;
		Statement st;
		long lid=0;
        String t=list.getSelectedValue();
        lid=tmplist.getModel().getElementAt(list.getSelectedIndex());
		System.out.println(lid+" id "+t);
		try
		{
			st=cn.createStatement();
			System.out.println(st.executeUpdate("delete * from exam_questions where id ="+lid+""));
			list.removeAll();
			tmplist.removeAll();
			listLoad();
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
	        System.err.println(ex.getMessage());
        }
		}
	  }
		
	}
	public boolean onSubmitValiation()
	{
		String Blank="",msg="";
		boolean chk=false;
		if(catCombo.getSelectedItem().toString().equals(Blank))
		{
			msg="Please Select Category...!";
			chk=true;
			catCombo.requestFocusInWindow();
		}
		else if(QuestionText.getText().equals(Blank))
		{
			msg="Please Enter Question...!";
			QuestionText.requestFocusInWindow();
			chk=true;
		}
		else if(ans1Text.getText().equals(Blank))
		{
			msg="Please Enter Answer Option 1...! ";
			ans1Text.requestFocusInWindow();
			chk=true;
		}
		else if(ans2Text.getText().equals(Blank))
		{
			msg="Please Enter Answer Option 2...!";
			ans2Text.requestFocusInWindow();
			chk=true;
		}
		else if(ans3Text.getText().equals(Blank))
		{
			msg="Please Enter Answer Option 3...!";
			ans3Text.requestFocusInWindow();
			chk=true;
		}
		else if(ans4Text.getText().equals(Blank))
		{
			msg="Please Enter Answer Option 4...!";
			ans4Text.requestFocusInWindow();
			chk=true;
		}
		else if(Fgroup.getSelection() == null && Sgroup.getSelection() == null)
		{
			msg="Please select answer ...!";
			chk=true;
			ans1Radio.setSelected(true);
		}
		if(chk)
		JOptionPane.showMessageDialog(null,msg);
		return chk;
	}
	public void clear()
	{
		QuestionText.setText("");
		ans1Text.setText("");
		ans2Text.setText("");
		ans3Text.setText("");
		ans4Text.setText("");
		Sgroup.clearSelection();
		Fgroup.clearSelection();
		ans4Text.setEnabled(true);
	}
	public void displayQuestion()
	{
		if(!catCombo.getSelectedItem().equals("") && !list.isSelectionEmpty())
		  {
			
			ResultSet rs=null;
			Statement st;
			long lid=0;
	        String t=list.getSelectedValue();
	        lid=tmplist.getModel().getElementAt(list.getSelectedIndex());
			System.out.println(lid+" id "+t);
			try
			{
				st=cn.createStatement();
				st=cn.createStatement();
				rs=st.executeQuery("select * from exam_questions where id ="+lid+"");
				if(rs.next())
				{
					optionsLabel.setText(rs.getString(3));
					aLabel.setText(rs.getString(4));
					bLabel.setText(rs.getString(5));
					cLabel.setText(rs.getString(6));
					dLabel.setText(rs.getString(7));
					long color=rs.getLong(8);
					aLabel.setForeground(Color.BLACK);
					bLabel.setForeground(Color.BLACK);
					cLabel.setForeground(Color.BLACK);
					dLabel.setForeground(Color.BLACK);
					System.out.println(color);
					if(color==1)
						aLabel.setForeground(Color.GREEN);
					if(color==2)
						bLabel.setForeground(Color.GREEN);
					if(color==3)
						cLabel.setForeground(Color.GREEN);
					if(color>=4)
						dLabel.setForeground(Color.GREEN);
					
				}
			}
			catch (Exception ex)
			{
				System.err.print("Exception: ");
		        System.err.println(ex.getMessage());
	        }
		  
		  }
	}
	public void kill()
	{
		this.dispose();
	}
}
