package com.k22.student.service;

import com.k22.student.model.Score;
import com.k22.student.model.TypeScore;

import java.util.ArrayList;

public interface ScoreServiceInterface {
    ArrayList<Score> getList(int studentId);
    ArrayList<TypeScore> getListTypeScore();
    int saveScore(Score score);
    void saveScoreDetail(int scid, int sid);
    Score findScoreById(int id);
    void updateScore(Score score);

}
