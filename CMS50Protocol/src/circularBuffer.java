import java.awt.List;
import java.util.ArrayList;
import java.util.Queue;

//windowed data
public class circularBuffer<T> {
	//thread safe
	T[] buffer;
	int start;
	int capacity;
	@SuppressWarnings("unchecked")
	public circularBuffer(int capacity) {
		buffer=(T[])new Object[capacity]; 
		this.capacity=capacity;
		start=0;
	}
	public void add(T aT){
		synchronized(this){
		buffer[start]=aT;
		start=(start+1)%capacity;
		}
	}
	public T get(int x){
		T result;
		synchronized(this){
		result=buffer[(start+x)%capacity];
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<T> getBuffer(){
		ArrayList<T> result=new ArrayList<T>(capacity);
		
		synchronized (this) {
			for(int i=start;i<capacity;i++){
				//result[index]=buffer[i];
				result.add( buffer[i]);
				
			}
			for(int i=0;i<start;i++){
				//result[index]=buffer[i];

				result.add( buffer[i]);
			}
		}
		return result;
	}
	public int size(){
		return capacity;
	}
	public static void main(String[] args){
		circularBuffer<Integer> tmp=new circularBuffer<Integer>(4);
		tmp.add(3);
		tmp.add(5);
		tmp.add(4);
		tmp.add(4);
		tmp.add(4);

		ArrayList<Integer> tt=tmp.getBuffer();
		for(int i=0;i<tmp.size();i++){
		System.out.println(tt.get(i));
		}
	}
}
