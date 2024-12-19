public class Editor1 {
    public static void main(String[] args) throws Exception {
        String filename = args[0];
        String operation = args[1];

        Color[][] image = Runigram.read(filename);
        Runigram.display(image);

        Color[][] result = switch (operation) {
            case "fh" -> Runigram.flippedHorizontally(image);
            case "fv" -> Runigram.flippedVertically(image);
            case "gs" -> Runigram.greyScaled(image);
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };

        StdDraw.pause(2000);
        Runigram.display(result);
    }
}