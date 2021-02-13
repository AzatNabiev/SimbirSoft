package ru.itis.javalab.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.ApplicationConfig;
import ru.itis.javalab.models.Word;
import ru.itis.javalab.services.ParsingService;
import ru.itis.javalab.services.TextService;

import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        TextService textService;
        ParsingService parsingService;
        Scanner scanner= new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        textService = context.getBean("textServiceImpl", TextService.class);
        parsingService=context.getBean("parsingServiceImpl",ParsingService.class);

        System.out.println("Введите ссылку:");
        String url=scanner.next();

        StringTokenizer tokenizer=new StringTokenizer(parsingService.getPageText(url), " \"[]()\t\n\r,.?!;:");
        while (tokenizer.hasMoreTokens()){
            textService.saveWord(Word.builder()
                        .value(tokenizer.nextToken().toLowerCase())
                        .build());
        }
        System.out.println("Статистика:");
        System.out.println("-----------------------");
        List<Word> words=textService.getAllWords();
        for (Word word: words){
            System.out.println(word.getValue()+" - "+word.getCount());
        }
        System.out.println("-----------------------");
    }
}
