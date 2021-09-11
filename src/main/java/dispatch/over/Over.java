package dispatch.over;

class Shape {
    public String method(Shape  s) { return "Shape.method(Shape)"; }
    public String method(Circle c) { return "Shape.method(Circle)"; }
    public String method(Rect   r) { return "Shape.method(Rect)"; }
}

class Circle extends Shape {
    public String method(Shape  s) { return "Circle.method(Shape)"; }
    public String method(Circle c) { return "Circle.method(Circle)"; }
    public String method(Rect   r) { return "Circle.method(Rect)"; }
}

class Rect   extends Shape {
    public String method(Shape  s) { return "Rect.method(Shape)"; }
    public String method(Circle c) { return "Rect.method(Circle)"; }
    public String method(Rect   r) { return "Rect.method(Rect)"; }
}

class Over {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rect   = new Rect();
        String value = circle.method(rect);
        System.out.println(value);
    }
}
