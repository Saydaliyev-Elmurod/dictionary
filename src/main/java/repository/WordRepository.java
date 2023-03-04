package repository;

import container.Container;
import db.DataBaseInit;
import main.java.dto.Word;
import util.RandomUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WordRepository {

    public Word getWord(String word) {
        Word word1 = new Word();
        String sql = "SELECT * FROM word " +
                "WHERE word = ?";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        Connection connection = DataBaseInit.getConnection();
        String sql = "INSERT INTO word (word,translate,description)" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, word.getWord());
            statement.setString(2, word.getTranslate());
            statement.setString(3, word.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public List<Word> wordList() {
        String sql = "select * from word ";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            return getWordByRS(p.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public Integer getMaxId() {

        String sql = "SELECT max(id) AS size FROM word ";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet set = p.executeQuery();
            if (set.next()) {
                connection.close();
                return set.getInt("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Word getRandomWord(Integer r) {
        Word word1 = new Word();

        String sql = "SELECT * FROM word " +
                "WHERE id = ?";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, r);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {
                word1.setId(resultSet.getInt("id"));
                word1.setWord(resultSet.getString("word"));
                word1.setTranslate(resultSet.getString("translate"));
                word1.setDescription(resultSet.getString("description"));
                return word1;
            }else {
                return getRandomWord(RandomUtil.random(getMaxId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Word> search(String word) {
        String sql = "SELECT * FROM word " +
                "WHERE word ILIKE '%'||?||'%' OR translate ILIKE '%'||?||'%' " +
                " or description ILIKE '%'||?||'5%' ";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, word);
            p.setString(2, word);
            p.setString(3, word);
            return getWordByRS(p.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public int deleteById(int id) {
        String sql = "delete from word " +
                "where id = " + id;
        Connection connection = DataBaseInit.getConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Integer getSize() {
        String sql = "SELECT count(id) AS size FROM word ";
        Connection connection = DataBaseInit.getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(sql);
            ResultSet set = p.executeQuery();
            if (set.next()) {
                connection.close();
                return set.getInt("size");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
