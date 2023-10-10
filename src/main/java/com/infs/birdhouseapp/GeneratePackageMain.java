package com.infs.birdhouseapp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class GeneratePackageMain {
    public static String PACKAGE_NAME = "BirdHouseGuessingGame";
    public static String LEARN_VIDEOS_PATH = "assets/LearnVideos/";
    public static String QUIZ_JSON_PATH = "assets/QuizJsons/";
    public static String EXAM_VIDEOS_PATH = "assets/ExamVideos/";

    public static List<String> requirePaths = Arrays.asList(LEARN_VIDEOS_PATH, QUIZ_JSON_PATH, EXAM_VIDEOS_PATH);
    public static void main(String[] args) throws IOException {
        unPackResources();
    }
    public static void unPackResources() throws IOException {
        String targetPath =  "src/../target/"+ PACKAGE_NAME+"/res";
        File targetFolder = new File(targetPath);
        if (!targetFolder.exists() && !targetFolder.isDirectory()) {
            targetFolder.mkdirs();
            System.out.println("首次運行軟件,正在創建相關配置文件");
            System.out.println("文件創建完成");
        }
        for (String fromPath: requirePaths) {
            File fp = new File(fromPath);
            File[] files = fp.listFiles();
            System.out.println("列出文件表");
            File tp = new File(targetPath+"/"+fp.getName());
            if (!tp.exists() && !tp.isDirectory()) {
                tp.mkdirs();
                }
            assert files != null;
            for (File f : files) {
                FileCopy(fromPath + f.getName(), targetPath + "/" + fp.getName() + "/" + f.getName());
                System.out.println(f.getName());
            }
        }

    }

    public static void FileCopy(String path1, String path2) throws IOException {
        System.out.println("複製開始");
        FileInputStream fis = new FileInputStream(path1);
        FileOutputStream fos = new FileOutputStream(path2);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] buf = new byte[1024*40];
            int len;
            while ((len = bis.read(buf))>0) {
                bos.write(buf,0,len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis.close();
            fos.close();
        }
    }


}
