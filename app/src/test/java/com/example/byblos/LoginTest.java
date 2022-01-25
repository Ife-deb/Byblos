package com.example.byblos;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    Context mMockContext;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testValidateEmailT_admin() {
        Login myObjectUnderTest = new Login(mMockContext);
        Boolean result = myObjectUnderTest.validateEmailT("admin");
        assertThat(result, is(true));
    }
    @Test
    public void testValidateEmailT_employee() {
        Login myObjectUnderTest = new Login(mMockContext);
        Boolean result = myObjectUnderTest.validateEmailT("employee");
        assertThat(result, is(true));
    }

    @Test
    public void testValidateEmailT_patternPass() {
        Login myObjectUnderTest = new Login(mMockContext);
        Boolean result = myObjectUnderTest.validateEmailT("SteveRogers.daddy@gmail.com");
        assertThat(result, is(true));
    }

    @Test
    public void testValidateEmailT_patternFail() {
        Login myObjectUnderTest = new Login(mMockContext);
        Boolean result = myObjectUnderTest.validateEmailT("SteveRogers.atgmailcom");
        assertThat(result, is(false));
    }
}