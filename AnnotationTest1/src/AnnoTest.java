import java.util.*;
import java.lang.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UseCase{
	public int id();
	public String description() default "no description";
}

class PasswordUtils {
	@UseCase(id = 47, description =
	"Password must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}
	
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
	
	@UseCase(id = 49, description =
	"New passwords can't equal previously used ones")
	public boolean checkForNewPassword(
	List<String> prevPasswords, String password) {
		return !prevPasswords.contains(password);
	}
}

public class AnnoTest {
	public static void
	trackUseCases(List<Integer> useCases, Class<?> cl) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Method m : cl.getDeclaredMethods()) {
			UseCase uc = m.getAnnotation(UseCase.class); //polymophism
			if(uc != null) {
				System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
				useCases.remove(new Integer(uc.id()));
			}
		}
		
		for(int i : useCases) {
			System.out.println("Warning: Missing use case-" + i);
		}
	}
		
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 46, 47, 48, 49, 50);
		trackUseCases(useCases, PasswordUtils.class);
	}
	
}





















