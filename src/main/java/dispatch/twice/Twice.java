package dispatch.twice;

interface Shape {
    public String method(Shape  s);
    public String method(Circle c);
    public String method(Rect   r);
}

class Circle implements Shape {
    public String method(Shape  s) { return s.method(this); }
    public String method(Circle c) { return "Circle/Circle"; }
    public String method(Rect   r) { return "Circle/Rect"; }
}

class Rect   implements Shape {
    public String method(Shape  s) { return s.method(this); }
    public String method(Circle c) { return "Rect/Circle"; }
    public String method(Rect   r) { return "Rect/Rect"; }
}

class Twice {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rect   = new Rect();
        String value = circle.method(rect);
        System.out.println(value);
    }
}
