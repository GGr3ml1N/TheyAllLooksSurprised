package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.ui;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.factory.TabulatedFunctionFactory;
import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.operations.TabulatedDifferentialOperator;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DifferentialOperationWindow extends JFrame {
    JButton diff = new JButton();
    JButton createTubFuncOne = new JButton();
    JButton createMathFuncOne = new JButton();
    JButton saveOne = new JButton();
    JButton downloadOne = new JButton();
    JButton saveThree = new JButton();
    private final TabulatedDifferentialOperator diffOperator = new TabulatedDifferentialOperator();
    private final TableModelMainWindow tableModel1 = new TableModelMainWindow();
    private final TableModelWindowForResult tableModel2 = new TableModelWindowForResult();
    private final JTable table1 = new JTable(tableModel1);
    private final JTable table2 = new JTable(tableModel2);
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final TabulatedFunctionFactory factory;

    public DifferentialOperationWindow() {
        setTitle("Нахождение производной");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 1200, 700);
        designButton(diff, "Результат");
        getContentPane().add(diff);

        designButton(createTubFuncOne, "Создать через массивы");
        designButton(createMathFuncOne, "Создать через функцию");
        designButton(saveOne, "Сохранить");
        designButton(downloadOne, "Загрузить");
        getContentPane().add(createTubFuncOne);
        getContentPane().add(createMathFuncOne);
        getContentPane().add(saveOne);
        getContentPane().add(downloadOne);

        designButton(saveThree, "Сохранить");
        getContentPane().add(saveThree);
        compose();
        setLocationRelativeTo(null);
        this.factory = new ArrayTabulatedFunctionFactory();

        createTubFuncOne.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedFunctionWindow.main(factory, data -> tableModel1.setFunction(data));
                        int countNew = tableModel1.getFunction().getCount();
                        wrapTable(tableModel1, countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorsWindow(this, e);
                    }
                }
        );

        createMathFuncOne.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathFunctionWindow.main(factory, data -> tableModel1.setFunction(data));
                int countNew = tableModel1.getFunction().getCount();
                wrapTable(tableModel1, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        saveOne.addActionListener(event -> {
            try {
                FileWriter.main(tableModel1.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        saveThree.addActionListener(event -> {
            try {
                FileWriter.main(tableModel2.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        downloadOne.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel1.setFunction(data));
                int countNew = tableModel1.getFunction().getCount();
                wrapTable(tableModel1, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        diff.addActionListener(event -> {
            try {
                int countOld = tableModel1.getFunction().getCount();
                tableModel2.setFunction(diffOperator.derive(tableModel1.getFunction()));
                int countNew = tableModel2.getFunction().getCount();
                wrapTableForResult(tableModel2, countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
    }

    public static void main() {
        DifferentialOperationWindow window = new DifferentialOperationWindow();
        window.setVisible(true);
        window.setResizable(false);
    }

    public void wrapTable(TableModelMainWindow tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    public void wrapTableForResult(TableModelWindowForResult tableModel, int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane firstTableScrollPane = new JScrollPane(table1);
        JScrollPane resultTableScrollPane = new JScrollPane(table2);
        designTable(table1, firstTableScrollPane);
        designTable(table2, resultTableScrollPane);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstTableScrollPane)
                        .addComponent(diff)
                        .addComponent(resultTableScrollPane))

                .addGroup(layout.createSequentialGroup()
                        .addComponent(createTubFuncOne)
                        .addComponent(createMathFuncOne))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(saveOne)
                        .addComponent(downloadOne)
                        .addGap(438)
                        .addComponent(saveThree)));


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(diff)
                        )
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createTubFuncOne)
                        .addComponent(createMathFuncOne))
                .addGroup(layout.createParallelGroup()
                        .addComponent(saveOne)
                        .addComponent(downloadOne)
                        .addComponent(saveThree)));
        setLocationByPlatform(true);
    }

    public void designButton(JButton button, String name) {
        button.setText(name);
        button.setBackground(Color.pink);
        button.setFocusPainted(false);
    }

    public void designTable(JTable designedTable, JScrollPane designedPane) {
        UIManager.put("ScrollPane.thumb", new ColorUIResource(Color.BLACK));
        designedPane.setUI(new BasicScrollPaneUI());
        UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(186, 177, 173, 225)));
        designedPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        designedPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        designedPane.getViewport().setBackground(new Color(255, 248, 224));  //фон панели
        designedTable.setBackground(new Color(255, 248, 224)); //фон полей таблицы
        designedTable.getTableHeader().setBackground(Color.pink);
        designedTable.getTableHeader().setForeground(Color.DARK_GRAY);
        designedTable.setSelectionBackground(new Color(220, 194, 184));
        designedTable.getTableHeader().setFont(new Font("VVV", Font.BOLD, 14));
        designedPane.setBackground(new Color(235, 205, 193));
        designedPane.setForeground(Color.DARK_GRAY);
        designedPane.setViewportView(designedTable);
    }
}

