import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;


public class Main {

	public static void main(String[] args) {	
		try {
			File file = new File("/home/melody/SharedVM/ml j2me/heroes/out/out_49.jpg");
			File file2 = new File("/home/melody/workspace/JAVA/project-j2me/res/demo/img/melody.png");
			
			FileInputStream in;
			FileInputStream in2;
			
			in = new FileInputStream(file);
			int length = in.available();
			byte[] buffer = new byte[length];
			
			in2 = new FileInputStream(file2);
			int length2 = in2.available();
			byte[] buffer2 = new byte[length2];
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			DataOutputStream dos = new DataOutputStream(gzip);
			
			System.out.println(in.available());
			System.out.println(in2.available());
			
			// convert byte to positive buffer[400] & 0xff
			
			int frame = 0;
			
//			while (in.available() > 0) {
				in.read(buffer);
				in2.read(buffer2);
				
				dos.write(buffer);
				dos.write(buffer2);
				
				frame++;
				System.out.println(frame);
//			}
			
			gzip.close();
			byte[] out_result = out.toByteArray();
			dos.close();
			out.close();
			System.out.println(out_result.length);
			
			try (FileOutputStream fos = new FileOutputStream("compress")) {
			   fos.write(out_result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}