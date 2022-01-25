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

public class SignUpTest{
    @Mock
    Context mMockContext;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void passwordsMatch_passed() {
        SignUp myObjectUnderTest = new SignUp(mMockContext);
        Boolean result = myObjectUnderTest.passwordsMatch("test");
        assertThat(result, is(true));
    }

    @Test
    public void passwordsMatch_failed() {
        SignUp myObjectUnderTest = new SignUp(mMockContext);
        Boolean result = myObjectUnderTest.passwordsMatch("notTest");
        assertThat(result, is(false));
    }

}