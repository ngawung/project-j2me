package mlbb.scene.demo;

import java.util.Random;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import melody.core.Scene;
import melody.enums.KeyCode;
import melody.utils.RandomUtils;
import mlbb.interfaces.Sortable;

public class SortingDemo extends Scene {
	
	private Vector list;
	private int[] index;
	
	private Random random;
	
	private double _playerAngle = 0;
	private boolean _playerMove = false;
	private String text = "";

	public SortingDemo() {
		list = new Vector();
		random = new Random(System.currentTimeMillis());
		
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		list.addElement(new SortableQuad(random));
		
		index = new int[list.size()];
		for (int i=0; i<index.length; i++) index[i] = i;
	}

	public void destroy() {

	}

	public void initialize() {

	}

	public void update(long dt) {
		requestRender();
		sort(index, 0, index.length - 1);
		toStringArray(index);
		
		_playerAngle = 0;
		_playerMove = false;
		
		// input
		if (get_input().isHeld(KeyCode.LEFT)) {
			_playerAngle = 180;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.RIGHT)) {
			_playerAngle = 0;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.UP)) {
			_playerAngle = 270;
			_playerMove = true;
		}
		if (get_input().isHeld(KeyCode.DOWN)) {
			_playerAngle = 90;
			_playerMove = true;
		}
		
		if (_playerMove) {
			((SortableQuad)list.elementAt(0)).x += (int)Math.cos(_playerAngle * (Math.PI/180)) * 5;
			((SortableQuad)list.elementAt(0)).y += (int)Math.sin(_playerAngle * (Math.PI/180)) * 5;
		}
	}
	
	public void render(Graphics g) {
		for (int i=0; i<index.length; i++) {
			SortableQuad temp = (SortableQuad)list.elementAt(index[i]);
			g.setColor(temp.r, temp.g, temp.b);
			g.fillRect(temp.x - 15, temp.y - 50, 30, 50);
		}
		
		g.setColor(0);
		g.drawString(text, 5, get_height() - 15, Graphics.LEFT | Graphics.BOTTOM);
	}
	
	public void toStringArray(int[] array) {
		String out = "";
		String out2 = "";
		
		for (int i=0; i<array.length; i++) {
			out2 += ((SortableQuad)list.elementAt(i)).y + ",";
			out += array[i] + ",";
		}
		
		text = out;
//		System.out.println(out);
//		System.out.println(out2);
	}
	
	
	//////
	
	public class SortableQuad {
		public int x = 0;
		public int y = 0;
		public int r = 0;
		public int g = 0;
		public int b = 0;
		
		public SortableQuad(Random r) {
			this.r = RandomUtils.rand(0, 255, r);
			this.g = RandomUtils.rand(0, 255, r);
			this.b = RandomUtils.rand(0, 255, r);
			
			this.x = RandomUtils.rand(0, get_width(), r);
			this.y = RandomUtils.rand(0, get_height(), r); 
		}
	}
	
	/////////
	
	private void sort(int arr[], int start, int end) {
		if (start < end) {
			int pIndex = partition(arr, start, end);
			sort(arr, start, pIndex - 1);
			sort(arr, pIndex + 1, end);
		}
	}
	
	private int partition(int arr[], int start, int end) {
		int pivot = ((SortableQuad)list.elementAt(arr[end])).y;
		int pIndex = start;
		for (int i = start; i < end; i++) {
			if (((SortableQuad)list.elementAt(arr[i])).y <= pivot) {
				swap(arr, i, pIndex);
				pIndex++;
			}
		}
		swap(arr, pIndex, end);
		return pIndex;
	}

	private void swap(int arr[], int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

}
