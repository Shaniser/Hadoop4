import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Tester {
    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("var f = function(a, b) {return a + b}");
        Invocable invocable = (Invocable) engine;
        System.out.println(invocable.invokeFunction("f", params).toString());

    }
}
