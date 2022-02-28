package UtilObservable;

import java.io.Serializable;

public class Chrono implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final public static int TIMER = 180;
	public int timeChrono;

	public Chrono() {
		this.timeChrono = TIMER;
	}

	public int getTimeChrono() {
		return timeChrono;
	}

	public void setTimeChrono(int timer) {
		this.timeChrono = timer;
	}

}
