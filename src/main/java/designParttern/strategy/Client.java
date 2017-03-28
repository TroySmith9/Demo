package designParttern.strategy;

public class Client {
	public static void main(String[] args) {
		
		Strategy s1 = new OldCustomerManyStrategy();
		Strategy strategy= new NewCustomerFewStrategy();
		Context ctx = new Context(s1);
		
		ctx.pringPrice(1000);
		
	}
}
