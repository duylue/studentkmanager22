package com.k22.student.service;

import com.k22.student.config.JDBCCon;
import com.k22.student.model.Score;
import com.k22.student.model.TypeScore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreService {

    public static ArrayList<Score> getList() {
        try {
            PreparedStatement statement = JDBCCon.connection().prepareStatement("select s.scid, s.score, sj.sj_name," +
                    " t.type_name\n" +
                    "from scores s,\n" +
                    "     subjects sj,\n" +
                    "     type_score t\n" +
                    "where s.sj_id = sj.sjid\n" +
                    "  and s.type_id = t.type_id");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Score> list = new ArrayList<>();
            while (resultSet.next()) {
                Score score = new Score();
                int id = resultSet.getInt("scid");
                score.setScid(id);
                float diem = resultSet.getFloat("score");
                score.setScore(diem);
                String tName = resultSet.getString("type_name");
                String sjName = resultSet.getString("sj_name");
                score.setTypeName(tName);
                score.setSjName(sjName);
                list.add(score);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<TypeScore> getListTypeScore() {
        try {
            PreparedStatement statement = JDBCCon.connection().prepareStatement("select * from type_score");

            ResultSet resultSet = statement.executeQuery();
            ArrayList<TypeScore> list = new ArrayList<>();
            while (resultSet.next()) {
                TypeScore typeScore = new TypeScore();
                int id = resultSet.getInt("type_id");
                typeScore.setTid(id);
                String tName = resultSet.getString("type_name");
                typeScore.setTName(tName);
                list.add(typeScore);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
