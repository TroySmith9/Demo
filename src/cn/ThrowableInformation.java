package cn;

import java.io.Writer;
import java.io.PrintWriter;
import java.util.Vector;

public class ThrowableInformation implements java.io.Serializable {

	private transient Throwable throwable;

	private String[] rep;

	public ThrowableInformation(Throwable throwable) {
		this.throwable = throwable;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public String[] getThrowableStrRep() {
		if (rep != null) {
			return (String[]) rep.clone();
		} else {
			VectorWriter vw = new VectorWriter();
			throwable.printStackTrace(vw);
			rep = vw.toStringArray();
			return rep;
		}
	}

}

class VectorWriter extends PrintWriter {
	private Vector<String> v;

	VectorWriter() {
		super(new NullWriter());
		v = new Vector<String>();
	}

	public void print(Object o) {
		v.addElement(o.toString());
	}

	public void print(char[] chars) {
		v.addElement(new String(chars));
	}

	public void print(String s) {
		v.addElement(s);
	}

	public void println(Object o) {
		v.addElement(o.toString());
	}

	public void println(String s) {
		v.addElement(s);
	}

	// public void write(char[] chars) {
	// v.addElement(new String(chars));
	// }
	//
	// public void write(char[] chars, int off, int len) {
	//
	// v.addElement(new String(chars, off, len));
	//
	// }
	//
	// public void write(String s, int off, int len) {
	// v.addElement(s.substring(off, off + len));
	// }
	//
	// public void write(String s) {
	// v.addElement(s);
	// }

	public String[] toStringArray() {
		int len = v.size();
		String[] sa = new String[len];
		for (int i = 0; i < len; i++) {
			sa[i] = (String) v.elementAt(i);
		}
		return sa;
	}

}

class NullWriter extends Writer {

	public void close() {
	}

	public void flush() {
	}

	public void write(char[] cbuf, int off, int len) {
	}

}