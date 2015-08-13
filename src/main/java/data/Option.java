package data;

import java.io.Serializable;

public class Option implements Serializable {									//期权
    private static final long serialVersionUID = 1L;
    private String firstClassName;					//第一大分类;
	private String secondClassName;				//第二大分类;  
	private EorA eora;									//欧式或美式
	private UpOrDown upordown;					//看涨或看跌

    public Option(String firstClassName, String secondClassName, EorA eora, UpOrDown upordown) {
        this.firstClassName = firstClassName;
        this.secondClassName = secondClassName;
        this.eora = eora;
        this.upordown = upordown;
    }

    public String getFirstClassName() {
        return firstClassName;
    }

    public String getSecondClassName() {
        return secondClassName;
    }

    public EorA getEora() {
        return eora;
    }

    public UpOrDown getUpordown() {
        return upordown;
    }
}
