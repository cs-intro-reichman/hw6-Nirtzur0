public class Editor1 {
    public static void main(String[] args) {
        String filename = args[0];
        String operation = args[1];

        Color[][] image = Runigram.read(filename);
        Runigram.print(image);

        Color[][] processedImage = null;

        switch (operation) {
            case "fh":
                processedImage = Runigram.flippedHorizontally(image);
                break;
            case "fv":
                processedImage = Runigram.flippedVertically(image);
                break;
            case "gs":
                processedImage = Runigram.greyScaled(image);
                break;
            default:
                System.out.println("Invalid operation");
                System.exit(1);
        }

        Runigram.print(processedImage);
    }
}