package bl;

import blservice.Service;
import data.Option;
import data.User;
import dataTool.DataTool;

import java.rmi.RemoteException;
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
    public double[] getPurchasePrice(double executeprice, double rate, double deadline) throws RemoteException {
        return new double[2];
    }

    @Override
    public boolean purchaseOption(Option option, int number) throws RemoteException {
        return false;
    }

    @Override
    public boolean sellOption(Option option, int number) throws RemoteException {
        return false;
    }

    @Override
    public double getPresentPriceByOption(Option option) throws RemoteException {
        return dataTool.getHuShen300Price();
    }

}
