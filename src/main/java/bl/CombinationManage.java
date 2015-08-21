package bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import option.MatlabOption;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

import data.EorA;
import data.ServerData;
import data.upORdown;
import dataTool.DBTool;
import dataTool.DataTool;
import dataservice.DBService;

public class CombinationManage {

	private static CombinationManage combinationManage;
	private DBService dbtool;
	private DataTool dataTool;

	private CombinationManage() {
		dbtool = DBTool.getInstance();
		dataTool = new DataTool();
	}

	public static CombinationManage getInstance() {
		if (combinationManage != null) {
			return combinationManage;
		} else {
			combinationManage = new CombinationManage();
			return combinationManage;
		}
	}

	public double getHuShen300Price() {
		// TODO Auto-generated method stub
		return dataTool.getHuShen300Price();
	}

	public double[] getCommonPurchasePrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.vanillacall(5, s, k, t, r, sg);
			} else {
				result = option.vanillaput(5, s, k, t, r, sg);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}

	public double[] getlookbackfixedPrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		MWNumericArray smin = new MWNumericArray(
				dbtool.getMinPrice(new Date()), MWClassID.DOUBLE);
		MWNumericArray smax = new MWNumericArray(
				dbtool.getMaxPrice(new Date()), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;
		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.lookbackfixedcall(5, s, k, smax, t, r, sg);
			} else {
				result = option.lookbackfixedput(5, s, k, smin, t, r, sg);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(smin);
			MWArray.disposeArray(smax);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}

	public double[] getlookbackfloatPrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		MWNumericArray smin = new MWNumericArray(
				dbtool.getMinPrice(new Date()), MWClassID.DOUBLE);
		MWNumericArray smax = new MWNumericArray(
				dbtool.getMaxPrice(new Date()), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;
		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.lookbackfloatcall(5, s, smin, t, r, sg);
			} else {
				result = option.lookbackfloatput(5, s, smax, t, r, sg);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(smin);
			MWArray.disposeArray(smax);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}
	
	public double[] getBinaryAONPrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.BinaryAONCall(5, s, t, r, sg, k);
			} else {
				result = option.BinaryAONPut(5, s, t, r, sg, k);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}

	public double[] getBinaryCONPrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.BinaryCONCall(5, s, t, r, sg, k);
			} else {
				result = option.BinaryCONPut(5, s, t, r, sg, k);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}
	
	public double[] getSubtypeAveragePricePrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.AveragePriceCall(5, s, t, r, sg, k);
			} else {
				result = option.AveragePricePut(5, s, t, r, sg, k);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}

	public double[] getSubtypeAverageStrikePrice(EorA eora, upORdown upordown,
			double executeprice, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice,
				MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(
				ServerData.getDeadTime(deadline), MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(
				ServerData.getRisk_free_Rate(), MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.AverageStrikeCall(5, s, r, sg, j, t_now, t_end);
			} else {
				result = option.AverageStrikePut(5, s, r, sg, j, t_now, t_end);
			}
			PurchasePrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray)(result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray)(result[1])).getDouble();
			Gamma = ((MWNumericArray)(result[2])).getDouble();
			Vega = ((MWNumericArray)(result[3])).getDouble();
			Theta = ((MWNumericArray)(result[4])).getDouble();
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(s);
			MWArray.disposeArray(k);
			MWArray.disposeArray(t);
			MWArray.disposeArray(r);
			MWArray.disposeArray(sg);
			MWArray.disposeArray(result);
			if (option != null) {
				option.dispose();
			}
		}
		return new double[]{ PurchasePrice, SellPrice, Delta, Gamma, Vega, Theta };
	}
	
	
	public static void main(String[] args){
		CombinationManage c = CombinationManage.getInstance();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr="2015-09-24";  
		java.util.Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(c.getCommonPurchasePrice(null, null, 3000, date)[0]);
		System.out.println(c.getCommonPurchasePrice(null, null, 3000, date)[1]);
	}

}
