package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.ui;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function.TabulatedFunction;

import javax.swing.table.AbstractTableModel;

public class TableModelMainWindow extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN = 1;
    private static final int Y_COLUMN = 2;
    private TabulatedFunction function;

    public TableModelMainWindow() {
    }

    @Override
    public int getRowCount() {
        return (function == null) ? 0 : function.getCount();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case X_COLUMN:
                return function.getX(rowIndex);
            case Y_COLUMN:
                return function.getY(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
        if (columnIndex == Y_COLUMN) {
            try {
                function.setY(rowIndex, Double.parseDouble(aValue.toString()));
            } catch (Exception e) {

                function.setY(rowIndex, 0.0);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
            case X_COLUMN:
                return false;
            case Y_COLUMN:
                return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case INDEX_COLUMN_NUMBER:
                return "Индекс";
            case X_COLUMN:
                return "X";
            case Y_COLUMN:
                return "Y";
        }
        return super.getColumnName(column);
    }

    public TabulatedFunction getFunction() {
        return function;
    }

    public void setFunction(TabulatedFunction function) {
        this.function = function;
    }
}
