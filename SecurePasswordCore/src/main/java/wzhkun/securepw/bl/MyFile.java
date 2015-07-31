package wzhkun.securepw.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyFile {
	
	public static MyFile toMyFile(final File file){
		MyFile result=new MyFile();
		result.setInputStream(new IOStreamSupplier<InputStream>() {
			
			@Override
			public InputStream get() throws IOException {
				return new FileInputStream(file);				
			}
		});
		
		result.setOutputStream(new IOStreamSupplier<OutputStream>() {
			
			@Override
			public OutputStream get() throws FileNotFoundException {
				return new FileOutputStream(file);
			}
		});
		return result;
	}
	
	private IOStreamSupplier<InputStream> inputStream;
	private IOStreamSupplier<OutputStream> outputStream;
	
	public InputStream getInputStream() throws IOException {
		return inputStream.get();
	}
	public void setInputStream(IOStreamSupplier<InputStream> inputStream) {
		this.inputStream = inputStream;
	}
	public OutputStream getOutputStream() throws IOException {
		return outputStream.get();
	}
	public void setOutputStream(IOStreamSupplier<OutputStream> outputStream) {
		this.outputStream = outputStream;
	}
	
}
