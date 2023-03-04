package service;

import container.Container;
import dto.MultipleChoice;
import main.java.dto.Word;
import util.RandomUtil;

import java.util.*;


public class WordService {

    public void addWord(String word, String translate, String description) {
        Word word1 = Container.wordRepository.getWord(word);
        if (word1 == null) {
            Container.wordRepository.addWord(new Word(word, translate, description));
        } else {
            System.out.println("This word already exists");
        }
    }

    public MultipleChoice multipleChoice() {
        List<Word> wordList = new ArrayList<>();
        Integer r = RandomUtil.random(Container.wordRepository.getMaxId());
        Word question = Container.wordRepository.getRandomWord(r);
        wordList.add(question);
        while (wordList.size() < 4) {
            Integer random = RandomUtil.random(Container.wordRepository.getMaxId());
            Word subword = Container.wordRepository.getRandomWord(random);
            if (!wordList.contains(subword)) {
                wordList.add(subword);
            }
        }
        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setAnswer(question);

        Word getA = wordList.get(RandomUtil.random(wordList.size()) - 1);
        multipleChoice.setA(getA);
        wordList.remove(getA);

        Word getB = wordList.get(RandomUtil.random(wordList.size()) - 1);
        multipleChoice.setB(getB);
        wordList.remove(getB);

        Word getC = wordList.get(RandomUtil.random(wordList.size()) - 1);
        multipleChoice.setC(getC);
        wordList.remove(getC);

        Word getD = wordList.get(RandomUtil.random(wordList.size()) - 1);
        multipleChoice.setD(getD);
        wordList.remove(getD);
        return multipleChoice;
    }

    public boolean spelling() {
        Integer r = RandomUtil.random(Container.wordRepository.getMaxId());
        Word spellWord = Container.wordRepository.getRandomWord(r);
        System.out.println(spellWord.getWord());
        String answer = Container.getTranslate();
        if (answer.equals("q") || answer.equals("Q")) {
            System.out.println(Container.correct + "/" + Container.all);
            return false;
        } else {
            if (answer.equals(spellWord.getTranslate())) {
                Container.correct++;
                System.out.println("Correct ");
                System.out.println(spellWord);
            } else {
                System.out.println("Incorrect ");
                System.out.println(spellWord);
            }
        }
        Container.all++;
        return true;


    }

    public boolean checkMChoice(String answer, MultipleChoice multipleChoice) {
        if (answer.equals("Q") || answer.equals("q")) {
            System.out.println(Container.correct + "/" + Container.all);
            return false;
        }
        if (answer.equals("A") || answer.equals("a")) {
            if (multipleChoice.getA().equals(multipleChoice.getAnswer())) {
                System.out.println("Correct");
                Container.correct++;
            } else {
                System.out.println("Incorrect");
            }
        } else if (answer.equals("B") || answer.equals("b")) {
            if (multipleChoice.getB().equals(multipleChoice.getAnswer())) {
                System.out.println("Correct");
                Container.correct++;
            } else {
                System.out.println("Incorrect");
            }
        } else if (answer.equals("C") || answer.equals("c")) {
            if (multipleChoice.getC().equals(multipleChoice.getAnswer())) {
                System.out.println("Correct");

                Container.correct++;
            } else {
                System.out.println("Incorrect");
            }
        } else if (answer.equals("D") || answer.equals("d")) {
            if (multipleChoice.getD().equals(multipleChoice.getAnswer())) {
                System.out.println("Correct");
                Container.correct++;
            } else {
                System.out.println("Incorrect");
            }
        }
        Container.all++;
        return true;
    }

    public void searching(String word) {
        List<Word> wordList = Container.wordRepository.search(word);
        wordList.forEach(System.out::println);

    }
}
