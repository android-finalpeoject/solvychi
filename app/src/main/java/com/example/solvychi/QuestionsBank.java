package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    private static List<QuestionsList> Level1Questions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.maths1);
        final QuestionsList question2 = new QuestionsList("2 3 9", "3 9 2", "9 3 2", "9 2 3", "2 3 9", "", R.drawable.question2);
        final QuestionsList question3 = new QuestionsList("5 2 14", "2 5 14", "5 14 2", "14 2 5", "2 5 14", "", R.drawable.question3);
        final QuestionsList question4 = new QuestionsList("4", "8", "3", "5", "4", "", R.drawable.question4);
        final QuestionsList question5 = new QuestionsList("3", "7", "2", "4", "4", "", R.drawable.question5);
        final QuestionsList question6 = new QuestionsList("23", "2", "3", "75", "3", "", R.drawable.question6);
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        questionsListLists.add(question5);
        questionsListLists.add(question6);
        return questionsListLists;

    }

    ;

    private static List<QuestionsList> Level2Questions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("Animal", "Fire", "Kid", "Bird", "Fire", "", R.drawable.riddle11);
        final QuestionsList question2 = new QuestionsList("Mirror", "Glasse", "Lake", "Water", "Mirror", "", R.drawable.riddle12);
        final QuestionsList question3 = new QuestionsList("Glasse", "Table", "Towel", "Pen", "Towel", "", R.drawable.riddle22);
        final QuestionsList question4 = new QuestionsList("Ears", "Wheel", "Noise", "Eyes", "Eyes", "", R.drawable.riddle44);
        final QuestionsList question5 = new QuestionsList("Egg", "Boxe", "Ball", "Pillow", "Egg", "", R.drawable.riddle55);
        final QuestionsList question6 = new QuestionsList("Bottle", "Water", "Food", "Pit", "Pit", "", R.drawable.riddle66);
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        questionsListLists.add(question5);
        questionsListLists.add(question6);
        return questionsListLists;

    }

    ;

    private static List<QuestionsList> Level3Questions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step1);
        final QuestionsList question2 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step2);
        final QuestionsList question3 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step3);
        final QuestionsList question4 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step4);
        final QuestionsList question5 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step1);
        final QuestionsList question6 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step2);
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        questionsListLists.add(question5);
        questionsListLists.add(question6);
        return questionsListLists;

    }

    ;

    private static List<QuestionsList> Level4Questions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.maths1);
        final QuestionsList question2 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step2);
        final QuestionsList question3 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step3);
        final QuestionsList question4 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step4);
        final QuestionsList question5 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step1);
        final QuestionsList question6 = new QuestionsList("213", "216", "344", "785", "344", "", R.drawable.step2);
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        questionsListLists.add(question5);
        questionsListLists.add(question6);
        return questionsListLists;

    }


    public static List<QuestionsList> getQuestions(String selectedLevelName) {
        switch (selectedLevelName) {
            case "level1":
                return Level1Questions();
            case "level2":
                return Level2Questions();
            case "level3":
                return Level3Questions();
            default:
                return Level4Questions();

        }

    }
}
