package controller;

import container.Container;

import java.sql.Connection;

import db.DataBaseInit;
import dto.MultipleChoice;
import main.java.dto.Word;
import repository.WordRepository;

import java.util.List;

public class MainController {
    public void start() {
        boolean b = true;
        DataBaseInit.init();
        while (b) {
            menu();
            switch (Container.getAction()) {
                case 1 -> {
                    addWord();
                }
                case 2 -> {
                    wordList();
                }
                case 3 -> {
                    multipleChoice();
                }
            }


        }
    }

    private void multipleChoice() {
        Integer size = Container.wordRepository.getSize();
        if (size < 4) {
            System.out.println("YET MULTIPLE CHOICE NOT WORK");
            return;
        }
        MultipleChoice multipleChoice = Container.wordService.multipleChoice();
        System.out.println(multipleChoice);
        String answer = Container.getAnswer();
        if (answer.equals("Q") || answer.equals("q")) {
            System.out.println(Container.correct+"/"+Container.all);
            return;
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
        multipleChoice();

    }

    private void wordList() {
        List<Word> wordList = Container.wordRepository.wordList();
        wordList.stream().forEach(System.out::println);
    }

    private void addWord() {
        System.out.print("Enter word : ");
        String word = Container.scannerString.nextLine();
        System.out.print("Enter translate : ");
        String translate = Container.scannerString.nextLine();
        System.out.print("Enter description : ");
        String description = Container.scannerString.nextLine();
        Container.wordService.addWord(word, translate, description);
    }

    private void menu() {
        System.out.println(" 1. Add Word\n" +
                " 2. WordList List\n" +
                " 3. Multiple Choice\n" +
                " 4. Spelling\n" +
                " 5. Searching\n" +
                " 6. Delete by id");
    }
}
