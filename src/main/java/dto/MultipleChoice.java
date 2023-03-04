package dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.java.dto.Word;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultipleChoice {
    private Word  answer;
    private Word  A;
    private Word  B;
    private Word  C;
    private Word  D;

    @Override
    public String toString() {
        return answer.getWord()+"  ________\n" +
                "A)"+A.getTranslate()+"\n"+
                "B)"+B.getTranslate()+"\n"+
                "C)"+C.getTranslate()+"\n"+
                "D)"+D.getTranslate()+"\n"+
                "Exit Q";
    }
}
