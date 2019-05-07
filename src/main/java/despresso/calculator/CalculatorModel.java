package despresso.calculator;

public class CalculatorModel {
    private double value = 0.0;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void clear() {
        value = 0.0;
    }

    public void add(double arg) {
        value += arg;
    }

    public void multiply(double arg) {
        value *= arg;
    }

    public void divide(double arg) {
        value /= arg;
    }

    public static interface CalculatorViewSubjectInterface {

        void setDisplay(double value);

        void addListener(CalculatorViewObserverInterface listener);
    }
}