
public class TestMath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a= new int[5];
		a[0]=(int) Math.round(42.2);
		a[1]=Math.abs(-4);
		a[2]=Math.min(56, 13);
		a[3]=Math.max(55, 49);
		a[4]=Math.getExponent(3.14);
		for(int i:a) {
			System.out.println(i);
		}
	}

}
