public class Editor3 {
    public static void main(String[] args) {
        String sourceFile = args[0];
        String targetFile = args[1];
        int steps = Integer.parseInt(args[2]);

        Color[][] sourceImage = Runigram.read(sourceFile);
        Color[][] targetImage = Runigram.read(targetFile);

        Runigram.display(sourceImage);

        for (int step = 0; step <= steps; step++) {
            double alpha = (double) (steps - step) / steps;
            Color[][] blended = Runigram.blend(sourceImage, targetImage, alpha);
            Runigram.display(blended);
            StdDraw.pause(500);
        }
    }
}