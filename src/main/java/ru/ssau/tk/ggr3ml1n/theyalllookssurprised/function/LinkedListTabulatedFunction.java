package ru.ssau.tk.ggr3ml1n.theyalllookssurprised.function;

import ru.ssau.tk.ggr3ml1n.theyalllookssurprised.exeptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;

import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {

    private static final long serialVersionUID = 4099715325863317331L;
    private Node head;

    protected static class Node implements Serializable {
        private static final long serialVersionUID = 1465232404893441735L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    private void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;
        } else {
            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
            last.next = newNode;
        }
        count += 1;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        if ((xFrom >= xTo) || (xFrom < 0) | (xTo < 0)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        double[] xValues = new double[count];
        xValues[0] = xFrom;
        final double step = (xTo - xFrom) / (count - 1);
        this.addNode(xValues[0], source.apply(xValues[0]));
        for (int i = 1; i < count; i++) {
            xValues[i] = xValues[i - 1] + step;
            this.addNode(xValues[i], source.apply(xValues[i]));
        }
    }


    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("X is less than the left border");
        }
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateRight(double x) {

        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }


    @Override
    protected double extrapolateLeft(double x) {

        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    public int getCount() {
        return count;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        if (x < head.x || x > head.prev.x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, left.x, right.x, left.y, right.y);
    }


    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    private Node getNode(int index) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return indexNode;
            }
            indexNode = indexNode.next;
        }
        return new Node();
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }

}


