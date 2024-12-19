import java.awt.Color;

public class Runigram {

    // Reads a PPM file and creates a 2D array of Color objects
    public static Color[][] read(String filename) {
        In in = new In(filename);
        in.readLine(); // Skip the P3 header
        int width = in.readInt();
        int height = in.readInt();
        int maxColorValue = in.readInt(); // Read but not used
        
        Color[][] image = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int red = in.readInt();
                int green = in.readInt();
                int blue = in.readInt();
                image[i][j] = new Color(red, green, blue);
            }
        }
        return image;
    }

    // Prints a 2D array of Color objects
    public static void print(Color[][] image) {
        for (Color[] row : image) {
            for (Color pixel : row) {
                System.out.print("(" + pixel.getRed() + ", " + pixel.getGreen() + ", " + pixel.getBlue() + ") ");
            }
            System.out.println();
        }
    }

    // Horizontally flips the image
    public static Color[][] flippedHorizontally(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] flipped = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flipped[i][width - j - 1] = image[i][j];
            }
        }
        return flipped;
    }

    // Vertically flips the image
    public static Color[][] flippedVertically(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] flipped = new Color[height][width];

        for (int i = 0; i < height; i++) {
            flipped[height - i - 1] = image[i];
        }
        return flipped;
    }

    // Converts an image to greyscale
    public static Color[][] greyScaled(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] greyscaled = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = image[i][j];
                int lum = (int) (0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue());
                greyscaled[i][j] = new Color(lum, lum, lum);
            }
        }
        return greyscaled;
    }

    // Scales an image
    public static Color[][] scaled(Color[][] image, int newWidth, int newHeight) {
        int oldHeight = image.length;
        int oldWidth = image[0].length;
        Color[][] scaled = new Color[newHeight][newWidth];

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                int srcI = i * oldHeight / newHeight;
                int srcJ = j * oldWidth / newWidth;
                scaled[i][j] = image[srcI][srcJ];
            }
        }
        return scaled;
    }
}