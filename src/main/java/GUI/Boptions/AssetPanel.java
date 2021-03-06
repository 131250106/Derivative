package GUI.Boptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import GUI.InsurePanel;
import GUI.Loader;
import GUI.myswing.DateChooser;
import GUI.myswing.MyColor;
import blservice.Service;
import data.EorA;
import data.Option;
import data.Order;
import data.upORdown;

public class AssetPanel extends BoptionsPanel{
	Service service;
	Option option;
	EorA eora;
	upORdown upordown;
	Date deadline;
	double executePrice;
 	double dealprice;
 	boolean isPurchase;
 	int number;
 	TimerThread timerThread=null;
	boolean result;
	Order tempOrder;
	JFrame ensureFrame;

 	
	private JLabel tag;
	private ButtonGroup LookUpAndDown;
	private JRadioButton LookUp;
	private JRadioButton LookDown;
	private ButtonGroup EAndA;
	private JRadioButton Europe;
	private JRadioButton America;
	private ButtonGroup BorA;
	private JRadioButton bidButton;
	private JRadioButton askButton;
	
    private JLabel timer;
	private DateChooser datechooser;
    
	static int PANEL_WIDTH = 960;
	static int PANEL_HEIGHT = 600;
	static int LABEL_WIDTH = 70;
	static int LABEL_HEIGHT = 25;
	static int TEXTFIELD_WIDTH = 70;
	static int TEXTFIELD_HEIGHT = 22;
	
	double[] PurchasePrice;
	
	JLabel executePriceLabel,noRiskRateLabel,deadlineLabel,bidPriceLabel,askPriceLabel,dealNumLabel,bidPriceField,askPriceField;
	JTextField executePriceField,noRiskRateField,deadlineField,dealNumField;
	JButton submitButton,dealButton;
	
	public AssetPanel(String name){
		super("asset");
		service = Loader.service;
		super.assetoption.setBackground(MyColor.deepblue2);
		super.assetoption.setForeground(MyColor.white);
		super.tag.setVisible(false);;
		    Font font = new Font("微软雅黑",Font.PLAIN,15);
			
		    tag = new JLabel("资产或无价值期权");
			tag.setSize(175,70);
			tag.setLocation(830,127);
			tag.setFont(font);
			tag.setBackground(MyColor.white);
			tag.setForeground(MyColor.deepblue);
			tag.setVisible(true);
			//this.add(tag);
		    
			font = new Font("微软雅黑",Font.PLAIN,15);
			
			LookUpAndDown = new ButtonGroup();
			LookUp = new JRadioButton("看涨");
			LookUp.setFont(font);
			LookDown = new JRadioButton("看跌");
			LookDown.setFont(font);
			LookUpAndDown.add(LookUp);
			LookUpAndDown.add(LookDown);
			
			LookUp.setFocusPainted(false);
			LookUp.setBorderPainted(false);
			LookDown.setFocusPainted(false);
			LookDown.setBorderPainted(false);
			LookUp.setBackground(MyColor.white);
			LookDown.setBackground(MyColor.white);
			LookUp.setForeground(MyColor.black);
			LookDown.setForeground(MyColor.black);
			
			LookUp.setBounds(295+100, 160+50, 64, 30);
			LookDown.setBounds(208+100,160+50,64,30);		
			LookDown.setVisible(true);
			LookUp.setVisible(true);
			this.add(LookUp);
			this.add(LookDown);
			
			font = new Font("微软雅黑",Font.PLAIN,15);
			Font font3 = new Font("微软雅黑",Font.BOLD,20);
			
			EAndA = new ButtonGroup();
			Europe = new JRadioButton("欧式");
			Europe.setFont(font);
			America = new JRadioButton("美式");
			America.setFont(font);
			EAndA.add(Europe);
			EAndA.add(America);
			
			Europe.setFocusPainted(false);
			Europe.setBorderPainted(false);
			America.setFocusPainted(false);
			America.setBorderPainted(false);
			Europe.setBackground(MyColor.white);
			America.setBackground(MyColor.white);
			Europe.setForeground(MyColor.black);
			America.setForeground(MyColor.black);
			
			Europe.setBounds(295+100, 210+50, 64, 30);
			America.setBounds(208+100,210+50,64,30);		
			America.setVisible(true);
			Europe.setVisible(true);
			this.add(Europe);
			this.add(America);
			
			Europe.setSelected(true);
			LookUp.setSelected(true);
			
			
			executePriceLabel = new JLabel("执行价格:");
			executePriceLabel.setFont(font);
			executePriceLabel.setBounds(213+100, 260+50, LABEL_WIDTH, LABEL_HEIGHT);
			executePriceLabel.setVisible(true);
			this.add(executePriceLabel);
			
			executePriceField = new JTextField();
			executePriceField.setBounds(290+100, 260+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			executePriceField.setVisible(true);
			executePriceField.setFont(font);
			this.add(executePriceField);
			
//			noRiskRateLabel = new JLabel("无风险利率:");
//			noRiskRateLabel.setFont(font);
//			noRiskRateLabel.setBounds(200, 280, LABEL_WIDTH, LABEL_HEIGHT);
//			noRiskRateLabel.setVisible(true);
//			this.add(noRiskRateLabel);
//			
//			noRiskRateField = new JTextField();
//			noRiskRateField.setFont(font);
//			noRiskRateField.setBounds(280, 280, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
//			noRiskRateField.setVisible(true);
//			this.add(noRiskRateField);
			
			//需要处理计算一下
			deadlineLabel = new JLabel("截止日期:");
			deadlineLabel.setFont(font);
			deadlineLabel.setBounds(213+100,310+50, LABEL_WIDTH, LABEL_HEIGHT);
			deadlineLabel.setVisible(true);
			this.add(deadlineLabel);
			
			deadlineField = new JTextField();
			deadlineField.setFont(font);
			deadlineField.setBounds(100+290+100,310+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			deadlineField.setVisible(false);
			this.add(deadlineField);
			
			datechooser = new DateChooser();
			datechooser.setEnabled(true);
			datechooser.setVisible(true);
			datechooser.setFont(font);
//			datechooser.setBorder(null);//把边框隐藏
			datechooser.setBounds(290+100,310+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT+3);
			this.add(datechooser);
			
//			hourLabel = new JLabel("时");
//			hourLabel.setFont(font);
//			hourLabel.setBounds(r);
//			hourLabel.setVisible(true);
//			this.add(hourLabel);
			
			
			JLabel warningLabel = new JLabel("(请输入完整信息)");
			Font font2 = new Font("微软雅黑",Font.PLAIN,12);
			warningLabel.setFont(font);
			warningLabel.setBounds(240+100+80+5,380+50,140,25);
			warningLabel.setForeground(Color.RED);
			warningLabel.setVisible(false);
			this.add(warningLabel);
			
			bidPriceLabel = new JLabel(" 买价:");
			bidPriceLabel.setFont(font3);
			bidPriceLabel.setBounds(500+101,160+50,LABEL_WIDTH,LABEL_HEIGHT);
			bidPriceLabel.setVisible(false);
//			bidPriceLabel.setForeground(Color.RED);;
			this.add(bidPriceLabel);
			
			
			bidPriceField = new JLabel();
			bidPriceField.setFont(font3);
			bidPriceField.setBounds(580+101, 160+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			bidPriceField.setVisible(false);
			bidPriceField.setForeground(Color.RED);;
			this.add(bidPriceField);
			
			askPriceLabel = new JLabel(" 卖价:");
			askPriceLabel.setFont(font3);
//			askPriceLabel.setForeground(Color.RED);
			askPriceLabel.setBounds(500+101,210+50,LABEL_WIDTH,LABEL_HEIGHT);
			askPriceLabel.setVisible(false);
			this.add(askPriceLabel);
			
			askPriceField = new JLabel();
			askPriceField.setFont(font3);
			askPriceField.setForeground(Color.RED);
			askPriceField.setBounds(580+101, 210+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			askPriceField.setVisible(false);
			this.add(askPriceField);
			
			BorA = new ButtonGroup();
			bidButton = new JRadioButton("买入");
			bidButton.setFont(font);
			askButton = new JRadioButton("卖出");
			askButton.setFont(font);
			BorA.add(bidButton);
			BorA.add(askButton);
			
			bidButton.setFocusPainted(false);
			bidButton.setBorderPainted(false);
			askButton.setFocusPainted(false);
			askButton.setBorderPainted(false);
			bidButton.setBackground(MyColor.white);
			askButton.setBackground(MyColor.white);
			bidButton.setForeground(MyColor.black);
			askButton.setForeground(MyColor.black);
			
			bidButton.setBounds(500+101,260+50, 64, 30);
			askButton.setBounds(580+101,260+50,64,30);		
			askButton.setVisible(false);
			bidButton.setVisible(false);
			this.add(bidButton);
			this.add(askButton);
			
			dealNumLabel = new JLabel("交易数量:");
			dealNumLabel.setFont(font);
			dealNumLabel.setBounds(500+101,310+50,LABEL_WIDTH,LABEL_HEIGHT);
			dealNumLabel.setVisible(false);
			this.add(dealNumLabel);
			
			dealNumField = new JTextField();
			dealNumField.setFont(font);
			dealNumField.setBounds(580+101,310+50, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
			dealNumField.setVisible(false);
			this.add(dealNumField);
			
			timer = new JLabel("初始化");
			timer.setFont(font);
			timer.setBounds(520+101+85,380+50, LABEL_WIDTH+100, LABEL_HEIGHT);
			timer.setVisible(false);
			this.add(timer);
			
			submitButton = new JButton("查询");
			submitButton.setFont(font);
			submitButton.setSize(80, 30);
			submitButton.setLocation(240+100,380+50);
			submitButton.setBackground(MyColor.deepblue);
			submitButton.setForeground(MyColor.lightblue);
			submitButton.setFocusPainted(false);
			submitButton.setBorderPainted(false);
			submitButton.setVisible(true);
			submitButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//这里显示输出的两个框
					if(executePriceField.getText().equals("")||
							EAndA.getSelection()==null||LookUpAndDown.getSelection()==null){
						warningLabel.setVisible(true);
					}else{
						//画出显示框
						warningLabel.setVisible(false);
						executePrice = Double.parseDouble(executePriceField.getText());
//						double noRiskRate = Double.parseDouble(noRiskRateField.getText());
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						deadline = null;
						try {
							deadline = format.parse(datechooser.getTime());
							System.out.println(deadline);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						eora = Europe.isSelected()?EorA.E:EorA.A;
						upordown = LookDown.isSelected()?upORdown.down:upORdown.up;
						
						try {
							PurchasePrice = service.getBinaryPurchasePrice1(eora, upordown, executePrice, deadline, "131250131");
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						System.out.println(executePrice);
						//这里调用getPurchasePrice
						bidPriceField.setText(Double.toString(PurchasePrice[0]));
						askPriceField.setText(Double.toString(PurchasePrice[1]));
//						bidPriceField.setText("12.5");
//						askPriceField.setText("12.0");
						bidPriceField.setVisible(true);
						bidPriceLabel.setVisible(true);
						askPriceLabel.setVisible(true);
						askPriceField.setVisible(true);
						bidButton.setVisible(true);
						askButton.setVisible(true);
						dealNumLabel.setVisible(true);
						dealNumField.setVisible(true);
						dealButton.setVisible(true);
						timer.setVisible(true);
						if(timerThread==null){
							timerThread = new TimerThread();
							timerThread.start();
						}else{
							timerThread.stop();
							timerThread = new TimerThread();
							timerThread.start();
						}
						submitButton.setEnabled(false);
					}			
					System.out.println("submitButton has been clicked!");
				}
			});
			this.add(submitButton);
			
			dealButton = new JButton("交易");
			dealButton.setFont(font);
			dealButton.setSize(80, 30);
			dealButton.setLocation(520+101,380+50);
			dealButton.setBackground(MyColor.deepblue);
			dealButton.setForeground(MyColor.lightblue);
			dealButton.setFocusPainted(false);
			dealButton.setBorderPainted(false);
			dealButton.setVisible(false);
			dealButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//这里显示输出的两个框
					if(dealNumField.getText().equals("")||BorA.getSelection()==null){
						System.out.println("没填完");
					}else{
						if(timer.getText().equals("(请在0秒内完成操作)")){
							submitButton.setEnabled(true);
							timer.setText("操作超时，请重新查询");
						}else{
							number = Integer.parseInt(dealNumField.getText());
							//调用交易接口
							option =  new Option("二元期权", "资产或无价值期权", eora, upordown);
							isPurchase = bidButton.isSelected()?true:false;
							if(isPurchase){
								number = number*1;
								dealprice = PurchasePrice[0];
							}else{
								number = number*(-1);
								dealprice = PurchasePrice[1];
							}
							try {
								tempOrder = service.purchaseOption(option, number, "131250131", deadline, executePrice, dealprice);
							} catch (RemoteException e2) {
								timer.setText("网络问题");
								e2.printStackTrace();
							}
							ensureFrame = new JFrame("确认界面");
							ensureFrame.setAlwaysOnTop(true);
							ensureFrame.setUndecorated(true);
							ensureFrame.setLocation(550,80);
							ensureFrame.setSize(400,620);
							InsurePanel ensure = new InsurePanel(tempOrder);
							addensure(ensure);

							ensure.ensure.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e){
									try {
										result = service.InsurePurchase(tempOrder);
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
									removeensure(ensure);
								}
							});
							
							ensure.cancel.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e){
									removeensure(ensure);
								}
							});
//							try {
//								result = service.purchaseOption(option, number, "131250131", deadline, executePrice, dealprice);
//							} catch (RemoteException e1) {
//								e1.printStackTrace();
//							}
							System.out.println("交易执行结果:"+result);
							if(result){
								timer.setText("交易成功!");
							}else{
								timer.setText("交易失败!请重试");
							}
						}
		
					}			
					System.out.println("submitButton has been clicked!");
				}
			});
			
			this.add(dealButton);
			

		}
	  void addensure(InsurePanel panel){
	    	this.ensureFrame.add(panel);
	    	this.ensureFrame.setVisible(true);
	    	this.repaint();
	    	this.updateUI();
	    }
	    
	    void removeensure(InsurePanel panel){
	    	this.ensureFrame.setVisible(false);
	    	this.repaint();
	    	this.updateUI();
	    }
	    
	    
	    class TimerThread extends Thread{
	    	public void run(){
	      	  long time = 1 * 30 ;// 自定义倒计时时间
	      	  while (time >= 0){
	              timer.setText("(请在"+time+"秒内完成操作)");
	              timer.setForeground(Color.RED);
	                try {
	                	this.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                time--;
	            }
	    	}  	
	    }
}