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
        Integer r = RandomUtil.random(Container.wordRepository.getSize());
        Word question = Container.wordRepository.getRandomWord(r);
        wordList.add(question);
        while (wordList.size() < 4) {
            Integer random = RandomUtil.random(Container.wordRepository.getSize());
            Word subword = Container.wordRepository.getRandomWord(random);
            if (!wordList.contains(subword)) {
                wordList.add(subword);
            }
        }
        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setAnswer(question);

        Word getA = wordList.get(RandomUtil.random(wordList.size())-1);
        multipleChoice.setA(getA);
        wordList.remove(getA);

        Word getB = wordList.get(RandomUtil.random(wordList.size())-1);
        multipleChoice.setB(getB);
        wordList.remove(getB);

        Word getC = wordList.get(RandomUtil.random(wordList.size())-1);
        multipleChoice.setC(getC);
        wordList.remove(getC);

        Word getD = wordList.get(RandomUtil.random(wordList.size())-1);
        multipleChoice.setD(getD);
        wordList.remove(getD);
        return multipleChoice;
    }
}
