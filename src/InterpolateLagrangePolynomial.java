import java.util.ArrayList;

public class InterpolateLagrangePolynomial {
    static ArrayList<Double> xValue, yValue;

    static double getY(double x) {
        xValue = Storage.getCoordinatesXNodules();
        yValue = Storage.getCoordinatesYNodules();
        double result = 0;
        for (int i = 0; i < xValue.size(); i++) {
            double multiplier = 1;
            for (int j = 0; j < xValue.size(); j++) {
                if (j != i) {
                    multiplier *= (x - xValue.get(j)) / (xValue.get(i) - xValue.get(j));
                }
            }
            result += multiplier * yValue.get(i);
        }
        return result;
    }
}
