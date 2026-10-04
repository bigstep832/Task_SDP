public class VectorRenderer implements Renderer {
    @Override
    public String render(String shapeDescription) {
        return "VECTOR " + shapeDescription;
    }
}
