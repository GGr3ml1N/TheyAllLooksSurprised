package function;

import static java.lang.Math.sin;

public class IdentityFunction1 implements MathFunction {
    @Override
    public double apply ( double x) {
        return 1/sin(x);
    }



}
