import com.example.test.ImageProtos.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import com.google.protobuf.ByteString;

class RetrieveImage {
	static void printToFile(Image img) throws IOException{
		String img_file_name = img.getName();
		byte[] image_content = img.getImg().toByteArray();
		File file = new File(img_file_name + "_enc");
		OutputStream os = new FileOutputStream(file);
		os.write(image_content);
		os.close();
	}

	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			System.err.println("Usage : FILE_NAME");
			System.exit(-1);
		}
		Image img = Image.parseFrom(new FileInputStream(args[0]));
		printToFile(img);
	}
}