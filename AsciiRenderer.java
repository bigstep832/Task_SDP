public class AsciiRenderer implements Renderer {
    @Override
    public String render(String shapeDescription) {
        return "ASCII <" + shapeDescription + ">";
    }
}
