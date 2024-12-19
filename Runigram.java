import java.awt.Color;

public class Runigram {

    // Reads a PPM file and creates a 2D array of Color objects
    public static Color[][] read(String filename) {
        In in = new In(filename);
        in.readLine(); // Skip the P3 header
        int width = in.readInt();
        int height = in.readInt();
        in.readInt(); // Skip maxColorValue (255)

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
                System.out.printf("(%3d, %3d, %3d) ", pixel.getRed(), pixel.getGreen(), pixel.getBlue());
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
            for (int j = 0; j < width; j++) {
                flipped[height - i - 1][j] = image[i][j];
            }
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

    // Blends two images with a given alpha value
    public static Color[][] blend(Color[][] img1, Color[][] img2, double alpha) {
        int height = img1.length;
        int width = img1[0].length;
        Color[][] blended = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c1 = img1[i][j];
                Color c2 = img2[i][j];

                int red = (int) ((1 - alpha) * c1.getRed() + alpha * c2.getRed());
                int green = (int) ((1 - alpha) * c1.getGreen() + alpha * c2.getGreen());
                int blue = (int) ((1 - alpha) * c1.getBlue() + alpha * c2.getBlue());

                blended[i][j] = new Color(red, green, blue);
            }
        }
        return blended;
    }

    // Displays an image using StdDraw
    public static void display(Color[][] image) {
        int height = image.length;
        int width = image[0].length;

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                StdDraw.setPenColor(image[i][j]);
                StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
            }
        }
        StdDraw.show();
    }
}