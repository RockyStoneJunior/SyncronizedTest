
public class Hello {
	public static void main(String[] args) {
		World wd = new World();
		
		System.out.println(wd.getString());
		
		wd.setString("Hello Java!");
		
		System.out.println(wd.getString());
		
		Rocky rky = new Rocky();
		System.out.println(rky.getString());
		
		wd = rky;
		System.out.println(wd.getString());
	}
}
