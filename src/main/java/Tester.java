import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Tester {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("var f = function(a, b) {return a + b}");
        Invocable invocable = (Invocable) engine;
        System.out.println(invocable.invokeFunction("f", 5, 12).toString());

    }
}
