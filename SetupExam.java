import java.awt.Font;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;


public  class SetupExam extends JFrame {
	 JLabel status1;//temp status
	 JComboBox<String> cat_combo;//category
	 JComboBox<String> ename_combo;//
	 JLabel main_l;//main top lable where l is for label
	 JLabel cat_l;//category label
	 JLabel td_l;//time duration label 
	 JLabel noq_l;//number of questions 
	 JLabel mfeq_l;//marks for each question
	 JLabel nmfe_l;//Negative marks for each question
	 JSpinner noq_spin;//number of question spinner
	 JSpinner td_spin;//
	 JSpinner mfeq_spin;//
	 JSpinner nmfe_spin;
	 String catname,ename,mystr;//category name,exam name
	 long timed,nofq,mfeq,nmfe;//declaration
	 final JButton update_btn,sbmt_btn,delExam_btn,delcat_btn;
	 ExamData db;
	 Connection cn;
	 //long id;
	 private JPanel contentPane;//main panel
	 int i=1;
	 private JButton insertFormBtn;
	 private JButton viewResultBtn;
	 private JButton homeBtn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					SetupExam frame = new SetupExam();
					frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public SetupExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(-8, 90, 738, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		/*label section */
		contentPane.setLayout(null);
		//main label 
		
		JLabel main_l = new JLabel("Exam Setup");
	    main_l.setHorizontalAlignment(SwingConstants.CENTER);
	    main_l.setFont(new Font("Arial Black", Font.PLAIN, 20));
		main_l.setBounds(408, -3, 228, 42);
		getContentPane().add(main_l);
		//end of main label//
		
		//exam label->>
		JLabel ename_l = new JLabel("Exam Name");
		ename_l.setFont(new Font("Arial", Font.PLAIN, 15));
		ename_l.setBounds(16, 115, 162, 32);
		contentPane.add(ename_l);
		//end of this
		
		//status label->
		JLabel status1 = new JLabel("");
		status1.setBounds(45, 446, 78, 14);
		getContentPane().add(status1);
		//end of this //
		
		//category label->
		JLabel cat_l = new JLabel("1.Category");
		cat_l.setFont(new Font("Arial", Font.PLAIN, 15));
		cat_l.setBounds(16, 171, 162, 32);
		getContentPane().add(cat_l);
		//end of category label//
		
		//time duration label->
		JLabel td_l = new JLabel("2.Time Duration(mm)");
		td_l.setFont(new Font("Arial", Font.PLAIN, 15));
		td_l.setBounds(16, 221, 162, 32);
		getContentPane().add(td_l);
		//end of time duration label//
		
		//number of questions label->
		JLabel noq_l = new JLabel("3.Number of Questions");
		noq_l.setFont(new Font("Arial", Font.PLAIN, 15));
		noq_l.setBounds(13, 275, 165, 32);
		getContentPane().add(noq_l);
		//end of number of questions label//
		
		//marks for each question label->
		JLabel mfeq_l = new JLabel("4.Marks for each Question");
		mfeq_l.setFont(new Font("Arial", Font.PLAIN, 15));
		mfeq_l.setBounds(13, 327, 178, 32);
		getContentPane().add(mfeq_l);
		//end of marks for each question//
		
		
		//Negative marks label//
		JLabel nmfe_l = new JLabel("5.Negtive Mark For Each");
		nmfe_l.setFont(new Font("Arial", Font.PLAIN, 15));
		nmfe_l.setBounds(13, 384, 178, 32);
		getContentPane().add(nmfe_l);
		//end of this //
		
		//category combo->>
		cat_combo = new JComboBox<String>();
		cat_combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					if(!ename_combo.getSelectedItem().equals(""))
					{
						delcat_btn.setEnabled(true);
					}
				}
			}
		});
		cat_combo.setEditable(true);
		cat_combo.setBounds(237, 171, 208, 32);
		getContentPane().add(cat_combo);
		//end of this//
		
//        //exam name combo
		ename_combo = new JComboBox<String>();
		ename_combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) 
				{
					if(!ename_combo.getSelectedItem().equals(""))
					{
						display();
					}
				}
			}
		});
		ename_combo.setEditable(true);
		ename_combo.setBounds(237, 116, 208, 32);
		contentPane.add(ename_combo);
		//end of this 
		
        //Submit button->
		sbmt_btn = new JButton("Submit");
		sbmt_btn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(inserti())
					 {
						 fload();
					 }
		           
		        }
			}
		});
		sbmt_btn.addMouseListener(new MouseAdapter() { //button click event mouse
			 
			public void mouseClicked(MouseEvent arg0) {
		   // mystr=ename_combo.getSelectedItem().toString();
			 if(inserti())
			 {
				 fload();
			 }
			}
		});//end of this->event
		sbmt_btn.setFont(new Font("Arial", Font.PLAIN, 12));
		sbmt_btn.setBounds(228, 474, 126, 41);
		getContentPane().add(sbmt_btn);
		//end of this
		
		//update button
	
		//end of this// 
		
        // spinners->>>>>>>
		//number of question spinner
	    noq_spin = new JSpinner();
		noq_spin.setModel(new SpinnerNumberModel(10, 1, 100, 1));
		noq_spin.setBounds(237, 271, 208, 32);
		getContentPane().add(noq_spin);
		
		//time duration
		td_spin = new JSpinner();
		td_spin.setModel(new SpinnerNumberModel(new Integer(60), new Integer(10), null, new Integer(5)));
		td_spin.setBounds(237, 221, 208, 32);
		contentPane.add(td_spin);
		//end this//
		
		//marks for each question
	    mfeq_spin = new JSpinner();
	    mfeq_spin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		mfeq_spin.setBounds(237, 327, 208, 32);
		contentPane.add(mfeq_spin);
		//end this//
		
		//Negative marks for each
		nmfe_spin = new JSpinner();
		nmfe_spin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		nmfe_spin.setBounds(237, 384, 208, 32);
		contentPane.add(nmfe_spin);
		
		update_btn = new JButton("Update");
		update_btn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					update();
		           
		        }
				
			}
		});
		update_btn.setEnabled(false);
		update_btn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent arg0) {
				update();
			}
		});
		update_btn.setBounds(364, 474, 104, 41);
		contentPane.add(update_btn);
		
		delExam_btn = new JButton("Delete");
		delExam_btn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					delExam();
					fload();
		           
		        }
			}
		});
		delExam_btn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent arg0) {
				delExam();
				//JOptionPane.showMessageDialog(null,"return");
				fload();
			}
		});
		delExam_btn.setFont(new Font("Arial", Font.PLAIN, 12));
		delExam_btn.setEnabled(true);
		delExam_btn.setBounds(466, 115, 104, 32);
		contentPane.add(delExam_btn);
		
		delcat_btn = new JButton("Delete");
		delcat_btn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					deleteCategory();
					fload();
		           
		        }
			}
		});
		delcat_btn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				deleteCategory();
				//JOptionPane.showMessageDialog(null,"clicked");
				fload();
				
			}
		});
		delcat_btn.setFont(new Font("Arial", Font.PLAIN, 12));
		delcat_btn.setEnabled(true);
		delcat_btn.setBounds(466, 171, 104, 32);
		contentPane.add(delcat_btn);
		
		insertFormBtn = new JButton("Insert Questions");
		insertFormBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					 new InsertFrom().setVisible(true);
					    kill();

		           
		        }
			}
		});
		insertFormBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent arg0) 
	        {	
			    new InsertFrom().setVisible(true);
			    kill();
			}
		});
		insertFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		insertFormBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		insertFormBtn.setEnabled(true);
		insertFormBtn.setBounds(3, 5, 126, 32);
		contentPane.add(insertFormBtn);
		
		viewResultBtn = new JButton("View Result");
		viewResultBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						 new ViewResult().setVisible(true);
						    kill();
			        }
			}
		});
		viewResultBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new ViewResult().setVisible(true);
				kill();
			}
		});
		viewResultBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		viewResultBtn.setEnabled(true);
		viewResultBtn.setBounds(151, 4, 112, 33);
		contentPane.add(viewResultBtn);
		
		homeBtn = new JButton("Logout");
		homeBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				new Home().setVisible(true);
				kill();
			}
		});
		homeBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					 new Home().setVisible(true);
					    kill();
		        }
			}
		});
		homeBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		homeBtn.setEnabled(true);
		homeBtn.setBounds(286, 5, 112, 33);
		contentPane.add(homeBtn);
		
		//end of this// 
		
		fload();//calling form load;
		
	}//SetupExam();
	public boolean inserti()
	{
		/*1.exam with same name cannot be inserted 
		  2.if exam consist of existing category allowed 
		  3.new category will be directly inserted into category table
		  4.
		*/
		if(validation())
			return false;
		if(!validation())
		{
		String me=td_spin.getValue().toString();
		timed=Long.parseLong(me);
	    me= mfeq_spin.getValue().toString();
	    mfeq=Long.parseLong(me);
	    me=noq_spin.getValue().toString();
        nofq=Long.parseLong(me);
	    me=nmfe_spin.getValue().toString();
	    nmfe=Long.parseLong(me);
	    ////////////////////////
	    /*
        timed=Integer.parseInt();
        mfeq=(int) mfeq_spin.getValue();
        nmfe=(int) nmfe_spin.getValue();
        nofq = (int) noq_spin.getValue();*/
    	catname = (String)cat_combo.getSelectedItem();
    	catname=catname.toUpperCase();
    	ename=(String) ename_combo.getSelectedItem();
    	ename=ename.toUpperCase();
    	//status1.setText("hello");

    	long c_id = 0,id=0;
    	boolean flag=true,flag2=true;
    	Statement st;
    	String sql;
    	ResultSet rs;
		try
        {
		 //working with exam combo checking existing //
	     //{  
			 sql="select id,exam_name from exam_setup order by exam_name";
			 st=cn.createStatement();
			 rs=st.executeQuery(sql);
			 if(rs.next())
			      id=rs.getLong(1);
			 rs=st.executeQuery(sql);
			 if(!rs.wasNull())
			 {
				// System.out.println("inside if");
				 while(rs.next())
				 {
					// System.out.println(ename.equals(rs.getString(2)));
					 if((ename.equals(rs.getString(2)))==true)
					 {
						 System.out.println("Exam Already Exists...!");
						 flag2=false;
						 return false;
					 }
				 }
			 }
			 
		 //working with category table;
		 //{
			 sql="Select c_id from  category order by c_id DESC limit 1";
			// System.out.println("f1");
			 rs= st.executeQuery(sql);
			 if(rs.next())
			      c_id=rs.getLong(1);
			 else c_id=1;
			 sql="select c_id,c_name from category";
			 //System.out.println("f2");
			 rs=st.executeQuery(sql);
			 if(!rs.wasNull())
			 {
				 System.out.println("inside if");
				 while(rs.next())
				 {
					// System.out.println(catname.equals(rs.getString(2)));
					 if((catname.equals(rs.getString(2)))==true)
					 {
						 System.out.println("Category found inserting id ...!");
						 flag=false;
					 }
				 }
				 if(flag==true)
				 { 
					 c_id++;
					// System.out.println("statment de nede aa 22");
					 sql =	"INSERT INTO category(c_id,c_name) VALUES ("+c_id+",'"+catname+"')";
					 st.executeUpdate(sql);
					 System.out.println("New Category found Category /ninserted successfully ");
				 }
			}
		//}end of this
			if(flag2)
			{
			 sql="Select id from  exam_setup order by id DESC limit 1";
			 rs= st.executeQuery(sql);
			 if(rs.next())
			  id=rs.getLong(1)+1;
			 else id=1;
			  if(mfeq<nmfe)// this can be skipped 
			  {
				  JOptionPane.showMessageDialog(null,"Negtive Marks Should less than question mark...!");
				  return false;
			  }
			  sql =	"INSERT INTO exam_setup (id,exam_name,c_id,time,mark_each,negtive_mark,number_of_questions) VALUES ("+id+",'"+ename+"',"+c_id+","+timed+","+mfeq+","+nmfe+","+nofq+")";
			  st = cn.createStatement() ;
			  st.executeUpdate(sql);
			  System.out.println("Exam inserted");
			  return true;
			}
         } 
		catch (Exception ex)
         {
			System.err.print("Exception: ");
            System.err.println(ex.getMessage());
         }
		}
		//System.out.println("insert called");
		return true;
	
	}//end of inserti();
	public void fload()  
	{
		//form load//
		
		try
        {
		 db=new ExamData();
		 cn=db.getConnection();
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery("Select c_name from  category order by c_name");
         cat_combo.removeAllItems();
         ename_combo.removeAllItems();
         ename_combo.addItem("");
         cat_combo.addItem("");
         update_btn.setEnabled(false);
         sbmt_btn.setEnabled(true);
		 delExam_btn.setEnabled(false);
		 delcat_btn.setEnabled(false);
         //update_btn.setEnabled(false);
        // update_btn.setEnabled(false);//;("")

         while (rs.next())
            {
        	 cat_combo.addItem(rs.getString(1));
            }
         rs = st.executeQuery("Select exam_name from  exam_setup order by exam_name");
         while (rs.next())
            {
        	 ename_combo.addItem(rs.getString(1));
            }
	    } 
		catch (Exception ex)
        {
              System.err.print("Exception: ");
              System.err.println(ex.getMessage());
        }
	}//end of fload();
	public void display()//when existing exam is selected will be updated;
	{
		//sbmt_btn.setEnabled(false);
    	Statement st;
    	String sql,tmp="";
    	ResultSet rs;
    	boolean flag=true;
    	long id=0,c_id=0;
		try
        {
			 String b="";
			 System.out.println("lalalallallalalal");
		   //Retrieving existing data
			 sql="select * from exam_setup where exam_name='"+ename_combo.getSelectedItem()+"' limit 1";
			 st=cn.createStatement();
			 rs=st.executeQuery(sql);
			 update_btn.setEnabled(false);
			 sbmt_btn.setEnabled(true);
			// rs= db.selectCols("", "exam_setup","");
			 if(rs.next())
			 {
			      id=rs.getLong(1);
			 System.out.println(id);
			 rs=st.executeQuery(sql);
			 //displaying data into fields
			 if(rs.next())
			 {
				 update_btn.setEnabled(true);
				 sbmt_btn.setEnabled(false);
				 delExam_btn.setEnabled(true);
				 td_spin.setValue(rs.getLong(3));
				 mfeq_spin.setValue(rs.getLong(4));
				 nmfe_spin.setValue(rs.getLong(5));
				 noq_spin.setValue(rs.getLong(6));
			 }
			 cat_combo.removeAllItems();//clearing category combo
			 //Retrieving current category 
			 rs = st.executeQuery("Select c_name from  category where c_id="+rs.getLong(2)+" limit 1");
			 if(rs.next())
			    tmp=rs.getString(1);
			    cat_combo.addItem(tmp);
			 //ithis end //
			 //Retrieving rest of categories 
			 rs = st.executeQuery("Select c_name from  category order by c_name");
			  while (rs.next())
	            {
				 if(!rs.getString(1).equals(tmp))
				 {
	        	  cat_combo.addItem(rs.getString(1));
	        	  //System.out.println(rs.getString(1));
				 }
	            }
			 catname = (String)cat_combo.getSelectedItem();
		     catname=catname.toUpperCase();	 
			 }			 
		}//try end
		catch (Exception ex)
        {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
        }//end of catch 
	}//end of display
	public void update()
	{
		if(!validation())
		{	
		update_btn.setEnabled(true);
		System.out.println("called");
		String sql=null;
		ResultSet rs=null;
		long c_id=0,id=0;
		Statement st;
		boolean flag=true;
		//updating data
	     //*****************************************************************************
		try
		{
			sql="select * from exam_setup where exam_name='"+ename_combo.getSelectedItem()+"' limit 1";
			 st=cn.createStatement();
			 rs=st.executeQuery(sql);
			 if(rs.next())
				 id=rs.getLong(1);
			 sql="Select c_id from  category order by c_id DESC limit 1";
			// System.out.println("f1");
	         st=cn.createStatement();
			 rs= st.executeQuery(sql);
			 System.out.println("statment 2");
			 if(rs.next())
			      c_id=rs.getLong(1);
			 else c_id=1;
			 System.out.println(c_id);
			 sql="select c_id,c_name from category";
			 //System.out.println("f2");
			 rs=st.executeQuery(sql);
			 catname=(cat_combo.getSelectedItem().toString()).toUpperCase();
			 if(!rs.wasNull())
			 {
				 System.out.println("inside if");
				 while(rs.next())
				 {
					// System.out.println(catname.equals(rs.getString(2)));
					 if((catname.equals(rs.getString(2)))==true)
					 {
						 System.out.println("Category found inserting id ");
						 flag=false;
					 }
				 }
				 if(flag==true)
				 { 
					 c_id++;
					// System.out.println("statment de nede aa 22");
					 sql =	"INSERT INTO category(c_id,c_name) VALUES ("+c_id+",'"+catname+"')";
					 st.executeUpdate(sql);
					 System.out.println("New Category found Category /ninserted successfully ");
				 }
			}
	     //*******************************************************************************
		//	 updating stuff
	    //find current c_id 
	    String uptmp=cat_combo.getSelectedItem().toString();
	    uptmp=uptmp.toUpperCase();
	     System.out.println(uptmp);
		sql="Select c_id from  category where c_name='"+uptmp+"' limit 1";
		st=cn.createStatement();
		rs= st.executeQuery(sql);
		 System.out.println("statment 2");
		if(rs.next())
		 c_id=rs.getLong(1);
		System.out.println(c_id);
		PreparedStatement pst ;
	    pst = cn.prepareStatement("UPDATE exam_setup SET c_id = ?,time = ?,mark_each=?,number_of_questions=?,negtive_mark=?  WHERE id = ?");
	    pst.setLong(1, c_id);
	    String me=td_spin.getValue().toString();
	    Long my=Long.parseLong(me);
	    pst.setLong(2,my);
	    me= mfeq_spin.getValue().toString();
	    my=Long.parseLong(me);
	    pst.setLong(3,my);
	    me=noq_spin.getValue().toString();
	    my=Long.parseLong(me);
	    pst.setLong(4,my);
	    me=nmfe_spin.getValue().toString();
	    my=Long.parseLong(me);
	    pst.setLong(5,my);
	    pst.setLong(6,id);
	    System.out.println("id->"+id);
	    pst.executeUpdate();  
	    fload();
		}//try end
		catch (Exception ex)
		{
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		}//end of catch
		}//end of this function  
	}   
	public void delExam()
	{
	   long eid=0;
	   String s="";
	   if(!((ename_combo.getSelectedItem().toString()).toUpperCase()).equals(s))
	   {
		try
		{
			Statement st = cn.createStatement();
			ResultSet rs=st.executeQuery("select id from exam_setup where exam_name='"+ename_combo.getSelectedItem().toString().toUpperCase()+"' limit 1");
			if(rs.next())
				eid=rs.getLong(1);
			else
			{
				JOptionPane.showMessageDialog(null,"There is no such a exam");
				return;
			}
			System.out.println(eid);
	        st.executeUpdate("delete * from exam_setup where id ="+eid+"");
		}
		catch (Exception ex)
		{
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		}
	   }
	   else
		   System.out.println("returning");
	}
	public void deleteCategory()//deleting category will delete all exams consist of current category,questions and current selected category;
	{
		long cid=0;
		String s="";
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog (null, "This will delete everything releted to current selected category","Delete",dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION)
		{
		   if(!((cat_combo.getSelectedItem().toString()).toUpperCase()).equals(s))
		   {
			try
			{
				Statement st = cn.createStatement();
				ResultSet rs=st.executeQuery("select c_id from category where c_name='"+cat_combo.getSelectedItem().toString().toUpperCase()+"' limit 1");
				if(rs.next())
					cid=rs.getLong(1);
				else
				{
					JOptionPane.showMessageDialog(null,"There is no such a category");
					return;
				}
				System.out.println(cid);
		        st.executeUpdate("delete * from exam_setup where c_id ="+cid+"");
		        st.executeUpdate("delete * from exam_questions where c_id ="+cid+"");
		        st.executeUpdate("delete * from category where c_id="+cid+"");
			}
			catch (Exception ex)
			{
				System.err.print("Exception: ");
				System.err.println(ex.getMessage());
			}
		   }
		   else
			   System.out.println("returning");
		}
	}//end del exam
	public boolean validation()
	{
		String Blank="",msg="";
		boolean chk=false;
		System.out.println();
		if(ename_combo.getSelectedItem().toString().equals(Blank))
		{
			msg="please select exam";
			chk=true;
			ename_combo.requestFocusInWindow();
		}
		
		else if(cat_combo.getSelectedItem().toString().equals(Blank))
		{
			msg="please select category";
			chk=true;
			cat_combo.requestFocusInWindow();
		}
		if(chk)
		JOptionPane.showMessageDialog(null,msg);
		return chk;
	}
	public void clear()
	{
		 cat_combo.removeAllItems();
         ename_combo.removeAllItems();
	}
	public void kill()
	{
		this.dispose();
	}
}//end of class
