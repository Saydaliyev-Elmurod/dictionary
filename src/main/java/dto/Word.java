package main.java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    private Integer id;
    private String word;
    private String translate;
    private String description;

    public Word(String word, String translate, String description) {
        this.word = word;
        this.translate = translate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(id, word1.id) && Objects.equals(word, word1.word) && Objects.equals(translate, word1.translate) && Objects.equals(description, word1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translate, description);
    }

    @Override
    public String toString() {
        return word + " => " + translate + " <<>>" + description;
    }
}
