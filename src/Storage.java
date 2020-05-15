import java.util.ArrayList;

public class Storage {



    private static ArrayList<Double> coordinatesXNodules = new ArrayList<>();
    private static ArrayList<Double> coordinatesYNodules = new ArrayList<>();
    private static ArrayList<Double> coordinatesXGraph = new ArrayList<>();
    private static ArrayList<Double> coordinatesYGraph = new ArrayList<>();
    private static ArrayList<Double> coordinatesXPolGraph = new ArrayList<>();
    private static ArrayList<Double> coordinatesYPolGraph = new ArrayList<>();


    public static void createNodules(int mode) {
        Storage.cleanNodules();
        switch (mode) {
            case 1:
                for (double i = -2 * Math.PI; i <= 2 * Math.PI; i += 1.5) {
                    Storage.addNodulePoint(i,MathFunction.getY(i));
                }
                break;
            case 2:
                for (double i = -2 * Math.PI + 0.2; i <= 2 * Math.PI; i += 0.96) {
                    Storage.addNodulePoint(i,MathFunction.getY(i));
                }
                break;
            case 3:
                for (double i = -2 * Math.PI; i <= 5 * Math.PI; i += 4.6) {
                    Storage.addNodulePoint(i,MathFunction.getY(i));
                }
                break;
        }
    }

    public static void createDataForGraph() {
        Storage.cleanGraphCoordinates();
        for (double i = -2 * Math.PI; i <= 2.25 * Math.PI; i += 0.1) {
            Storage.addPointGraph(i, MathFunction.getY(i), InterpolateLagrangePolynomial.getY(i));
        }
    }

    public static void addNodulePoint(double x, double y) {
        coordinatesXNodules.add(x);
        coordinatesYNodules.add(y);
    }

    public static void addPointGraph(double xGraph, double yGraph, double yPolGraph) {
        coordinatesXGraph.add(xGraph);
        coordinatesYGraph.add(yGraph);
        coordinatesXPolGraph.add(xGraph);
        coordinatesYPolGraph.add(yPolGraph);

    }

    private static void cleanNodules() {
        coordinatesXNodules = new ArrayList<>();
        coordinatesYNodules = new ArrayList<>();
    }

    private static void cleanGraphCoordinates() {
        coordinatesXGraph = new ArrayList<>();
        coordinatesYGraph = new ArrayList<>();
        coordinatesXPolGraph = new ArrayList<>();
        coordinatesYPolGraph = new ArrayList<>();
    }

    public static ArrayList<Double> getCoordinatesXNodules() {
        return coordinatesXNodules;
    }

    public static ArrayList<Double> getCoordinatesYNodules() {
        return coordinatesYNodules;
    }

    public static ArrayList<Double> getCoordinatesXGraph() {
        return coordinatesXGraph;
    }

    public static ArrayList<Double> getCoordinatesYGraph() {
        return coordinatesYGraph;
    }

    public static ArrayList<Double> getCoordinatesXPolGraph() {
        return coordinatesXPolGraph;
    }

    public static ArrayList<Double> getCoordinatesYPolGraph() {
        return coordinatesYPolGraph;
    }
}
