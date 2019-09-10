package com.neomatrix.appbank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void validateUserLogin() {

        String user = "teste";
        String password = "Test@1";

        if (user.isEmpty()) {

            Assert.assertFalse(user.isEmpty());
        }

        if (password.isEmpty()) {

            Assert.assertFalse(true);
        }
        if (password.length() < 6) {

            Assert.assertTrue(true);

        }

    }



}

