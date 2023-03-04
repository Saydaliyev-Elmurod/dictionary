package util;

import java.util.Random;

public class RandomUtil {
    public static Integer random(Integer size){
        Random random = new Random();
       return random.nextInt(size)+1;
    }
    public static Integer answer(){
        Random random = new Random();
        return random.nextInt(4);
    }
}
