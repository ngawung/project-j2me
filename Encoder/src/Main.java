import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;


public class Main {

	public static void main(String[] args) {	
		try {
			String[] list = {
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/1.gif",
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/2.gif",
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/3.gif",
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/4.gif",
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/5.gif",
					"/home/melody/SharedVM/ml j2me/heroes/layla/op/6.gif",
			};
			
			File file;
			FileInputStream in;
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			GZIPOutputStream gzip = new GZIPOutputStream(out);
//			DataOutputStream dos = new DataOutputStream(gzip);
			DataOutputStream dos = new DataOutputStream(out);
			
			int total = 0;
			
			for(int i=0; i<list.length; i++) {
				in = new FileInputStream(list[i]);
				int length = in.available();
				byte[] buffer = new byte[length];
				
				total += length;
				System.out.println(total);
//				System.out.println(length);
				
				in.read(buffer);
				dos.write(buffer);
			}
			
//			gzip.close();
			byte[] out_result = out.toByteArray();
			dos.close();
			out.close();
			System.out.println("Total: " + out_result.length);
			
			try (FileOutputStream fos = new FileOutputStream("compress")) {
			   fos.write(out_result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}