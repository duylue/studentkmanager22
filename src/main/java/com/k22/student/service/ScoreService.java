package com.k22.student.service;

import com.k22.student.config.JDBCCon;
import com.k22.student.model.Score;
import com.k22.student.model.TypeScore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreService {

    public static ArrayList<Score> getList(int studentId) {
        String query = "";
        query += "select st.sid,st.sname, s.scid, s.score, sj.sj_name," +
                " t.type_name\n" +
                "from scores s,\n" +
                "  subjects sj,\n" +
                " type_score t,students st, score_details sd  \n" +
                "where s.sj_id = sj.sjid\n" +
                " and s.type_id = t.type_id and st.sid = sd.sid " +
                " and sd.scid = s.scid ";
        if (studentId > 0) {
            query += " and st.sid = " + studentId;
        }
        query += " order by s.scid";

        try {
            PreparedStatement statement = JDBCCon.connection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Score> list = new ArrayList<>();
            while (resultSet.next()) {
                Score score = new Score();
                int id = resultSet.getInt("scid");
                int sid = resultSet.getInt("sid");
                score.setScid(id);
                float diem = resultSet.getFloat("score");
                score.setScore(diem);
                String tName = resultSet.getString("type_name");
                String sname = resultSet.getString("sname");
                String sjName = resultSet.getString("sj_name");
                score.setTypeName(tName);
                score.setSjName(sjName);
                score.setSName(sname);
                score.setStudentId(sid);
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

    public static int saveScore(Score score) {

        try {
            int id = (int) Math.floor(((Math.random() * 899999) + 100000));
            PreparedStatement preparedStatement = JDBCCon.connection().prepareStatement("insert into scores\n" +
                    "values (?,?,?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setFloat(2, score.getScore());
            preparedStatement.setInt(3, score.getTypeId());
            preparedStatement.setInt(4, score.getSjId());
            preparedStatement.execute();
            return id;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveScoreDetail(int scid, int sid) {
        try {
            PreparedStatement preparedStatement = JDBCCon.connection().prepareStatement("insert into score_details(sid, scid) \n" +
                    "values (?,?)");
            preparedStatement.setInt(1, sid);
            preparedStatement.setInt(2, scid);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Score findScoreById(int id) {
        try {
            PreparedStatement statement = JDBCCon.connection().prepareStatement("SELECT * from scores where scid = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Score score = new Score();
            while (resultSet.next()) {
                float sc = resultSet.getFloat("score");
                int tId = resultSet.getInt("type_id");
                int sjId = resultSet.getInt("sj_id");
                score.setSjId(sjId);
                score.setTypeId(tId);
                score.setScid(id);
                score.setScore(sc);
            }
            return score;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateScore(Score score) {
        try {
            PreparedStatement statement = JDBCCon.connection().
                    prepareStatement("update scores set score = ?, type_id = ?, sj_id =? where scid = ?");
            statement.setFloat(1, score.getScore());
            statement.setInt(2, score.getTypeId());
            statement.setInt(3, score.getSjId());
            statement.setInt(4, score.getScid());
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
