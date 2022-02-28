package UtilObservable;

import java.util.EventObject;

public class OtherEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object data, data2;
	
	public OtherEvent(Object source, Object data, Object data2) {
		super(source);
		this.data = data;
		this.data2 = data2;
		// TODO Auto-generated constructor stub
	}
	public OtherEvent(Object source, Object data) {
		super(source);
		this.data = data;
		
		// TODO Auto-generated constructor stub
	}
	
	public Object getData() {
		return this.data;
	}
	
	public Object getData2() {
		if(this.data2 != null)
			return this.data2;
		else
			return null;
	}
	
	
	
}
