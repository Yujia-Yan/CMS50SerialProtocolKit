import java.io.IOException;
import java.io.InputStream;

//byte in java is signed, we use int
public class CMS50 {
	static final int flagAllOff = 0;  //         000...00000000 (empty mask)
	static final int flagbit0 = 1;    // 2^^0    000...00000001
	static final int flagbit1 = 2;    // 2^^1    000...00000010
	static final int flagbit2 = 4;    // 2^^2    000...00000100
	static final int flagbit3 = 8;    // 2^^3    000...00001000
	static final int flagbit4 = 16;   // 2^^4    000...00010000
	static final int flagbit5 = 32;   // 2^^5    000...00100000
	static final int flagbit6 = 64;   // 2^^6    000...01000000
	static final int flagbit7 = 128;   // 2^^6    000...10000000
	public  int readNext(InputStream input) throws IOException{
		while(!(input.available()>0)){
			
		}
		return   input.read();
	}
	public CMS50Packet parse(InputStream input){
		int byte1;
		int byte2;
		int byte3;
		int byte4;
		int byte5;
		//byte 1
		boolean beep=false;
		//byte 2
		//byte 3
		//byte 4
		//byte 5
		int spo2;
		
		if(input!=null){
			try {
				
					//skip to sync
					while(true){
					int sync= readNext(input);
					if(isSync(sync)) {
						byte1=sync;
						break;
					}
					}
					byte2=readNext(input);
					byte3=readNext(input);
					byte4=readNext(input);
					byte5=readNext(input);
					

					CMS50Packet result=new CMS50Packet();
					
					result.beep=(byte1&flagbit6)==flagbit6;
					result.spo2Dropping=(byte1&flagbit5)==flagbit5;
					result.searchingTooLong=(byte1&flagbit4)==flagbit4;
					result.signalstrength=byte1&(flagbit3|flagbit2|flagbit1|flagbit0);
					
					result.pulsewaveform=byte2;
					
					result.bargraph=byte3&(flagbit3|flagbit2|flagbit1|flagbit0);
					result.probeerror=(byte3&flagbit4)==flagbit4;
					result.searching=(byte3&flagbit5)==flagbit5;
					
					result.pulseRate=(byte3&flagbit6)<<1+byte4;
					result.spo2=byte5;
					//System.out.println(result.toString());
					return result;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		return null;
	}
	boolean isSync(int aByte){
		return ((aByte&flagbit7)==flagbit7);
	}
}
