package ru.netology;

import java.util.Random;

import static uitls.Utils.*;

public class Main {
    public static final int TEXTS_SIZE = 100_000;
    public static final int WORD_LENGTH = 3;
    public static final int WORD_LENGTH_ADDING = 3;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[TEXTS_SIZE];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", WORD_LENGTH + random.nextInt(WORD_LENGTH_ADDING));
        }

        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        palindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (isAscendingOrder(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();

        sameChar.join();
        ascendingOrder.join();
        palindrome.join();

        System.out.println("Original words with length of 3 quantity is: " + counter3);
        System.out.println("Original words with length of 4 quantity is: " + counter4);
        System.out.println("Original words with length of 5 quantity is: " + counter5);
    }
}