public class RasterRenderer implements Renderer {
    @Override
    public String render(String shapeDescription) {
        return "RASTER [" + shapeDescription + "]";
    }
}
