package com.example.noteapp.espresso

import android.view.View
import androidx.core.view.isVisible
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.noteapp.R
import com.example.noteapp.activities.NoteActivity
import com.example.noteapp.extensions.gone
import com.example.noteapp.extensions.visible
import org.junit.Rule
import org.junit.Test

/**
 * The NotesActivityTest.kt, to validate the UI test for Note screen/fragment
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NotesActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(
        NoteActivity::class.java
    )

    @Test
    fun makeDeleteButtonVisible() {
        Espresso.onView(withId(R.id.btnDelete))
            .perform(closeSoftKeyboard())
            .check { view, _ ->
                changeVisibility(view)
            }
    }

    @Test
    fun onClickDeleteButton() {
        Espresso.onView(withId(R.id.btnDelete))
            .perform(closeSoftKeyboard())
            .check { view, _ ->
                changeVisibility(view)
            }
            .perform(click())
    }

    private fun changeVisibility(view: View) {
        if (!view.isVisible)
            view.visible()
        else
            view.gone()
    }

    @Test
    fun saveNoteWithOutImage() {
        saveNote(title = "Meeting", details = "having a meeting tomorrow at 4pm")
    }

    @Test
    fun saveNoteWithImage() {
        saveNote(
            title = "Meeting", details = "having a meeting tomorrow at 4pm",
            image = "https://miro.medium.com/max/854/1*i4K3H70wavICYJc1YHfl0w.png"
        )
    }

    private fun saveNote(title: String, image: String? = null, details: String) {
        Espresso.onView(withId(R.id.etTitle))
            .perform(typeText(title)) //type title
            .perform(closeSoftKeyboard())//close keyboard

        image?.run {
            Espresso.onView(withId(R.id.etImg))
                .perform(typeText(this))//type image
                .perform(closeSoftKeyboard()) //close keyboard
        }

        Espresso.onView(withId(R.id.etDetails))
            .perform(typeText(details)) //type details
            .perform(closeSoftKeyboard()) //type title

        Espresso.onView(withId(R.id.btnSave))
            .perform(click()) //perform click
    }
}