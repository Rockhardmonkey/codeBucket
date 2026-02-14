public class Cone extends Shape {
    private double radius;
    private double height;

    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double surface_area() {
        double slantHeight = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
        return Math.PI * radius * (radius + slantHeight);
    }

    @Override
    public double volume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public String toString() {
        return "Cone{" +
                "radius=" + String.format("%.2f", radius) +
                ", height=" + String.format("%.2f", height) +
                ", surface_area=" + String.format("%.2f", surface_area()) +
                ", volume=" + String.format("%.2f", volume()) +
                '}';
    }
    
}
