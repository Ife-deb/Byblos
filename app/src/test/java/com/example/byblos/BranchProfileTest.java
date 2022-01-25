package com.example.byblos;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchProfileTest {

    private static final Boolean EXPECTED_TRUE = true;
    private static final Boolean EXPECTED_FALSE = false;

    @Mock
    Context mMockContext;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testValidateAddress_passed() {
        BranchProfile myObjectUnderTest = new BranchProfile(mMockContext);
        Boolean result = myObjectUnderTest.validateAddressT("123 Forever St.");
        assertEquals(result, EXPECTED_TRUE);
    }

    @Test
    public void testValidateAddress_failed() {
        BranchProfile myObjectUnderTest = new BranchProfile(mMockContext);
        Boolean result = myObjectUnderTest.validateAddressT("We 23. @");
        assertEquals(result, EXPECTED_FALSE);
    }

    @Test
    public void testValidatePhoneNumber_passed() {
        BranchProfile myObjectUnderTest = new BranchProfile(mMockContext);
        Boolean result = myObjectUnderTest.validatePhoneNumberPattern("123-456-7855");
        assertEquals(result, EXPECTED_TRUE);
    }

    @Test
    public void testValidatePhoneNumber_failed() {
        BranchProfile myObjectUnderTest = new BranchProfile(mMockContext);
        Boolean result = myObjectUnderTest.validatePhoneNumberPattern("1234567855");
        assertEquals(result, EXPECTED_FALSE);
    }



}