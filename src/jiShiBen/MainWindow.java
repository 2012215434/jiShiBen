package jiShiBen;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener{
	
	//create the window;
	public MainWindow()
	{
		//call initial method to initial the window;
		initial();
	}
	
	//components;
	private JFrame JFrameMain = new JFrame();//主窗口；
	private JTextArea JTextArea =new JTextArea();//input text area ;
	private JMenuBar JMenuBar= new JMenuBar(); //菜单栏；
	private String  path ;//the selected file's path;
	String  data ; //used to restore read text;
	
	
//顾一凡定义的变量；=======================================================
	private String s1;//用于存储获取JTextArea 的文本；
	private int seat = 0;//开始搜索的位置；
	private ImageIcon background;
	private JPanel imagePanel;
	
	//两个输入框
	private JTextField JTextField1=new JTextField(15);
	private JTextField JTextField2=new JTextField(15);
	
			
	//弹出窗口中的按钮
	private JButton JButtonK=new JButton("开始");
	private JButton JButtonT=new JButton("替换为");
		
		
//===================================================================
		
	
	//the method to initial window;
	public void initial()
	{
		
//顾一凡================================================================
		//监听“开始”和“替换为”两个按钮
		JButtonK.addActionListener(this);
		JButtonT.addActionListener(this);
//=====================================================================
				
				
		//this array is used to initial object as name;
		String[] MenuText = {"文件","编辑","格式","帮助"};
		
		//this array is used to initial MenuItem object as name ;
		String[][] MenuItemText = {{"新建","打开","保存","另存为","退出"},
				{"复制","粘贴","查找","替换"},{"自动换行","字体"} ,
				{ "关于记事本"} };
		
		//to create JMenuItem object;
		for(int i=0 ;i< MenuText.length;i++){
			JMenu JMenu = new JMenu(MenuText[i]);
			for(int j =0;j< MenuItemText[i].length;j++){
				JMenuItem JMenuItem = new JMenuItem(MenuItemText[i][j]);
				JMenu.add(JMenuItem);
				JMenuItem.addActionListener(this);
			}
			JMenuBar.add(JMenu);
		}
		
		JFrameMain.setJMenuBar(JMenuBar);
		
		JTextArea.setLineWrap(true);//set change line automatic;
		JScrollPane JScrollPane = new JScrollPane(JTextArea);
		JFrameMain.add(JScrollPane);
		
		JFrameMain.setLocation(200,50);
		JFrameMain.setSize(620,660);
		JFrameMain.setVisible(true);
		JFrameMain.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	
	}
	
	//new MainWindow;
	public void newNew(){
		JFrameMain.dispose();
		new MainWindow();
	}

	//read  the text from file;
	public void read() throws IOException{
		 try {
			 File file=new File(path);
             if(file.isFile() && file.exists()){ //判断文件是否存在
            	 FileReader fileReader = new FileReader(path);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 String lineText = null;
                 while((lineText = bufferedReader.readLine()) != null){
                     data= data + lineText;
                     data = data+ " \n" ;
                 }
                 bufferedReader.close();
             }else{
            	 	System.out.println("找不到指定的文件");
             }
     } catch (Exception e) {
         System.out.println("读取文件内容出错");
         e.printStackTrace();
     }
	}
	
	//write text in file ;
	public void write(){
		try {
			FileOutputStream fos=new FileOutputStream(path);
			fos.write(JTextArea.getText().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String Command = e.getActionCommand();
		
		if("新建".equals(Command)){
			JFileChooser jfc=new JFileChooser();
			 int returnVal = jfc.showDialog(null,"保存为");
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			        File f=jfc.getSelectedFile();
			        path = f.getPath();
					write();
					newNew();
			 }
		
		}else if("打开".equals(Command)){
			
			JFileChooser JFileChooser =new JFileChooser();
			JFileChooser.addChoosableFileFilter(new fileFilter("class"));
			JFileChooser.addChoosableFileFilter(new fileFilter("java"));
			JFileChooser.addChoosableFileFilter(new fileFilter("txt"));
			
			JFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			int returnVal = JFileChooser.showOpenDialog(null);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = JFileChooser.getSelectedFile();
	            //This is where a real application would open the file.
	            JFrameMain.setTitle(file.getName());
	            
	            //this is the chosen file's path
	            path = file.getPath();
	            
	            //read the text from chosen file
	            try {
					read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            
	            JTextArea.setText(data);
	           
	        }	
		}else if("保存".equals(Command)){
			
			JFileChooser JFileChooser=new JFileChooser();
			JFileChooser.setFileFilter(new fileFilter("txt"));
			
		    int returnVal = JFileChooser.showDialog(null,"保存为");
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				           
				File f=JFileChooser.getSelectedFile();
				path = f.getPath() ;
				write();
			}		
			
		}else if("另存为".equals(Command)){
			JFileChooser JFileChooser=new JFileChooser();
			JFileChooser.setFileFilter(new fileFilter("txt"));
			
			 int returnVal = JFileChooser.showDialog(null,"另存");
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			      File f=JFileChooser.getSelectedFile();
			      path = f.getPath();
			      write();
			 }
			
		}else if("退出".equals(Command)){
			System.exit(0);
		}
		else if("自动换行".equals(Command)){
			JTextArea.setLineWrap(true);//set change line automatic;
			
		}else if("字体".equals(Command)){
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        JList fontNames = new JList(ge.getAvailableFontFamilyNames());

	        int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontNames));
	        Object selectedFont = fontNames.getSelectedValue();
	        Font  font = fontNames.getFont();
	        if (response == JOptionPane.YES_OPTION && selectedFont != null)
	        {
	        	JTextArea.setFont(font);
	        }
	        else{
	        	
	            System.out.println("取消了或没作出选择");
	        }
		}else if("关于记事本".equals(Command)){
			JFrame JFrameAboutNoteBook = new JFrame("关于记事本");
		    JTextArea JTextAreaAboutNoteBook = new JTextArea(); 
		    
			String str ="Windows " + JFrameAboutNoteBook.getTitle() ;
			JTextAreaAboutNoteBook.setText(str);
			JTextAreaAboutNoteBook.setEditable(false);
			JScrollPane JScrollPane = new JScrollPane(JTextAreaAboutNoteBook);
			JFrameAboutNoteBook.add(JScrollPane);
			
			JFrameAboutNoteBook.setLocation(300,300);//弹出窗口的位置
			JFrameAboutNoteBook.setSize(200,100);
			JFrameAboutNoteBook.setVisible(true);
			JFrameAboutNoteBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
//顾一凡===============================================================
		   else if("复制".equals(Command))
			{
				JTextArea.copy();//这个操作能直接把选中的文字复制到系统剪切板里
			}
			else if("粘贴".equals(Command))
			{
				JTextArea.paste();//这个操作能直接把系统剪切板里的文字复制出来
			}
			else if("查找".equals(Command))
			{
				//用JFrame创建点击后弹出的窗口
			    JFrame JFrameC=new JFrame("查找");
				
				s1=JTextArea.getText();//getText()：返回数据窗口控件中 悬浮在当前行列之上的编辑框中的文本
				
				JFrameC.add(JTextField1,BorderLayout.CENTER);//这个框框输入要查找的字符
				JFrameC.add(JButtonK,BorderLayout.NORTH);//这个按钮执行查找
				
				JFrameC.setLocation(300,300);//弹出窗口的位置
				JFrameC.pack();//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 
				JFrameC.setVisible(true);
				JFrameC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				background = new ImageIcon("查找背景图.jpg");// 背景图片路径
				JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
				// 把标签的大小位置设置为图片刚好填充整个面板
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
				// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
				imagePanel = (JPanel) JFrameC.getContentPane();
				imagePanel.setOpaque(false);
				// 内容窗格默认的布局管理器为BorderLayout
				imagePanel.setLayout(new FlowLayout());

				JFrameC.getLayeredPane().setLayout(null);
				// 把背景图片添加到分层窗格的最底层作为背景
				JFrameC.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
				JFrameC.setSize(background.getIconWidth(), background.getIconHeight());
				JFrameC.setResizable(false);
				JFrameC.setVisible(true);
			}
			else if("开始".equals(Command))
			{
				String string1=JTextField1.getText();//string1是需要检索的字符串
				int s;
				s = s1.indexOf(string1,seat);//seat是从何处开始检索
				
				JTextArea.setSelectionStart(s);//检索到的文字的开头所在的位置
				JTextArea.setSelectionEnd(s+string1.length());//检索到的文字的末尾所在的位置
				JTextArea.setSelectedTextColor(Color.RED);//检索到的文字被强调出的颜色
				seat=s+1;
				
			}
			else if("替换".equals(Command))
			{
				//用JFrame创建点击后弹出的窗口
				JFrame JFrameT=new JFrame("替换");
				
				s1=JTextArea.getText();
				GridLayout gl=new GridLayout(3,3);//设置版面
				JLabel JLabel1=new JLabel("查找内容:");
				JLabel JLabel2=new JLabel("替换为:");
				
				JFrameT.setLayout(gl);
				JFrameT.add(JLabel1);
				JFrameT.add(JTextField1);
				JFrameT.add(JButtonK);
				JFrameT.add(JLabel2);
				JFrameT.add(JTextField2);
				JFrameT.add(JButtonT);
				
				JLabel JLabel3=new JLabel();
				JLabel JLabel4=new JLabel();
				JFrameT.add(JLabel3);
				JFrameT.add(JLabel4);						
				
				JFrameT.setLocation(300,300);
				JFrameT.pack();
				JFrameT.setVisible(true);
				JFrameT.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				background = new ImageIcon("替换背景图.jpg");// 背景图片路径
				JLabel label = new JLabel(background);// 把背景图片显示在一个Label里面
				// 把标签的大小位置设置为图片刚好填充整个面板
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
				// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
				imagePanel = (JPanel) JFrameT.getContentPane();
				imagePanel.setOpaque(false);
				

				JFrameT.getLayeredPane().setLayout(null);
				// 把背景图片添加到分层窗格的最底层作为背景
				JFrameT.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
				JFrameT.setSize(background.getIconWidth(), background.getIconHeight());
				JFrameT.setResizable(false);
				JFrameT.setVisible(true);
			}
			else if("替换为".equals(Command))
			{
				String string1=JTextField1.getText();
				int s=s1.indexOf(string1,seat);
				
				if(s1.indexOf(string1,seat)!=-1)
				{
				    //可替换一串文字
					JTextArea.setSelectionStart(s);
					JTextArea.setSelectionEnd(s+string1.length());
					
					
					JTextArea.setSelectedTextColor(Color.blue);
					seat=s+1;
					
					//Replaces the currently selected content with new content represented by the given string.
					JTextArea.replaceSelection(JTextField2.getText());
				}
			}
//============================================================================
		
	}
	
	public static void main(String args[]){
		new MainWindow();
	}
}
