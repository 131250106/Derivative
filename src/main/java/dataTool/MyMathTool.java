package dataTool;

import java.math.BigDecimal;

//利用泰勒展开式提高精度
public class MyMathTool {
	//求幂
	public static BigDecimal bigDecimalPower(BigDecimal a, BigDecimal power,
			int precision) {
		BigDecimal lnA = getLnx(a, 1000);
        BigDecimal fact = new BigDecimal(1);
        BigDecimal result = new BigDecimal(1);
        BigDecimal x = power;
        for (int i = 1; i < precision ; ++i)
        {
        	fact = fact.multiply(new BigDecimal(i));
        	result = result.add(getPowerLaylor(lnA,i,fact, x));
//        	System.out.println ("bigDecimalPower : "+ i);
        }
		return result;
	}

	public static BigDecimal getPowerLaylor(BigDecimal lnA, int n ,BigDecimal fact, BigDecimal x) {
		return lnA.pow(n).multiply(x.pow(n))
				.divide(fact, 100000, BigDecimal.ROUND_DOWN);
	}

	//求对数
	public static BigDecimal getLnx(BigDecimal decimal, int precision) {

		BigDecimal x = new BigDecimal(1).divide(decimal, 10000,
				BigDecimal.ROUND_DOWN).subtract(new BigDecimal(1));
		BigDecimal result = new BigDecimal(0);
		for (int i = 1; i <= precision; ++i) {
			result = result.add(getTaylorLnN(x, i));
//			System.out.println(" i : "+ i);
		}
		return result.negate();
	}
  
	//对数单项展开式
	private static BigDecimal getTaylorLnN(BigDecimal decimal, int n) {
		return new BigDecimal(-1).pow(n - 1).multiply(decimal.pow(n))
				.divide(new BigDecimal(n), 100000, BigDecimal.ROUND_DOWN);
	}

	public static BigDecimal factN(int n) {
		BigDecimal result = new BigDecimal(1);
		for (int i = 2; i <= n; ++i) {
			result = result.multiply(new BigDecimal(i));
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(bigDecimalPower(new BigDecimal("10000"),new BigDecimal("0.5"), 1000));
		System.out.println("max double 1: "+ bigDecimalPower(new BigDecimal(Double.MAX_VALUE),new BigDecimal("0.5"),1000));
		System.out.println("max double 2: "+Math.pow(Double.MAX_VALUE, 0.5));
//		for (int i = 0; i < 1006; ++i)
//		{
//			getLnx(new BigDecimal("10000"),200);
//		};
        
//		System.out.println(getTaylorLnN(new BigDecimal(String.valueOf(0.3)),1).doubleValue());
	}
}
