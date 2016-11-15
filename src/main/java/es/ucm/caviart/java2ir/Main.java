package es.ucm.caviart.java2ir;

import spoon.Launcher;

public class Main {

    static class X {
        public static void main(String[] args) {
            Main.main(new String[]{"src/main/java/testfiles"});
        }
    }

    public static void main(String[] args) {
        if(args.length > 0) {
            System.out.println("Should use path " + args[0]);

            Launcher l = new Launcher();
            l.setArgs(new String[]{
                    "-i", args[0],
                    "-p", ClassProcessor.class.getCanonicalName(),
                    "-g",
                    "-c",
            });
            l.run();


        } else {
            System.err.println("Usage: java -jar Java2Ir.jar " +
                    "/path/to/src/main/java");
        }
    }
}
