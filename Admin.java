import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passText;
	ExamData db;
	Connection cn;
	Statement st;
	String sql,examName,ans4;
	ResultSet rs;
	JButton loginBtn;
	private JLabel passlbl;
	private JButton homeBtn;


	public static void main(String[] args) {

					Admin frame = new Admin();
					frame.setVisible(true);

	}

	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel main = new JPanel();
		main.setBounds(4, 3, 328, 215);
		contentPane.add(main);
		main.setLayout(null);
		
		JLabel mainlbl = new JLabel("Admin");
		mainlbl.setBounds(6, 5, 65, 25);
		main.add(mainlbl);
		
		JLabel userlbl = new JLabel("User");
		userlbl.setBounds(6, 56, 46, 25);
		main.add(userlbl);
		
		userText = new JTextField();
		userText.setBounds(77, 58, 86, 20);
		main.add(userText);
		userText.setColumns(10);
		//userlbl.setLabel()
		
		loginBtn = new JButton("Login");
		loginBtn.addKeyListener(new KeyAdapter() {
			 
			public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						 login();

			           
			        }
				}
		});
		loginBtn.addMouseListener(new MouseAdapter() {
			 
			public void mouseClicked(MouseEvent e) {
				System.out.println("click");
				login();
			}
		});
		loginBtn.setBounds(39, 161, 89, 23);
		main.add(loginBtn);
		
		passlbl = new JLabel("Password");
		passlbl.setBounds(6, 92, 65, 25);
		main.add(passlbl);
		
		passText = new JPasswordField();
		passText.setBounds(78, 92, 86, 20);
		passText.setEchoChar('*');
		main.add(passText);
		passText.setColumns(10);
		
		homeBtn = new JButton("Home");
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
		homeBtn.setBounds(229, 6, 89, 23);
		main.add(homeBtn);
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
	public void login()
	{
		try
        {
		 sql="select * from user1 where username='"+userText.getText()+"' and pass='"+passText.getText()+"'";
		 System.out.println(sql);
		 rs=st.executeQuery(sql);
		 if(rs.next())
		 {
			 System.out.println("Login success");
			 JOptionPane.showMessageDialog(null,"Login success..");
			 new SetupExam().setVisible(true);
			 kill();
		 }
		 else
		 {
			 System.out.println("retry");
			 JOptionPane.showMessageDialog(null,"User name or password is wrong...!");
			 
		 }
        }
		catch (Exception ex)
        {
			JOptionPane.showMessageDialog(null,"User name or password is wrong...!");
        }
		
	}
	public void kill()
	{
		this.dispose();
	}
}
