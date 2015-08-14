package dataTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class DataTool {
    private String urlStr = "http://hq.sinajs.cn/rn=1439444714514&list=s_sh000300";
    private URL url;
    private double value = -1;

    public DataTool() {
        init();
    }

    public double getHuShen300Price() {
        return value;
    }

    private void init() {
        TimerTask task = new TimerTask() {
            public void run() {
                double data = catchData();
                if (data != -1) {
                    value = data;
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
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
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
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

    public static void main(String[] args) {
        DataTool dataTool = new DataTool();
        new Thread() {
            public void run() {
                while (true) {
                    System.out.println("value : " + dataTool.getHuShen300Price());
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
