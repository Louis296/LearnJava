import static java.lang.System.out;

class Car {
	private int size;
	String name;
	public static final int MODEL=520;
	public Car() {
		out.println("Making a Car...");
	}
	public final void setsize(Integer i) {
		if(i>=10) {
			size=i;
		}
	}
	public int getsize() {
		return size;
	}
		
}

class ElectricCar extends Car{
	void belling() {
		out.println("  滴滴滴");
	}
}

public class Test{
	public static void main(String[] args) {
		Car[] cars;
		cars=new Car[7];
		cars[0]=new Car();
		cars[0].setsize(10);
		int size0=cars[0].getsize();
		cars[0].name="wangzi";
		out.println("我的名字是"+cars[0].name);
		out.println("我的大小是"+size0);
		out.println("我的型号是"+Car.MODEL);
		cars[1]=new ElectricCar();
		cars[1].name="lajitong";
		out.print(cars[1].name);
		ElectricCar a=(ElectricCar) cars[1];
		a.belling();
		
		double d=Double.parseDouble("440.24");
		boolean b=new Boolean("true").booleanValue();
		out.println(d+" "+b);
	}
	
}