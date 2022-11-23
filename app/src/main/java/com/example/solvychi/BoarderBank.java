package com.example.solvychi;

import java.util.ArrayList;
import java.util.List;

public class BoarderBank {
    private static List<BoarderList> LevelQuestions() {
        final List<BoarderList> questionsListLists = new ArrayList<>();
        // create object of QuestionList class and pass a question along with options and answers
        final BoarderList question1 = new BoarderList("What’s your problem?","Is there a problem?What is the problem?How big is the problem?", R.drawable.step1,R.drawable.bgstep1,R.drawable.st1 );
        final BoarderList question2 = new BoarderList("Brainstorm","Come up with solutions and ideas to solve the problem",R.drawable.step2,R.drawable.bgstep2 ,R.drawable.st2);
        final BoarderList question3 = new BoarderList("Choose","Pick a solution that you think best solves the problem", R.drawable.step3,R.drawable.bgstep3 ,R.drawable.st3);
        final BoarderList question4 = new BoarderList("Do it !","Try out the agreed upon solution", R.drawable.step4,R.drawable.bgstep4 ,R.drawable.st4);
        //add all the questions to the ListQuestion
        questionsListLists.add(question1);
        questionsListLists.add(question2);
        questionsListLists.add(question3);
        questionsListLists.add(question4);
        return questionsListLists;

    }

    ;



    public static List<BoarderList> getQuestions() {

                return LevelQuestions();


    }
}
