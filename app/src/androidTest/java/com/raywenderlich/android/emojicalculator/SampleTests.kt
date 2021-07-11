package com.raywenderlich.android.emojicalculator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SampleTests {
    @Test
    fun simpleTest(){
        Espresso.onView(AllOf.allOf(withId(R.id.textTip), ViewMatchers.withText("Hello!"))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(AllOf.allOf(withId(R.id.textTip), Matchers.not(ViewMatchers.withText("Unwanted"))))
    }

    @Test
    fun simpleOnDataTest(){
        Espresso.openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext<Context>())
        //Espresso.onView(withId(R.id.spinner)).perform(ViewActions.click())
        Espresso.onData(AllOf.allOf(Matchers.`is`(IsInstanceOf.instanceOf(String::class.java)),
                Matchers.`is`("Americano"))).perform(ViewActions.click())
//        Espresso.onView(withId(R.id.spinner_text))
//                .check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("Americano"))))
    }

    @Test
    fun tricks(){
        Espresso.onView(withId(R.id.textTip))
                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.textTip))
                .check(ViewAssertions.doesNotExist())
    }
}