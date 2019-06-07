package presentation;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

import javax.swing.JTable;


public class MakeTable {
	public static JTable createTable(ArrayList<Object> objects){
		Object o= objects.get(0);
		Object[] headers= new Object[o.getClass().getDeclaredFields().length];
		int i =0;
		for (java.lang.reflect.Field field : o.getClass().getDeclaredFields()) {
			((AccessibleObject) field).setAccessible(true); // set modifier to public
			try {
				headers[i]=field.getName();
				i++;

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}

		Object[][] data= new Object[objects.size()][headers.length];

		for(int j=0;j<objects.size();j++){
			int k=0;
			for (java.lang.reflect.Field field : objects.get(j).getClass().getDeclaredFields()) {
				field.setAccessible(true); // set modifier to public
				try {
					data[j][k] = field.get(objects.get(j));
					k++;

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		JTable table= new JTable(data,headers);

		return table;
	}
	
}
