

import java.util.ArrayList;

public class QueueArrayList<E> {

	private ArrayList<E> listqueue;
	
	public QueueArrayList(){
		listqueue=new ArrayList<E>();
	}
	
	public void enque(E data){
		listqueue.add(data);
	}
	
	public E dequeue(){
		E data=listqueue.get(0);
		listqueue.remove(0);
		return data;
	}
	
	public boolean isFull(){
		return !listqueue.isEmpty();
	}
	
	public boolean isEmpty(){
		return listqueue.isEmpty();
	}
	
	public int size(){
		return listqueue.size();
	}
}
