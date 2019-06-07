package model;

import java.util.Observable;
import java.util.Observer;

public class CarObserver implements Observer{
	private Car b = null;
	
	@Override
	public void update(Observable o, Object arg) {
		b.setAvailability((String)arg);
	}
}
