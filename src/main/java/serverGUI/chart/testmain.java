package serverGUI.chart;

import javax.swing.JFrame;
import java.awt.Color;

public class testmain {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		LineChart chart = new LineChart(10, 9, 500, 500, 10, new String[] {
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" },
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
						"10" }, new double[] { 0.5, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5 },
				new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, new String[] {
						"111", "2222", "3333", "4444", "5555", "6666", "7777",
						"8888", "9999", "1000" }, new String[] { "111", "2222",
						"3333", "4444", "5555", "6666", "7777", "8888", "9999",
						"1000" }, new Color(221, 61, 66), new Color(6, 74, 150));
		chart.setBounds(100, 100, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chart.go();
		f.repaint();
	}

}
