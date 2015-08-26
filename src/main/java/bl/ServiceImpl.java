package bl;

import blservice.Service;
import data.EorA;
import data.Option;
import data.Order;
import data.OrderOFholdings;
import data.User;
import data.upORdown;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Nifury on 8/13/2015.
 */
public class ServiceImpl implements Service {

    private Map<String, User> userData = new HashMap<>();
    private OrderManage orderManage = OrderManage.getInstance();
    private CombinationManage combinationManage = CombinationManage.getInstance();
    

    @Override
    public User login(String username, String password) throws RemoteException {
        return userData.get(username);
    }

    @Override
    public String register(String username, String password, String name) throws RemoteException  {
        User user = userData.get(username);
        if (user != null)
            return "Username already exists.";
        userData.put(username, new User(UUID.randomUUID().toString(), name, username, password));
        return "success";
    }

    //普通期权
    @Override
    public double[] getCommonPurchasePrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID) throws RemoteException {
        return combinationManage.getCommonPurchasePrice(eora,upordown,executeprice,deadline);
    }
    
    //二元期权(资产或无价值期权)
	@Override
	public double[] getBinaryPurchasePrice1(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		  return combinationManage.getBinaryAONPrice(eora,upordown,executeprice,deadline);
	}

	//二元期权(现金或无价值期权)
	@Override
	public double[] getBinaryPurchasePrice2(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getBinaryCONPrice(eora,upordown,executeprice,deadline);
	}

	//回望固定期权
	@Override
	public double[] getlookbackfixedPrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getlookbackfixedPrice(eora,upordown,executeprice,deadline);
	}
	
	//回望浮动期权
	@Override
	public double[] getlookbackfloatPrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getlookbackfloatPrice(eora,upordown,executeprice,deadline);
	}

	//亚式期权(平均价格期权)
	@Override
	public double[] getSubtypeAveragePricePrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getSubtypeAveragePricePrice(eora,upordown,executeprice,deadline);
	}
	
	//亚式期权(平均执行价格期权)
	@Override
	public double[] getSubtypeAverageStrikePrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getSubtypeAverageStrikePrice(eora,upordown,executeprice,deadline);
	}

	//障碍期权（向下敲入）
	@Override
	public double[] getObstaclePurchasedownandinPrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, double rate, String ClientID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return combinationManage.getObstaclePurchasedownandinPrice(eora,upordown,executeprice,rate,deadline);
	}
	
	//障碍期权（向上敲入）
		@Override
		public double[] getObstaclePurchaseupandinPrice(EorA eora, upORdown upordown,double executeprice,
				Date deadline, double rate, String ClientID)
				throws RemoteException {
			// TODO Auto-generated method stub
			return combinationManage.getObstaclePurchaseupandinPrice(eora,upordown,executeprice,rate,deadline);
		}
		
		//障碍期权（向下敲出）
		@Override
		public double[] getObstaclePurchasedownandoutPrice(EorA eora, upORdown upordown,double executeprice,
				Date deadline, double rate, String ClientID)
				throws RemoteException {
			// TODO Auto-generated method stub
			return combinationManage.getObstaclePurchasedownandoutPrice(eora,upordown,executeprice,rate,deadline);
		}
		
		//障碍期权（向上敲出）
		@Override
		public double[] getObstaclePurchaseupandoutPrice(EorA eora, upORdown upordown,double executeprice,
				Date deadline, double rate, String ClientID)
				throws RemoteException {
			// TODO Auto-generated method stub
			return combinationManage.getObstaclePurchaseupandoutPrice(eora,upordown,executeprice,rate,deadline);
		}


    @Override
    public Order purchaseOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) throws RemoteException {
        Order result =  orderManage.requestOrder(option, number, ClientID, deadline, executeprice, dealprice);
//        combinationManage.hedging();
        return result;
    }

    
    @Override
    public Order sellOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) throws RemoteException {
    	number = -1 * number;
    	Order result =  orderManage.requestOrder(option, number, ClientID, deadline, executeprice, dealprice);
//        combinationManage.hedging();
        return result;
    }
    
	@Override
	public boolean InsurePurchase(Order order) throws RemoteException {
		// TODO Auto-generated method stub
		return orderManage.addOrder(order);
	}

	@Override
	public boolean InsureSell(Order order) throws RemoteException {
		// TODO Auto-generated method stub
		return orderManage.addOrder(order);
	}
    
    @Override
    public double getPresentPriceByOption(Option option) throws RemoteException {
        return combinationManage.getHuShen300Price();
    }

	@Override
	public Order[] getOrdersByAccount(String account) throws RemoteException  {
		// TODO Auto-generated method stub
//		OrderManage ordermanage = OrderManage.getInstance();
		return orderManage.getOrdersByAccount(account);
	}

	@Override
	public OrderOFholdings[] getOrderOFholdingsByAccount(String account)
			throws RemoteException {
		// TODO Auto-generated method stub
		return orderManage.getOrderOFholdingsByAccount(account);
	}


}
