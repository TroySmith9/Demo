package cn;

import org.apache.log4j.Category;

public class ThrowableInformation1 implements java.io.Serializable {

	static final long serialVersionUID = -4748765566864322735L;

	private transient Throwable throwable;

	private transient Category category;

	private String[] rep;

	public ThrowableInformation1(Throwable throwable) {
		this.throwable = throwable;
	}

	public ThrowableInformation1(Throwable throwable, Category category) {
		this.throwable = throwable;
		this.category = category;
	}

	/**
	 * Create new instance.
	 * @since 1.2.15
	 * @param r String representation of throwable.
	 */
	public ThrowableInformation1(final String[] r) {
		if (r != null) {
			rep = (String[]) r.clone();
		}
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public synchronized String[] getThrowableStrRep() {
//		if (rep == null) {
//			ThrowableRenderer renderer = null;
//			if (category != null) {
//				LoggerRepository repo = category.getLoggerRepository();
//				if (repo instanceof ThrowableRendererSupport) {
//					renderer = ((ThrowableRendererSupport) repo).getThrowableRenderer();
//				}
//			}
//			if (renderer == null) {
//				rep = DefaultThrowableRenderer.render(throwable);
//			} else {
//				rep = renderer.doRender(throwable);
//			}
//		}
		return (String[]) rep.clone();
	}
}