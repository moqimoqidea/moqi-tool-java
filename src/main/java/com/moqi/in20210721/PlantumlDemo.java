package com.moqi.in20210721;

import net.sourceforge.plantuml.Run;

import java.io.IOException;

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
                // "-overwrite",
                "-tsvg",
                "src/main/resources/flowYaml.yaml"});
    }

}
