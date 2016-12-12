package Dejan_Package_4;

public class myMainClass {

	public static void main(String[] args) {
		String myString  = "Test";
		
		if(myString == "Test"){
			System.out.print("Test Passes");
		}
		
		mySuperClass mySuperClassObject = new mySuperClass();
		myChildClass myChildClassObject = new myChildClass();
//		mySuperClassObject.test();
	}
}
