import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.*;

public class HelloWorld {
	public static void main(String args[]) throws IOException{
		System.out.println("Hello World!");
		
		int c;
		try {
			InputStream in =
					new LowerCaseInputStream(
						new BufferedInputStream(
							new FileInputStream("test.txt")
								));
			
			while((c = in.read()) >= 0) {
				System.out.print((char)c);
			}
			
			in.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class LowerCaseInputStream extends FilterInputStream{
	public LowerCaseInputStream(InputStream in) {
		super(in);
	}
	
	public int read() throws IOException{
		int c = super.read();
		return (c == -1 ? c : Character.toLowerCase((char)c));
	}
	
	public int read(byte[] b, int offset, int len) throws IOException{
		int result = super.read(b, offset, len);
		
		for(int i = offset; i < offset + result; i++) {
			b[i] = (byte)Character.toLowerCase((char)b[i]);
		}
		
		return result;
	}
}
