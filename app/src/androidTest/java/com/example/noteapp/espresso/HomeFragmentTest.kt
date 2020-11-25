package com.example.noteapp.espresso

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.noteapp.R
import com.example.noteapp.fragments.HomeFragment
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * The HomeFragmentTest.kt, to validate the UI test for home screen/fragment
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class HomeFragmentTest {
    @get:Rule
    var fragmentTestRule = FragmentTestRule(
        HomeFragment::class.java
    )

    @Before
    fun fragmentSetup() {
        // Launch the activity to make the fragment visible
        fragmentTestRule.launchActivity(null)
    }

    @Test
    fun isFabBtnVisible() {
        onView(withId(R.id.faBtnCreate)).check(matches(isDisplayed()))
    }

    @Test
    fun fabBtnClicked() {
        onView(withId(R.id.faBtnCreate)).perform(click())
    }

    @Test
    fun checkRecyclerViewVisibility() {
        onView(withId(R.id.notesRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun btnClicked() {
        onView(withId(R.id.notesRecyclerView)).perform(click())
    }

    @Test
    fun recyclerViewCount(){
        onView(withId(R.id.notesRecyclerView)).check( RecyclerViewItemCountAssertion(5)) //verify the RV item size
    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertFalse(adapter!!.itemCount == expectedCount)
        }
    }
}