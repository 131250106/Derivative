package dataTool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptExecutor {
       String scriptName;
       ScriptEngineManager sem;
       ScriptEngine se;
       public ScriptExecutor(String scriptName)
       {
    	   this.scriptName = scriptName;
    	   sem = new ScriptEngineManager();
    	   se = sem.getEngineByName(this.scriptName);
       }
       
       public Object eval(String script) throws ScriptException
       {
    	  return se.eval(script);
       }
       
       public Object evalScriptFile(String filePath) throws ScriptException
       {
			FileReader fr = null;
			try {
				fr = new FileReader(filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return se.eval(fr);
			
       }
       
       public static void main(String[] args) throws ScriptException
       {
    	   ScriptExecutor scriptExecutor  = new ScriptExecutor("javascript");
    	   
//    	   scriptExecutor.evalScriptFile("G:/chromeDownload/jquery/jb51.net/jquery 1.10.1/jquery-1.10.1.js");
       }
       
}
