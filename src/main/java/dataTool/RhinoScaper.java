package dataTool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;


public class RhinoScaper {
	private String url;
	private String jsFile;
	private Context cx;
	private Scriptable scope;
	public String getUrl() {
		return url;
	}
	public String getJsFile() {
		return jsFile;
	}
	public void setUrl(String url) {
		this.url = url;
		putObject("url", url);
	}
	public void setJsFile(String jsFile) {
		this.jsFile = jsFile;
	}
	public void init() {
		cx = ContextFactory.getGlobal().enterContext();
		scope = cx.initStandardObjects(null);
		cx.setOptimizationLevel(-1);
		cx.setLanguageVersion(Context.VERSION_1_5);
		String[] file = {"G:/chromeDownload/env-js-master/src/env.js","G:/chromeDownload/env-js-master/test/jquery.js" };
	    evaluateJs(file[0]);
	    String s =  cx.evaluateString(scope, "window.location='http://www.cngold.org/qihuo/hushen.html'", "windowLocation", 1, null).toString();
	    System.out.println(cx.evaluateString(scope, "typeof QuotList", "sdfsdf", 1, null));
	    evaluateJs(file[1]);
	}
	protected void evaluateJs(String f) {
		try {
			FileReader in = null;
			in = new FileReader(f);
			cx.evaluateReader(scope, in, f, 1, null);
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void putObject(String name, Object o) {
		scope.put(name, scope, o);
	}
	public void run() {

		evaluateJs(this.jsFile);
	}
	public static void main(String[] args)
	{
		RhinoScaper rs = new RhinoScaper();
		rs.init();
	}

}