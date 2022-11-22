package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    private static List<QuestionsList> LevelQuestions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("What’s your problem?", R.drawable.step1 );
        final QuestionsList question2 = new QuestionsList("Think,think ,think of some solutions", R.drawable.step2 );
        final QuestionsList question3 = new QuestionsList("What would happen if..?                         Would it be safe?", R.drawable.step3 );
        final QuestionsList question4 = new QuestionsList("Give it a try !", R.drawable.step4 );
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        return questionsListLists;

    }

    ;



    public static List<QuestionsList> getQuestions() {

                return LevelQuestions();


    }
}
