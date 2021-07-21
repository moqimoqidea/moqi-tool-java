package com.moqi.in20210721;

import net.sourceforge.plantuml.Run;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 练习 Plantuml
 *
 * @author moqi
 * @date 7/21/21 11:28
 */
public class PlantumlDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Run.main(new String[]{
                "-nometadata",
                // "-version",
                // "-verbose",
                // "-language",
                // "-pipe",
                "-tsvg",
                // readFile("src/main/resources/flowYaml.yaml", Charset.defaultCharset())
                "src/main/resources/flowYaml.yaml"
        });
    }

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
