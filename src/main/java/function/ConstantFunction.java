package function;

public class ConstantFunction implements MathFunction {

    final private double constanta;

    public ConstantFunction(double constanta) {
        this.constanta = constanta;
    }

    @Override
    public double apply(double x) {
        return constanta;
    }

    public double getConstanta() {
        return constanta;
    }
}

