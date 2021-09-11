package dispatch.name;

class Shape {
    public String method_Shape (Shape  s) { return "Shape.method_Shape"; }
    public String method_Circle(Circle c) { return "Shape.method_Circle"; }
    public String method_Rect  (Rect   r) { return "Shape.method_Rect"; }
}

class Circle extends Shape {
    public String method_Shape (Shape  s) { return "Circle.method_Shape"; }
    public String method_Circle(Circle c) { return "Circle.method_Circle"; }
    public String method_Rect  (Rect   r) { return "Circle.method_Rect"; }
}

class Rect   extends Shape {
    public String method_Shape (Shape  s) { return "Rect.method_Shape"; }
    public String method_Circle(Circle c) { return "Rect.method_Circle"; }
    public String method_Rect  (Rect   r) { return "Rect.method_Rect"; }
}

class Name {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rect   = new Rect();
        String value = circle.method_Shape(rect);
        System.out.println(value);
    }
}
