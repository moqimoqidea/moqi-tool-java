package com.moqi.before20200530.encode;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author moqi
 * On 2/25/20 12:58
 */
@Slf4j
public class Gbk2312ToUtf8 {

    private static final String DIR_PATH = "/Users/moqi/Downloads/ArtConcurrentBook/src/";
    private static final String GBK = "gbk";


    public static void main(String[] args) throws IOException {
        transferAllJavaFileGbk2Utf8(DIR_PATH);
    }

    /**
     * 将某个 Java 项目下所有 src 文件从 GBK 格式转换为 UTF-8
     * 需要放在未被 iCloud 同步的目录，否则会因为 iCloud 的同步导致旧文件恢复且命名加 2
     */
    private static void transferAllJavaFileGbk2Utf8(String dirPath) throws IOException {
        List<String> filePathList = getFilePathList(dirPath);
        for (final String filePath : filePathList) {
            transfer(filePath, filePath + "-utf8");
        }


        List<String> newFilePathList = getFilePathList(dirPath);

        for (final String filePath : newFilePathList) {
            if (filePath.endsWith("java")) {
                File file1 = new File(filePath);
                boolean delete = file1.delete();
                log.info("删除文件 {} 是否成功: {}", filePath, delete);
            }
        }

        List<String> finalFilePathList = getFilePathList(dirPath);

        for (final String filePath : finalFilePathList) {
            if (filePath.endsWith("-utf8")) {
                File file = new File(filePath);

                String newFilePath = filePath.substring(0, filePath.length() - 5);
                File file2 = new File(newFilePath);

                if (file2.exists()) {
                    log.error("文件 {} 已存在", newFilePath);
                }

                boolean success = file.renameTo(file2);

                log.info("重命名文件 {} 到 {} 是否成功: {}", filePath, newFilePath, success);
            }
        }


    }

    /**
     * 将 gbk 文件转换为 utf-8 文件
     *
     * @param sourcePath gbk 文件
     * @param targetPath utf-8 文件
     * @throws IOException IO 异常
     */
    private static void transfer(final String sourcePath, final String targetPath) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(sourcePath), GBK);
        OutputStreamWriter isw = new OutputStreamWriter(new FileOutputStream(targetPath), StandardCharsets.UTF_8);

        int len;
        while ((len = isr.read()) != -1) {
            isw.write(len);
        }

        isw.close();
        isr.close();
    }

    /**
     * 递归获取指定目录下所有的文件路径
     *
     * @param path 路劲
     * @return 所有的文件路径的 List
     */
    private static List<String> getFilePathList(String path) {
        File file = new File(path);
        File[] array = file.listFiles();

        if (array == null) {
            return Collections.emptyList();
        }

        List<String> filePathList = new ArrayList<>();

        for (final File value : array) {
            if (value.isFile()) {
                filePathList.add(value.getPath());
            } else if (value.isDirectory()) {
                List<String> subFilePathList = getFilePathList(value.getPath());
                filePathList.addAll(subFilePathList);
            }
        }

        return filePathList;
    }


}
