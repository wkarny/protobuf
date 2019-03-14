//package com.example.test;
import com.example.test.ImageProtos.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.google.protobuf.ByteString;

class AddImage {
	static Image add(){
		Image.Builder str = Image.newBuilder();
		str.setName("Image 1");
		//ByteString bstr = new ByteString();
		File file = new File("test_img.png");
    try {
  		FileInputStream fis = new FileInputStream(file);
  		byte cnt[] = new byte[(int)file.length()];
  		fis.read(cnt);
  		str.setImg(ByteString.copyFrom(cnt));
    }
    catch(Exception e) {
        System.out.println("Exception");
    }
    //bstr.copyFrom(cnt);
		return str.build();
	}

	public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage:  FILE_NAME");
      System.exit(-1);
    }

    long t = System.currentTimeMillis();

    Image image = add();
    ;
    FileOutputStream output = new FileOutputStream(args[0]);
    try {
      image.writeTo(output);
    } finally {
      output.close();
    }

    System.out.println("Time required to encode : " + (System.currentTimeMillis() - t));
  }
}