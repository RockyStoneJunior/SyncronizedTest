import java.lang.reflect.*;
import java.math.BigDecimal;

interface Interface{
	void doSomething();
	@Deprecated
	void test1();
	void somethingElse(String arg);
	void test();
}

class RealObject implements Interface{
	@Override
	public void doSomething() {System.out.println("doSomething");}
	public void somethingElse(String arg) {
		System.out.println("somethingElse " + arg);
	}
	
	public void test() {System.out.println("hello world!");};
	
	@Override
	public void test1() {System.out.println("hello test1!");};
}

class DynamicProxyHandler implements InvocationHandler{
	private Object proxied;
	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		
		if(method.getName().equals("test"))
		{
			System.out.println("*** proxy: " + proxy.getClass() + 
					", method: " + method + ", args: " + args);
			
			if(args != null)
				for(@SuppressWarnings("unused") Object arg : args)
					System.out.println(" " + args);
		}
				
		return method.invoke(proxied, args);
		
//		method.invoke(proxied, args);
//		return null;
	}
}


public class ProxyDemo {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("bonobo");
		iface.test();
		iface.test1();
	}
	
	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		
		
		Interface proxy = (Interface)Proxy.newProxyInstance(  //Object downcast to Interface
				Interface.class.getClassLoader(),
				new Class[] {Interface.class}, 
				new DynamicProxyHandler(real));
		consumer(proxy);
		
		Long lon = Double.doubleToLongBits(0.15625);
		String str = Long.toHexString(lon);
		
		System.out.println(str);
	}
}














