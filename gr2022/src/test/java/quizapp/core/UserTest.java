package quizapp.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import quizapp.json.JSONHandler;

public class UserTest{
    private User user = new User();
    
    private void setUser(){
        user.setUsername("Hallvard");
        user.setPassword("Trætteberg");
        user.addQuiz("quiz1", 1);
        user.addQuiz("quiz2", 0);
    }
    @Test
    public void correctUsernameAndPassword(){
        setUser();
        assertEquals(user.getUsername(), "Hallvard");
        assertEquals(user.getPassword(), "Trætteberg");
    }
    
    @Test
    public void correctMeanScore(){
        setUser();
        assertEquals(user.meanScore(), 0.5);
    }

    @Test 
    public void hasTakenQuiz(){
        setUser();
        assertTrue(user.quizTaken("quiz1"));
    }

    @Test
    public void hasNotTakenQuiz(){
        setUser();
        assertFalse(user.quizTaken("quiz3"));
    }
}