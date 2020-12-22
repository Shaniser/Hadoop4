import javax.script.ScriptEngine;

public class Tester {
    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(jscript);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, params).toString();

    }
}
