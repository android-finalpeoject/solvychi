package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {
    private static List<QuestionsList> LevelQuestions() {
        final List<QuestionsList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final QuestionsList question1 = new QuestionsList("What’s your problem?","Is there a problem?What is the problem?How big is the problem?", R.drawable.step1,R.drawable.bgstep1 );
        final QuestionsList question2 = new QuestionsList("Brainstorm","Come up with solutions and ideas to solve the problem",R.drawable.step2,R.drawable.bgstep2 );
        final QuestionsList question3 = new QuestionsList("Choose","Pick a solution that you think best solves the problem", R.drawable.step3,R.drawable.bgstep3 );
        final QuestionsList question4 = new QuestionsList("Do it !","Try out the agreed upon solution", R.drawable.step4,R.drawable.bgstep4 );
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
