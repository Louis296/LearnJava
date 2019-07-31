
abstract class Animal{
	private String name;
	public String getname(){
		return name;
	}
	public Animal(String n) {
		name=n;
	}
}

class dog extends Animal{
	public dog(String n) {
		super(n);
	}
	public void sayname(){
		System.out.println("My name is "+getname());
	}
}

public class AnimalPlaying {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dog d=new dog("Diannao");
		d.sayname();
	}
}
