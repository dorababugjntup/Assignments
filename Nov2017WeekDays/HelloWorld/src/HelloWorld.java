import javax.naming.InterruptedNamingException;

public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		for(int i=0;i<args.length;i++){
			System.out.println("arguments are=="+args[i]);
			
		}
		
		Integer i=10;
		System.out.println("Hash code of I"+ i.hashCode());
		String word="hi";
		System.out.println("Hash code of word"+ word.hashCode());
		String i1="10";
		System.out.println("Hash code of I1"+ i1.hashCode());
		
		
		Hash code of word 3329
		Hash code of I1 1567
	}

}
