package entry;

import bl.ServiceImpl;
import blservice.Service;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.mathworks.toolbox.javabuilder.*;
import option.*;

/**
 * Created by Nifury on 8/13/2015.
 */
public class Entry {

    private static void matlabTest() {
        try {
            MatlabOption option = new MatlabOption();
            MWNumericArray s = new MWNumericArray(100, MWClassID.DOUBLE);
            MWNumericArray k = new MWNumericArray(105, MWClassID.DOUBLE);
            MWNumericArray t = new MWNumericArray(0.5, MWClassID.DOUBLE);
            MWNumericArray r = new MWNumericArray(0.2, MWClassID.DOUBLE);
            MWNumericArray sg = new MWNumericArray(0.3, MWClassID.DOUBLE);
            MWNumericArray smin = new MWNumericArray(100, MWClassID.DOUBLE);
            MWNumericArray smax = new MWNumericArray(300, MWClassID.DOUBLE);
            Object[] result;

            result = option.vanillacall(5, s, k, t, r, sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            result = option.lookbackfixedcall(5, s,k,smax,t,r,sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            result = option.lookbackfixedput(5, s, k, smin, t, r, sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            result = option.lookbackfloatcall(5, s, smin, t, r, sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            result = option.lookbackfloatput(5, s, smax, t, r, sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            result = option.vanillaput(5, s,k,t,r,sg);
            for (Object o : result) {
                System.out.println(o);
            }
            MWArray.disposeArray(result);
            System.out.println("------------------------");

            MWArray.disposeArray(s);
            MWArray.disposeArray(k);
            MWArray.disposeArray(t);
            MWArray.disposeArray(r);
            MWArray.disposeArray(sg);
            option.dispose();
        } catch (MWException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Service service = (Service) UnicastRemoteObject.exportObject(new ServiceImpl(), 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("service", service);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
