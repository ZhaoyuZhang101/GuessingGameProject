package com.infs.birdhouseapp.JsonClasses;


import com.google.gson.Gson;
import com.infs.birdhouseapp.Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CommonFunctions {
    public static QuizType readQuiz() throws IOException {
        String url = Objects.requireNonNull(Application.class.getResource("QuizJsons/Quiz.json")).getPath();
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(url));
        int len =input.read(line);
        String text =new String(line,0,len);
        Gson gson = new Gson();
        return gson.fromJson(text, QuizType.class);
    }
    public static RankType readRank() throws IOException {
        String url = Objects.requireNonNull(Application.class.getResource("QuizJsons/Rank.json")).getPath();
        System.out.println(url);
        char[] line = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(url));
        int len =input.read(line);
        String text =new String(line,0,len);
        Gson gson = new Gson();
        return gson.fromJson(text, RankType.class);
    }
}