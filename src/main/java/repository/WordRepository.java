package repository;

import db.DataBaseInit;
import main.java.dto.Word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WordRepository {

    public Word getWord(String word) {
        Word word1 = new Word();
        String sql = "SELECT * FROM word " +
                "WHERE word = ?";
        try {
            PreparedStatement p = DataBaseInit.getConnection().prepareStatement(sql);
            p.setString(1, word);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {
                word1.setId(resultSet.getInt("id"));
                word1.setWord(resultSet.getString("word"));
                word1.setTranslate(resultSet.getString("translate"));
                word1.setDescription(resultSet.getString("description"));
                return word1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private List<Word> getWordByRS(ResultSet resultSet) {
        List<Word> wordList = new LinkedList<>();

        try {
            while (resultSet.next()) {
                Word word = new Word();
                word.setId(resultSet.getInt("id"));
                word.setWord(resultSet.getString("word"));
                word.setTranslate(resultSet.getString("translate"));
                word.setDescription(resultSet.getString("description"));
                wordList.add(word);
            }
            return wordList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    public void addWord(Word word) {
        try {
            String sql = "INSERT INTO word (word,translate,description)" +
                    "VALUES (?,?,?)";
            PreparedStatement statement = DataBaseInit.getConnection().prepareStatement(sql);
            statement.setString(1, word.getWord());
            statement.setString(2, word.getTranslate());
            statement.setString(3, word.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Word> wordList() {
        String sql = "select * from word ";
        try {
            PreparedStatement p = DataBaseInit.getConnection().prepareStatement(sql);
            return getWordByRS(p.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Integer getSize() {

        String sql = "SELECT COUNT(*) AS size FROM word ";
        try {
            PreparedStatement p = DataBaseInit.getConnection().prepareStatement(sql);
            ResultSet set = p.executeQuery();
            if (set.next()) {
                return set.getInt("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Word getRandomWord(Integer r) {
        Word word1 = new Word();
        String sql = "SELECT * FROM word " +
                "WHERE id = ?";
        try {
            PreparedStatement p = DataBaseInit.getConnection().prepareStatement(sql);
            p.setInt(1, r);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {
                word1.setId(resultSet.getInt("id"));
                word1.setWord(resultSet.getString("word"));
                word1.setTranslate(resultSet.getString("translate"));
                word1.setDescription(resultSet.getString("description"));
                return word1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
