package Project;

import java.awt.EventQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		//EDT환경에서 안전하게 구동하는 방법
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					College frame = new College();
					frame.home();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
