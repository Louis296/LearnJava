
abstract class Animal{
	private String name;
	static int AnimalCount=0;
	public String getname(){
		return name;
	}
	public Animal(String n) {
		name=n;
		AnimalCount++;
	}
}

class dog extends Animal{
	static int DogCount=0;
	public dog(String n) {
		super(n);
		DogCount++;
	}
	public dog() {
		this("No name");
	}
	public void sayname(){
		System.out.println("My name is "+getname());
	}
}

class cat extends Animal{
	public cat(String n) {
		super(n);
	}
	public cat() {
		this("Null");
	}
	
}
public class AnimalPlaying {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dog d=new dog("Diannao");
		d.sayname();
		dog d1=new dog();
		d1.sayname();
		dog d2=new dog();
		cat c=new cat();
		
		System.out.println(Animal.AnimalCount);
		System.out.println(dog.DogCount);
	}
}
