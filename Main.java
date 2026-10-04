public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Run: java -cp out Main --demo");
            return;
        }

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        int passed = 0;

        passed += check("T1", "Circle + VectorRenderer",
                new Circle("C1", 2, vector), "VECTOR circle radius=2");
        passed += check("T2", "Circle + RasterRenderer",
                new Circle("C2", 2, raster), "RASTER [circle radius=2]");
        passed += check("T3", "Square + VectorRenderer",
                new Square("S1", 3, vector), "VECTOR square side=3");
        passed += check("T4", "Square + RasterRenderer",
                new Square("S2", 3, raster), "RASTER [square side=3]");
        passed += checkSwitch(vector, raster);

        Renderer ascii = new AsciiRenderer();
        passed += check("T6", "Circle + AsciiRenderer",
                new Circle("C3", 2, ascii), "ASCII <circle radius=2>");
        passed += check("T7", "Square + AsciiRenderer",
                new Square("S3", 3, ascii), "ASCII <square side=3>");

        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }

    // Compare the real output with an independent expected string.
    private static int check(String id, String classes, Shape shape, String expected) {
        String actual = executeSafely(shape);
        boolean passed = expected.equals(actual);

        System.out.println(id + " " + (passed ? "PASS" : "FAIL")
                + " | " + classes + " | result=" + actual);
        if (!passed) {
            System.out.println("  expected=" + expected);
        }
        return passed ? 1 : 0;
    }

    // Keep the original reference and state before changing the renderer.
    private static int checkSwitch(Renderer vector, Renderer raster) {
        Circle circle = new Circle("C5", 2, vector);
        Circle original = circle;
        String originalId = circle.getId();
        int originalRadius = circle.getRadius();
        String before = executeSafely(circle);

        circle.setImplementation(raster);
        String after = executeSafely(circle);

        boolean sameObject = original == circle;
        boolean stateUnchanged = originalId.equals(circle.getId())
                && originalRadius == circle.getRadius();
        String expectedBefore = "VECTOR circle radius=2";
        String expectedAfter = "RASTER [circle radius=2]";
        boolean passed = sameObject && stateUnchanged
                && expectedBefore.equals(before) && expectedAfter.equals(after);

        System.out.println("T5 " + (passed ? "PASS" : "FAIL")
                + " | Circle + VectorRenderer -> RasterRenderer");
        System.out.println("  sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged);
        System.out.println("  id=" + originalId + " -> " + circle.getId()
                + " | radius=" + originalRadius + " -> " + circle.getRadius());
        System.out.println("  before=" + before + " | after=" + after);
        if (!passed) {
            System.out.println("  expected: sameObject=true | stateUnchanged=true"
                    + " | id=" + originalId + " | radius=" + originalRadius);
            System.out.println("  expected before=" + expectedBefore
                    + " | expected after=" + expectedAfter);
        }
        return passed ? 1 : 0;
    }

    // A renderer error becomes a failed check instead of stopping all tests.
    private static String executeSafely(Shape shape) {
        try {
            return shape.execute();
        } catch (RuntimeException error) {
            return "ERROR: " + error;
        }
    }
}
