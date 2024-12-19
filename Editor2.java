public class Editor2 {
    public static void main(String[] args) {
        String filename = args[0];
        int newWidth = Integer.parseInt(args[1]);
        int newHeight = Integer.parseInt(args[2]);

        Color[][] image = Runigram.read(filename);
        Color[][] scaledImage = Runigram.scaled(image, newWidth, newHeight);

        Runigram.display(image);
        StdDraw.pause(1000);
        Runigram.display(scaledImage);
    }
}