package com.infs.birdhouseapp.JsonClasses;

import java.util.List;

public class QuizType {
    public List<QuestionType> Quiz;

    public QuizType (List<QuestionType> Quiz) {
        this.Quiz = Quiz;
    }

    public List<QuestionType> getQuiz() {
        return Quiz;
    }

    public void setQuiz(List<QuestionType> quiz) {
        Quiz = quiz;
    }

    public class QuestionType {
        public String Name;
        public String Question;
        public String Video;
        public String[] Answer;
        public String CorrectAnswer;

        public QuestionType(String Name, String Question, String Video, String[] Answer, String CorrectAnswer) {
            this.Name = Name;
            this.Question = Question;
            this.Video = Video;
            this.Answer = Answer;
            this.CorrectAnswer = CorrectAnswer;

        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getQuestion() {
            return Question;
        }

        public void setQuestion(String question) {
            Question = question;
        }

        public String getCorrectAnswer() {
            return CorrectAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            CorrectAnswer = correctAnswer;
        }

        public void setAnswer(String[] answer) {
            Answer = answer;
        }

        public void setVideo(String video) {
            Video = video;
        }

        public String getVideo() {
            return Video;
        }

        public String[] getAnswer() {
            return Answer;
        }
    }

}
