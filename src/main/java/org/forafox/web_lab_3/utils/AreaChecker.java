package org.forafox.web_lab_3.utils;

import org.forafox.web_lab_3.Dot;

public class AreaChecker {
    public static boolean isHit(Dot dot) {
        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();

        return (isCircleZone(x, y, r) || isTriangleZone(x, y, r) || isRectangleZone(x, y, r));
    }

    private static boolean isRectangleZone(double x, double y, double r) {
        return (x >= 0) && (x <= r / 2) && (y <= 0) && (y >= -r);
    }

    private static boolean isCircleZone(double x, double y, double r) {
        return (x * x + y * y <= r / 2 * r / 2) && (x >= 0) && (y >= 0);
    }

    private static boolean isTriangleZone(double x, double y, double r) {
//        (1, 2, 3 - вершины треугольника, 0 - точка)
        double x1 = -r / 2, x2 = 0, x3 = 0, y1 = 0, y2 = 0, y3 = -r / 2;
        double a1 = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
        double a2 = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
        double a3 = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);

        return ((x <= 0) && (y <= 0) && (a1 >= 0 && a2 >= 0 && a3 >= 0) || (a1 <= 0 && a2 <= 0 && a3 <= 0));
    }
}
