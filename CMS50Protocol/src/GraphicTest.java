import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sql.PooledConnection;
import javax.swing.plaf.SliderUI;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import processing.core.*;
import processing.serial.*;
import processing.data.*;
public class GraphicTest extends PApplet{

	Serial serial;
	CMS50 device=new CMS50();
	circularBuffer<CMS50Packet> buffer=new circularBuffer<CMS50Packet>(1024);
	Thread pollData=new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(true){
			// TODO Auto-generated method stub
				//print("start");
				
				if(serial!=null)
				buffer.add(device.parse(serial.input));
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	});
	/**
	 * 
	 */
	private static final long serialVersionUID = 4986917521716650096L;
	public void closePort(){

		if(serial!=null){serial.stop();
		serial.dispose();
		}
		serial=null;
		
	}
	public void setPort(String portName){
		if(portName!="");
		println(portName);
		if(serial!=null) serial.stop();
		serial=new Serial(this,portName,19200,'O',8,1);
		
	}
	public String[] listPort(){
		String[] portList=Serial.list();
		for(String i : portList){
			println(i);
		}
		return portList;
	}
	public void setup() {

		size(800,600);
		
		background(255);
		color(255);
		size(800,600);
		frameRate(24);
		smooth();
		pollData.start();
		
	}
	
	public void draw() {
		if(serial!=null){
			ArrayList<CMS50Packet> tmp=buffer.getBuffer();
			drawWaveform(0,0,800,100,tmp);
			drawSignalStrength(0, 100, 800, 100, tmp);
		}
			
		
	}
	
	public void drawWaveform(int x,int y,int x1,int y1,ArrayList<CMS50Packet> tmp){
		//print("start");
		
		int l=tmp.size();
		pushMatrix();
		translate(x, y);

		fill(255);
		stroke(255);
		rect(0,0,x1,y1);
		stroke(0);
		line(0,0,x1,0);
		line(0,y1,x1,y1);
		float xx=0,yy=0,pxx=0,pyy=0;
		for(int posX=0;posX<tmp.size();posX++){
		
		stroke(0);
		strokeWeight(1);
		int posY=0;
		if(tmp.get(posX)!=null)
			{
			posY=tmp.get(posX).pulsewaveform;
			
			}
		posY=constrain(posY, 0, 128);
		 xx=map(posX,0,l,0,x1); yy=-map(posY, 0, 129, 0, y1)+y1;
		//println(posY);
		line(xx,yy,pxx,pyy);
		pxx=xx;pyy=yy;
		}
		popMatrix();
	}
	public void drawSignalStrength(int x,int y,int x1,int y1,ArrayList<CMS50Packet> tmp){
//print("start");
		
		int l=tmp.size();
		pushMatrix();
		translate(x, y);

		fill(255);
		stroke(255);
		rect(0,0,x1,y1);
		stroke(0);
		line(0,0,x1,0);
		line(0,y1,x1,y1);
		float xx=0,yy=0,pxx=0,pyy=0;
		for(int posX=0;posX<tmp.size();posX++){
		
		stroke(0);
		strokeWeight(1);
		int posY=0;
		if(tmp.get(posX)!=null)
			{
			posY=tmp.get(posX).signalstrength;
			
			}
		posY=constrain(posY, 0, 128);
		 xx=map(posX,0,l,0,x1); yy=-map(posY, 0, 8, 0, y1)+y1;
		//println(posY);
		line(xx,yy,pxx,pyy);
		pxx=xx;pyy=yy;
		}
		popMatrix();
	}
}