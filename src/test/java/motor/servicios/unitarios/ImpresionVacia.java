package motor.servicios.unitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ImpresionVacia extends PrintStream {

	public ImpresionVacia(String fileName) throws FileNotFoundException {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(File file, Charset charset) throws IOException {
		super(file, charset);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(File file) throws FileNotFoundException {
		super(file);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(OutputStream out, boolean autoFlush, Charset charset) {
		super(out, autoFlush, charset);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(String fileName, Charset charset) throws IOException {
		super(fileName, charset);
		// TODO Auto-generated constructor stub
	}

	public ImpresionVacia(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void print(String s) {
		
	}

	@Override
	public void println(String x) {
		
	}
	
	

}
