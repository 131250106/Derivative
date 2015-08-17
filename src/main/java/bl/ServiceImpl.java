package bl;

import blservice.Service;
import data.EorA;
import data.Option;
import data.Order;
import data.User;
import data.upORdown;
import dataTool.DataTool;

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
    private DataTool dataTool = new DataTool();

    @Override
    public User login(String username, String password) throws RemoteException {
        return userData.get(username);
    }

    @Override
    public String register(String username, String password, String name) {
        User user = userData.get(username);
        if (user != null)
            return "Username already exists.";
        userData.put(username, new User(UUID.randomUUID().toString(), name, username, password));
        return "success";
    }

    @Override
    public double[] getCommonPurchasePrice(EorA eora, upORdown upordown,double executeprice, Date deadline,String ClientID) throws RemoteException {
        return new double[2];
    }
    
	@Override
	public double[] getBinaryPurchasePrice1(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return new double[2];
	}

	@Override
	public double[] getBinaryPurchasePrice2(EorA eora, upORdown upordown,double executeprice,
			Date deadline, double salary, String ClientID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new double[2];
	}

	@Override
	public double[] getRetrospectPurchasePrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return new double[2];
	}

	@Override
	public double[] getSubtypePurchasePrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, String ClientID) throws RemoteException {
		// TODO Auto-generated method stub
		return new double[2];
	}

	@Override
	public double[] getObstaclePurchasePrice(EorA eora, upORdown upordown,double executeprice,
			Date deadline, double rate, String ClientID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new double[2];
	}


    @Override
    public boolean purchaseOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) throws RemoteException {
    	OrderManage ordermanage = OrderManage.getInstance();
        return ordermanage.addOrder(option, number, ClientID, deadline, executeprice, dealprice);
    }

    @Override
    public boolean sellOption(Option option, int number, String ClientID,
			Date deadline, double executeprice, double dealprice) throws RemoteException {
    	OrderManage ordermanage = OrderManage.getInstance();
    	number = -1 * number;
        return ordermanage.addOrder(option, number, ClientID, deadline, executeprice, dealprice);
    }

    @Override
    public double getPresentPriceByOption(Option option) throws RemoteException {
        return dataTool.getHuShen300Price();
    }

	@Override
	public Order[] getOrdersByAccount(String account) {
		// TODO Auto-generated method stub
		OrderManage ordermanage = OrderManage.getInstance();
		return ordermanage.getOrdersByAccount(account);
	}

}
