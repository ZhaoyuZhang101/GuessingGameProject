package com.infs.birdhouseapp.JsonClasses;


import com.google.gson.Gson;
import com.infs.birdhouseapp.Application;

import java.io.*;
import java.util.Objects;

public class CommonFunctions {
    public static QuizType readQuiz() throws IOException {
        String url = Application.RESOURCES_PATH+"/QuizJsons/Quiz.json";
        System.out.println("讀取文件");
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(url));
        int len =input.read(line);
        String text =new String(line,0,len);
        Gson gson = new Gson();
        return gson.fromJson(text, QuizType.class);
    }
    public static RankType readRank() throws IOException {
        System.out.println("開始讀取文件");
        String url = Application.RESOURCES_PATH+"/QuizJsons/Rank.json";
        System.out.println(url);
        System.out.println("讀取文件");
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(url));
        int len =input.read(line);
        String text =new String(line,0,len);
        Gson gson = new Gson();
        return gson.fromJson(text, RankType.class);
    }
}