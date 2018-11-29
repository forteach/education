package com.forteach.education.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/13 22:07
 * @Version: 1.0
 * @Description: 操作文件工具类
 */
public class FileUtils {

    public static void writeStringToFile(File file, String ppthtml, String s) throws IOException {
        FileWriter fw = null;
        if (!file.exists()){
            file.createNewFile();
        }
        fw = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(s, 0, s.length()-1);
        out.close();
    }

    public static String getFileNameWithoutExtension(String filePath) {
        String fName = filePath.trim();
        return fName.substring(fName.lastIndexOf("\\")+1);
    }

    public static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");

        if (index == -1) {
            return null;
        }
        return fileName.substring(index + 1);
    }

    /**
     * 根据文件名获取文件类型
     * @param filename
     * @return
     */
    public static String ext(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        String result = filename.substring(index + 1);
        return result;
    }
}
