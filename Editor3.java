public class Editor3 {
    public static void main(String[] args) throws Exception {
        String sourceFile = args[0];
        String targetFile = args[1];
        int steps = Integer.parseInt(args[2]);

        Color[][] source = Runigram.read(sourceFile);
        Color[][] target = Runigram.read(targetFile);

        Runigram.morph(source, target, steps);
    }
}