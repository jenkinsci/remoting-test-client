package test;

import hudson.remoting.Callable;
import java.util.function.Function;
import org.jenkinsci.remoting.RoleChecker;

/**
 * @author Kohsuke Kawaguchi
 */
public class ClassLoadingFromJarTester implements Callable<Object,Exception>, Function<Function,Void> {
    public Function verifier;

    @Override
    public Object call() throws Exception {
        // verify that the tester is loaded into the correct state
        return verifier.apply(this);
    }

    // just so that we can set the delegate without reflection
    @Override
    public Void apply(Function function) {
        this.verifier = function;
        return null;
    }

    @Override
    public void checkRoles(RoleChecker checker) throws SecurityException {
        throw new UnsupportedOperationException();
    }
}
