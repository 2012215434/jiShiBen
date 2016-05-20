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
	private JFrame JFrameMain = new JFrame();//�����ڣ�
	private JTextArea JTextArea =new JTextArea();//input text area ;
	private JMenuBar JMenuBar= new JMenuBar(); //�˵�����
	private String  path ;//the selected file's path;
	String  data ; //used to restore read text;
	
	
//��һ������ı�����=======================================================
	private String s1;//���ڴ洢��ȡJTextArea ���ı���
	private int seat = 0;//��ʼ������λ�ã�
	private ImageIcon background;
	private JPanel imagePanel;
	
	//���������
	private JTextField JTextField1=new JTextField(15);
	private JTextField JTextField2=new JTextField(15);
	
			
	//���������еİ�ť
	private JButton JButtonK=new JButton("��ʼ");
	private JButton JButtonT=new JButton("�滻Ϊ");
		
		
//===================================================================
		
	
	//the method to initial window;
	public void initial()
	{
		
//��һ��================================================================
		//��������ʼ���͡��滻Ϊ��������ť
		JButtonK.addActionListener(this);
		JButtonT.addActionListener(this);
//=====================================================================
				
				
		//this array is used to initial object as name;
		String[] MenuText = {"�ļ�","�༭","��ʽ","����"};
		
		//this array is used to initial MenuItem object as name ;
		String[][] MenuItemText = {{"�½�","��","����","���Ϊ","�˳�"},
				{"����","ճ��","����","�滻"},{"�Զ�����","����"} ,
				{ "���ڼ��±�"} };
		
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
             if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
            	 FileReader fileReader = new FileReader(path);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 String lineText = null;
                 while((lineText = bufferedReader.readLine()) != null){
                     data= data + lineText;
                     data = data+ " \n" ;
                 }
                 bufferedReader.close();
             }else{
            	 	System.out.println("�Ҳ���ָ�����ļ�");
             }
     } catch (Exception e) {
         System.out.println("��ȡ�ļ����ݳ���");
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
		
		if("�½�".equals(Command)){
			JFileChooser jfc=new JFileChooser();
			 int returnVal = jfc.showDialog(null,"����Ϊ");
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			        File f=jfc.getSelectedFile();
			        path = f.getPath();
					write();
					newNew();
			 }
		
		}else if("��".equals(Command)){
			
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
		}else if("����".equals(Command)){
			
			JFileChooser JFileChooser=new JFileChooser();
			JFileChooser.setFileFilter(new fileFilter("txt"));
			
		    int returnVal = JFileChooser.showDialog(null,"����Ϊ");
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				           
				File f=JFileChooser.getSelectedFile();
				path = f.getPath() ;
				write();
			}		
			
		}else if("���Ϊ".equals(Command)){
			JFileChooser JFileChooser=new JFileChooser();
			JFileChooser.setFileFilter(new fileFilter("txt"));
			
			 int returnVal = JFileChooser.showDialog(null,"���");
			 if(returnVal == JFileChooser.APPROVE_OPTION) {
			      File f=JFileChooser.getSelectedFile();
			      path = f.getPath();
			      write();
			 }
			
		}else if("�˳�".equals(Command)){
			System.exit(0);
		}
		else if("�Զ�����".equals(Command)){
			JTextArea.setLineWrap(true);//set change line automatic;
			
		}else if("����".equals(Command)){
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
	        	
	            System.out.println("ȡ���˻�û����ѡ��");
	        }
		}else if("���ڼ��±�".equals(Command)){
			JFrame JFrameAboutNoteBook = new JFrame("���ڼ��±�");
		    JTextArea JTextAreaAboutNoteBook = new JTextArea(); 
		    
			String str ="Windows " + JFrameAboutNoteBook.getTitle() ;
			JTextAreaAboutNoteBook.setText(str);
			JTextAreaAboutNoteBook.setEditable(false);
			JScrollPane JScrollPane = new JScrollPane(JTextAreaAboutNoteBook);
			JFrameAboutNoteBook.add(JScrollPane);
			
			JFrameAboutNoteBook.setLocation(300,300);//�������ڵ�λ��
			JFrameAboutNoteBook.setSize(200,100);
			JFrameAboutNoteBook.setVisible(true);
			JFrameAboutNoteBook.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
//��һ��===============================================================
		   else if("����".equals(Command))
			{
				JTextArea.copy();//���������ֱ�Ӱ�ѡ�е����ָ��Ƶ�ϵͳ���а���
			}
			else if("ճ��".equals(Command))
			{
				JTextArea.paste();//���������ֱ�Ӱ�ϵͳ���а�������ָ��Ƴ���
			}
			else if("����".equals(Command))
			{
				//��JFrame��������󵯳��Ĵ���
			    JFrame JFrameC=new JFrame("����");
				
				s1=JTextArea.getText();//getText()���������ݴ��ڿؼ��� �����ڵ�ǰ����֮�ϵı༭���е��ı�
				
				JFrameC.add(JTextField1,BorderLayout.CENTER);//����������Ҫ���ҵ��ַ�
				JFrameC.add(JButtonK,BorderLayout.NORTH);//�����ťִ�в���
				
				JFrameC.setLocation(300,300);//�������ڵ�λ��
				JFrameC.pack();//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 
				JFrameC.setVisible(true);
				JFrameC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				background = new ImageIcon("���ұ���ͼ.jpg");// ����ͼƬ·��
				JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
				// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
				// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
				imagePanel = (JPanel) JFrameC.getContentPane();
				imagePanel.setOpaque(false);
				// ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout
				imagePanel.setLayout(new FlowLayout());

				JFrameC.getLayeredPane().setLayout(null);
				// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
				JFrameC.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
				JFrameC.setSize(background.getIconWidth(), background.getIconHeight());
				JFrameC.setResizable(false);
				JFrameC.setVisible(true);
			}
			else if("��ʼ".equals(Command))
			{
				String string1=JTextField1.getText();//string1����Ҫ�������ַ���
				int s;
				s = s1.indexOf(string1,seat);//seat�ǴӺδ���ʼ����
				
				JTextArea.setSelectionStart(s);//�����������ֵĿ�ͷ���ڵ�λ��
				JTextArea.setSelectionEnd(s+string1.length());//�����������ֵ�ĩβ���ڵ�λ��
				JTextArea.setSelectedTextColor(Color.RED);//�����������ֱ�ǿ��������ɫ
				seat=s+1;
				
			}
			else if("�滻".equals(Command))
			{
				//��JFrame��������󵯳��Ĵ���
				JFrame JFrameT=new JFrame("�滻");
				
				s1=JTextArea.getText();
				GridLayout gl=new GridLayout(3,3);//���ð���
				JLabel JLabel1=new JLabel("��������:");
				JLabel JLabel2=new JLabel("�滻Ϊ:");
				
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
				
				background = new ImageIcon("�滻����ͼ.jpg");// ����ͼƬ·��
				JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ��Label����
				// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
				label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
				// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
				imagePanel = (JPanel) JFrameT.getContentPane();
				imagePanel.setOpaque(false);
				

				JFrameT.getLayeredPane().setLayout(null);
				// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
				JFrameT.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
				JFrameT.setSize(background.getIconWidth(), background.getIconHeight());
				JFrameT.setResizable(false);
				JFrameT.setVisible(true);
			}
			else if("�滻Ϊ".equals(Command))
			{
				String string1=JTextField1.getText();
				int s=s1.indexOf(string1,seat);
				
				if(s1.indexOf(string1,seat)!=-1)
				{
				    //���滻һ������
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
