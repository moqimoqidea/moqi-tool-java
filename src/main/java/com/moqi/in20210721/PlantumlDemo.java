package com.moqi.in20210721;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 练习 Plantuml
 *
 * @author moqi
 * @date 7/21/21 11:28
 */
@Slf4j
public class PlantumlDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        String source = "@startuml\n";
        source += "Bob -> Alice : hello\n";
        source += "@enduml\n";

        SourceStringReader reader = new SourceStringReader(source);
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        // Write the first image to "os"
        String desc = reader.outputImage(os, new FileFormatOption(FileFormat.SVG)).getDescription();
        log.info("desc:{}", desc);
        os.close();

        // The XML is stored into svg
        final String svg = new String(os.toByteArray(), StandardCharsets.UTF_8);
        log.info(svg);
    }

}
