package com.raywenderlich.android.emojicalculator


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun splashActivityTest() {
        val textInputEditText = onView(
                allOf(withId(R.id.inputAmount),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText.perform(click())

        val textInputEditText2 = onView(
                allOf(withId(R.id.inputAmount),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()))
        textInputEditText2.perform(replaceText("10"), closeSoftKeyboard())

        val appCompatButton = onView(
                allOf(withId(R.id.buttonBad), withText("\uD83D\uDCA9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatButton.perform(click())

        val textView = onView(
                allOf(withId(R.id.textTip), withText("1.50"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()))
        textView.check(matches(withText("1.50")))

        val textView2 = onView(
                allOf(withId(R.id.textTotal), withText("11.50"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()))
        textView2.check(matches(withText("11.50")))

        val textView3 = onView(
                allOf(withId(R.id.textTotal), withText("11.50"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()))
        textView3.check(matches(withText("11.50")))

        val switch_ = onView(
                allOf(withId(R.id.switchRound), withText("Round up to nearest \uD83D\uDCB5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        switch_.perform(click())

        val appCompatButton2 = onView(
                allOf(withId(R.id.buttonBad), withText("\uD83D\uDCA9"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatButton2.perform(click())

        val textView4 = onView(
                allOf(withId(R.id.textTotal), withText("12.00"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()))
        textView4.check(matches(withText("12.00")))

        val textView5 = onView(
                allOf(withId(R.id.textTotal), withText("12.00"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()))
        textView5.check(matches(withText("12.00")))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
