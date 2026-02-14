public class Cylinder extends Shape {
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double surface_area() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    public double volume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + String.format("%.2f", radius) +
                ", height=" + String.format("%.2f", height) +
                ", surface_area=" + String.format("%.2f", surface_area()) +
                ", volume=" + String.format("%.2f", volume()) +
                '}';
    }
    
}
