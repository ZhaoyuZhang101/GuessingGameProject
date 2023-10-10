package com.infs.birdhouseapp.JsonClasses;

import java.util.List;

public class RankType {
    public List<UserType> Rank;
    public RankType(List<UserType> Rank) {
        this.Rank = Rank;
    }

    public List<UserType> getRank() {
        return Rank;
    }

    public void setRank(List<UserType> rank) {
        Rank = rank;
    }
    public void addRank(UserType user) {
        Rank.add(user);
    }

    public static class UserType {
        public String name;
        public int Score;
        public String ID;

        public UserType (String name, int Scores, String ID) {
            this.name = name;
            this.Score = Scores;
            this.ID = ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return Score;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getID() {
            return ID;
        }

        public void setScore(int score) {
            Score = score;
        }
    }
}
