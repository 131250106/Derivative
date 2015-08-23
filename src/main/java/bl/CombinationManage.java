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
import data.OrderOFholdings;
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
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
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
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
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getSubtypeAverageStrikePrice(EorA eora, upORdown upordown,
			double executeprice,  Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				// result = option.AverageStrikeCall(5, s, r, sg, j, t_now,
				// t_end);
			} else {
				// result = option.AverageStrikePut(5, s, r, sg, j, t_now,
				// t_end);
			}
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getObstaclePurchasedownandinPrice(EorA eora,
			upORdown upordown, double executeprice, double rate, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray b = new MWNumericArray(rate, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.Cdownandin(5, s, k, b, r, sg, t);
			} else {
				result = option.Pdownandin(5, s, k, b, r, sg, t);
			}
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getObstaclePurchaseupandinPrice(EorA eora,
			upORdown upordown, double executeprice, double rate, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray b = new MWNumericArray(rate, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.Cupandin(5, s, k, b, r, sg, t);
			} else {
				result = option.Pupandin(5, s, k, b, r, sg, t);
			}
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getObstaclePurchasedownandoutPrice(EorA eora,
			upORdown upordown, double executeprice, double rate, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray b = new MWNumericArray(rate, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.Cdownandout(5, s, k, b, r, sg, t);
			} else {
				result = option.Pdownandout(5, s, k, b, r, sg, t);
			}
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getObstaclePurchaseupandoutPrice(EorA eora,
			upORdown upordown, double executeprice, double rate, Date deadline) {
		// TODO Auto-generated method stub
		double PurchasePrice = 0;
		double SellPrice = 0;
		double Delta = 0;
		double Gamma = 0;
		double Vega = 0;
		double Theta = 0;

		MWNumericArray s = new MWNumericArray(getHuShen300Price(),
				MWClassID.DOUBLE);
		MWNumericArray k = new MWNumericArray(executeprice, MWClassID.DOUBLE);
		MWNumericArray b = new MWNumericArray(rate, MWClassID.DOUBLE);
		MWNumericArray t = new MWNumericArray(ServerData.getDeadTime(deadline),
				MWClassID.DOUBLE);
		MWNumericArray r = new MWNumericArray(ServerData.getRisk_free_Rate(),
				MWClassID.DOUBLE);
		MWNumericArray sg = new MWNumericArray(
				ServerData.getFluctuation_Rate(), MWClassID.DOUBLE);
		Object[] result = null;
		MatlabOption option = null;

		try {
			option = new MatlabOption();
			if (upordown == upORdown.up) {
				result = option.Cupandout(5, s, k, b, r, sg, t);
			} else {
				result = option.Pupandout(5, s, k, b, r, sg, t);
			}
			PurchasePrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 + ServerData.getFloat_Rate());
			SellPrice = ((MWNumericArray) (result[0])).getDouble()
					* (1.0 - ServerData.getFloat_Rate());
			Delta = ((MWNumericArray) (result[1])).getDouble();
			Gamma = ((MWNumericArray) (result[2])).getDouble();
			Vega = ((MWNumericArray) (result[3])).getDouble();
			Theta = ((MWNumericArray) (result[4])).getDouble();
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
		return new double[] { PurchasePrice, SellPrice, Delta, Gamma, Vega,
				Theta };
	}

	public double[] getRiskRate(OrderOFholdings orders) { // 得到某种期权的风险值
		if (orders.getOption().getFirstClassName().equals("普通期权")) {
			return getCommonPurchasePrice(orders.getOption().getEora(), orders
					.getOption().getUpordown(), orders.getExecuteprice(),
					orders.getDeadline());
		} else if (orders.getOption().getFirstClassName().equals("二元期权")) {
			if (orders.getOption().getSecondClassName().equals("资产或无价值期权")) {
				return getBinaryAONPrice(orders.getOption().getEora(), orders
						.getOption().getUpordown(), orders.getExecuteprice(),
						orders.getDeadline());
			} else {
				return getBinaryCONPrice(orders.getOption().getEora(), orders
						.getOption().getUpordown(), orders.getExecuteprice(),
						orders.getDeadline());
			}
		} else if (orders.getOption().getFirstClassName().equals("回望期权")) {
			if (orders.getOption().getSecondClassName().equals("浮动执行价格期权")) {
				return getlookbackfloatPrice(orders.getOption().getEora(),
						orders.getOption().getUpordown(),
						orders.getExecuteprice(), orders.getDeadline());
			} else {
				return getlookbackfixedPrice(orders.getOption().getEora(),
						orders.getOption().getUpordown(),
						orders.getExecuteprice(), orders.getDeadline());
			}
		} else if (orders.getOption().getFirstClassName().equals("亚式期权")) {
			if (orders.getOption().getSecondClassName().equals("平均价格期权")) {
				return getSubtypeAveragePricePrice(
						orders.getOption().getEora(), orders.getOption()
								.getUpordown(), orders.getExecuteprice(),
						orders.getDeadline());
			} else {
				return getSubtypeAverageStrikePrice(orders.getOption()
						.getEora(), orders.getOption().getUpordown(),
						orders.getExecuteprice(), orders.getDeadline());
			}
		} else if (orders.getOption().getFirstClassName().equals("障碍期权")) {
			if (orders.getOption().getSecondClassName().equals("向上敲入期权")) {
				return getObstaclePurchaseupandinPrice(
						orders.getOption().getEora(), orders.getOption()
								.getUpordown(), orders.getExecuteprice(),orders.getOption().getObstacleRate(),
						orders.getDeadline());
			} else if (orders.getOption().getSecondClassName().equals("向下敲入期权")){
				return getObstaclePurchasedownandinPrice(orders.getOption()
						.getEora(), orders.getOption().getUpordown(),
						orders.getExecuteprice(),orders.getOption().getObstacleRate(), orders.getDeadline());
			}else if (orders.getOption().getSecondClassName().equals("向上敲出期权")){
				return getObstaclePurchaseupandoutPrice(orders.getOption()
						.getEora(), orders.getOption().getUpordown(),
						orders.getExecuteprice(),orders.getOption().getObstacleRate(), orders.getDeadline());
			}else {
				return getObstaclePurchasedownandoutPrice(orders.getOption()
						.getEora(), orders.getOption().getUpordown(),
						orders.getExecuteprice(),orders.getOption().getObstacleRate(), orders.getDeadline());
			}
		}
		return null;
	}

	public void hedging() { // 核心对冲算法！！！
		double w1 = 0, w2 = 0, w3 = 0, d0 = 0, d1 = 0, d2 = 0, g0 = 0, g1 = 0, g2 = 0, v0 = 0, v1 = 0, v2 = 0;
		OrderOFholdings[] orders = dbtool.getHoldingOrders();
		for (int i = 0; i < orders.length; i++) {
			double[] temp = getRiskRate(orders[i]);
			d0 += orders[i].getNumber() * temp[2];
			g0 += orders[i].getNumber() * temp[3];
			v0 += orders[i].getNumber() * temp[4];
		}
		d0 = -1 * d0;
		g0 = -1 * g0;
		v0 = -1 * v0;
		if (Math.abs((d0 - ServerData.getDelta()) / ServerData.getDelta()) > ServerData
				.getDelta_change()) { // 一旦Delta波动过大超出设定值
			// 在期货市场上进行仓位的增减。（自动买入-d0（取整）手期货
			ServerData.setDelta(d0);
			// hedging(); //不知道需不需要这一步。。。。。
		}
		ServerData.setDelta(d0);
		if (g0 > ServerData.getThreshold() || v0 > ServerData.getThreshold()) { // gamma或vega达到阈值时启动（暂定）

			// 暂定(暂时不知道这些值怎么来的)
			EorA eora = EorA.E;
			upORdown upordown1 = upORdown.up;
			upORdown upordown2 = upORdown.down;
			double executeprice1 = 100;
			double executeprice2 = 200;
			Date deadline1 = new Date();
			Date deadline2 = new Date();
			// 暂定(暂时不知道这些值怎么来的)

			double[] temp1 = getCommonPurchasePrice(eora, upordown1,
					executeprice1, deadline1);
			double[] temp2 = getCommonPurchasePrice(eora, upordown2,
					executeprice2, deadline2);
			d1 = temp1[2];
			g1 = temp1[3];
			v1 = temp1[4];
			d2 = temp2[2];
			g2 = temp2[3];
			v2 = temp2[4];

			w1 = (g2 * v0 - g0 * v2) / (g1 * v2 - g2 * v1);
			w2 = (g0 * v1 - g1 * v0) / (g1 * v2 - g2 * v1);
			w3 = -d0 - w1 * d1 - w2 * d2;
			w1 = Math.round(w1);
			w2 = Math.round(w2);
			w3 = Math.round(w3);
			// （自动买入w1（取整）手1期权，w2（取整）手2期权）
			// （自动买入w3（取整）手期货）
		}

	}

	public static void main(String[] args) {
		CombinationManage c = CombinationManage.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		String dstr = "2015-09-24";
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
