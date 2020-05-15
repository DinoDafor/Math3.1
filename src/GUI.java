import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

     JTextField input = new JTextField("", 5);


    public GUI() {


        super("Graph of function");
        Storage.createNodules(1);
        Storage.createDataForGraph();

        Container container = getContentPane();

        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Сalculate");
        button.addActionListener(e -> {
            try {
                double x = Double.parseDouble(input.getText().replace(",",".").trim());
                double y = InterpolateLagrangePolynomial.getY(x);
                String message = "Approximate value  of the function at point x=" + x + " " + "is: " + y;
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException e1) {
                e1.getMessage();
            }
        });
        JRadioButton radio1 = new JRadioButton("First sample", true);
        radio1.addActionListener(e -> {
            updateAllData(1);
            repaintGraph(container);
        });
        JRadioButton radio2 = new JRadioButton("Second sample");
        radio2.addActionListener(e -> {
            updateAllData(2);
            repaintGraph(container);
        });
        JRadioButton radio3 = new JRadioButton("Third sample");
        radio3.addActionListener(e -> {
            updateAllData(3);
            repaintGraph(container);
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);

        container.add(radio1);
        container.add(radio2);
        container.add(radio3);
        JLabel label = new JLabel("X-coordinate: ");
        container.add(label);
        container.add(input);
        container.add(button);
        JPanel jPanel = createPanel(createChart(createDataSet()));
        container.add(jPanel);

        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));



    }

    private XYDataset createDataSet() {
        XYSeries series1 = new XYSeries("Graph of function sin(x)*sqrt(x+PI*2)");
        for (int i = 0; i < Storage.getCoordinatesXGraph().size(); i++) {
            series1.add(Storage.getCoordinatesXGraph().get(i), Storage.getCoordinatesYGraph().get(i));
        }
        XYSeries series2 = new XYSeries("Graph of polynomial");
        for (int i = 0; i < Storage.getCoordinatesXPolGraph().size(); i++) {
            series2.add(Storage.getCoordinatesXPolGraph().get(i), Storage.getCoordinatesYPolGraph().get(i));
        }
        XYSeries series3 = new XYSeries("Interpolation nodes");
        for (int i = 0; i < Storage.getCoordinatesXNodules().size(); i++) {
            series3.add(Storage.getCoordinatesXNodules().get(i), Storage.getCoordinatesYNodules().get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Graph", "X", "Y", dataset);
        return customizationChart(chart);
    }


    private JFreeChart customizationChart(JFreeChart chart) {
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(2, true);
        return chart;
    }
    private void updateAllData(int mode){
        Storage.createNodules(mode);
        Storage.createDataForGraph();
    }
    private void repaintGraph(Container container){
        container.remove(6);
        container.add(createPanel(createChart(createDataSet())));
        container.revalidate();
        container.repaint();
    }

    public JPanel createPanel(JFreeChart chart) {
        return new ChartPanel(chart);

    }
}
