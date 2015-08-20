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
//	private String urlStr = "http://hq.sinajs.cn/rn=1439444714514&list=s_sh000300";  //获得沪深300
	private String urlStr = "http://hq.sinajs.cn/list=sh000300";        //获得沪深300
//	private String urlBjTime = "http://hq.sinajs.cn/?format=json&list=sys_time";   //获得背景时间
	private URL url;
	private volatile  double value = -1;
	private PriceQueue queue;
	private boolean onTime = true;
    private String time;
    private int count = 0;
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
				if (Math.abs((data + 1)) > 0.0000001) {
					value = data;
				}
				if (value != -1 && onTime == true) {
					System.out.println("add");
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
		String[] data = script.split(",");
		//通过两次时间数据的对比得出 股市是否收盘
		String hushenTime = data[data.length - 2];
		if (time == null)
		{
			time = hushenTime;
		}
		else 
		{
			if (time.equals(hushenTime))
			 {
				++count;
				if (count > 5)
				 this.setOnTime(false);
			 }
			else 
			{
				if (count > 5)
				{
				this.setOnTime(true);
				count = 0;
				}
				time = hushenTime;
			}
		}
		return data[3];

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
		final DataTool dataTool = new DataTool();
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
