public class Editor1 {
    public static void main(String[] args) {
        String filename = args[0];
        String operation = args[1];

        Color[][] image = Runigram.read(filename);

        Color[][] processedImage = switch (operation) {
            case "fh" -> Runigram.flippedHorizontally(image);
            case "fv" -> Runigram.flippedVertically(image);
            case "gs" -> Runigram.greyScaled(image);
            default -> throw new IllegalArgumentException("Invalid operation");
        };

        Runigram.display(image);
        StdDraw.pause(1000);
        Runigram.display(processedImage);
    }
}