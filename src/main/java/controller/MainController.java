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
                case 4 -> {
                    spelling();
                }
                case 5 -> {
                    searching();
                }
                case 6 -> {
                    deleteById();
                }

            }


        }
    }

    private void deleteById() {
        System.out.print("Enter id : ");
        int id = Container.scannerInt.nextInt();
        if (Container.wordRepository.deleteById(id)==1){
            System.out.println("Successfully");
        }else {
            System.out.println("Word not found");
        }
    }

    private void searching() {
        System.out.print("Enter word : ");
        String word = Container.scannerString.nextLine();
        Container.wordService.searching(word);


    }

    private void spelling() {
        if (Container.wordService.spelling()) {
            spelling();
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
        if (Container.wordService.checkMChoice(answer, multipleChoice)) {
            System.out.println(multipleChoice.getAnswer());
            multipleChoice();
        }
        Container.all = 0;
        Container.correct = 0;


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
        System.out.println("___________________\n" +
                " 1. Add Word\n" +
                " 2. WordList List\n" +
                " 3. Multiple Choice\n" +
                " 4. Spelling\n" +
                " 5. Searching\n" +
                " 6. Delete by id");
    }
}
