package dataTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import dataservice.DataToolService;

public class DataTool implements DataToolService{
	private String urlStr = "http://hq.sinajs.cn/rn=1439444714514&list=s_sh000300";
	private URL url;
	private double value = -1;
	private PriceQueue queue;
	private int count = 1;
	private boolean onTime = true;

	public DataTool() {
		init();
		queue = new PriceQueue();
	}

	public double getHuShen300Price() {
		return value;
	}

	public double getGeometricalMean(Date date) throws NoDataException {
		return queue.getGeometricalMean(date.getTime());
	}

	private void init() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				double data = catchData();
				if (data != -1) {
					value = data;
				}
				--count;
				if (count <= 0 && value != -1 && onTime == true) {
					queue.addPrice(new Price(value));
				}
			}
		};
		timer.schedule(task, 0, 3000);
	}

	private double catchData() {
		String dataStr = this.getDataStr();
		double result = -1;
		if (dataStr != null) {
			String priceStr = getValue(dataStr);
			result = Double.parseDouble(priceStr);
		}
		return result;
	}

	private String getDataStr() {
		String result = null;
		try {
			url = new URL(urlStr);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()))) {
				String temp;
				while ((temp = br.readLine()) != null) {
					result += temp;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getValue(String script) {
		return script.split(",")[1];

	}

	
	public void setOnTime(boolean onTime)
	{
		this.onTime = onTime;
	}
	
	public boolean getOnTime()
	{
		return this.onTime;
	}
	
	public static void main(String[] args) {
		DataTool dataTool = new DataTool();
		new Thread() {
			public void run() {
				while (true) {
					System.out.println("value : "
							+ dataTool.getHuShen300Price());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
