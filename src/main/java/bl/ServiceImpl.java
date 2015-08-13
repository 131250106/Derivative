package bl;

import blservice.Service;
import data.User;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Nifury on 8/13/2015.
 */
public class ServiceImpl implements Service {

    private Map<String, User> userData = new HashMap<>();

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
    public void placeholder() throws RemoteException {

    }
}
