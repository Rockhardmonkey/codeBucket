public class shapeArray {
    public static void main(String[] args) {
        Shape[] shapeArray = new Shape[3];
        shapeArray[0] = new Sphere(1);
        shapeArray[1] = new Cylinder(1, 2);
        shapeArray[2] = new Cone(1, 2);
        
        for (Shape shape : shapeArray) {
            System.out.println(shape.toString());
        }
    }
}
