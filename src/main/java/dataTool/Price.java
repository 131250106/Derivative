package dataTool;

import java.util.Date;

public class Price implements Comparable<Price>{
    long time;
    double price;
    public Price (long time, double price ){
    	this.time = time;
    	this.price = price;
    }
    
    public Price (double price )
    {
    	this.time = new Date().getTime();
    	this.price = price;
    }
    
    public long getTime ()
    {
    	return this.time;
    }
    
    public double getPrice ()
    {
    	return this.price;
    }
    
	public int compareTo(Price o) {
		if (this.time > o.getTime())
		{
			return 1;
		}
		else if (this.time < o.getTime())
		{
			return -1;
		}
		
		return 0;
	}

}
