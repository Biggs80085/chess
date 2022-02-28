package UtilObservable;

import javax.swing.event.EventListenerList;

public class Notifier {

	private EventListenerList listenerList = new EventListenerList();
	
	public void addEventListener(EventListener listener) {
		listenerList.add(EventListener.class, listener);
	}
	
	public void notify(OtherEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for(int i = 0; i < listeners.length; i = i + 2)
			if(listeners[i] == EventListener.class) 
				((EventListener) listeners[i+1]).update(evt);
	
	}
	
}
