package quizapp.core;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserTest{
    private User user = new User();
    
    private void setUser(){
        //Using private method instead of @BeforeAll to avoid static method

        user.setUsername("Hallvard");
        user.setPassword("Trætteberg");
        user.addQuiz("quiz1", 1);
        user.addQuiz("quiz2", 0);
    }
    @Test
    public void correctUsernameAndPassword(){
        //Tests the getter methods

        setUser();
        assertEquals(user.getUsername(), "Hallvard");
        assertEquals(user.getPassword(), "Trætteberg");
    }
    
    @Test
    public void correctMeanScore(){
        //Checks User.meanScore() method

        setUser();
        assertEquals(user.meanScore(), 0.5);
    }

    @Test 
    public void hasTakenQuiz(){
        //Checks if User.quizTaken() works properly

        setUser();
        assertTrue(user.quizTaken("quiz1"));
    }

    @Test
    public void hasNotTakenQuiz(){
        //Checks if User.quizTaken() works properly

        setUser();
        assertFalse(user.quizTaken("quiz3"));
    }

    @Test
    public void getCorrectScore(){
        //Checks if User.getScore() works properly

        setUser();
        assertEquals(1.0, user.getScore("quiz1"));
    }
}