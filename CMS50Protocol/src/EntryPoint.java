import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.core.PApplet;
import processing.serial.Serial;

public class EntryPoint extends JFrame{

	GraphicTest test;

	List portList;
	Button updateListBtn;
	Button selectBtn;
	Button closePortBtn;
	public EntryPoint(){
		super("test");
		test=new GraphicTest();
		setBounds(0, 0, 800+200, 600);
		setLayout(new BorderLayout());
		add(test,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.init();
		JPanel rg=new JPanel();
		portList=new List();
		updateListBtn=new Button("update");
		selectBtn=new Button("select");
		closePortBtn=new Button("close");
		rg.setBounds(0, 0, 200, 600);
		add(rg,BorderLayout.EAST);
		rg.setLayout(new FlowLayout());
		
		rg.add(portList);
		rg.add(updateListBtn);

		rg.add(selectBtn);
		rg.add(closePortBtn);
		updateComList();
		
		closePortBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				test.closePort();
			}
		});
		updateListBtn.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				updateComList();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		selectBtn.addMouseListener(new MouseListener() {
			

			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(portList.getSelectedItem()!=null)
				test.setPort(portList.getSelectedItem().toString());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	public void updateComList(){
		clearComList();
		for(String i :test.listPort() )
		portList.add(i);
	}
	public void clearComList(){
		portList.removeAll();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntryPoint m=new EntryPoint();
		m.setVisible(true);
	}
}
