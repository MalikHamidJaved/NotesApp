package com.example.noteapp.espresso

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.example.noteapp.R
import com.example.noteapp.activities.MainActivity
import org.junit.Assert

/**
 * The FragmentTestRule.kt to isolate the fragment
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class FragmentTestRule<F : Fragment?> constructor(private val fragmentClass: Class<F>) :
    ActivityTestRule<MainActivity>(MainActivity::class.java, true, false) {
    var fragment: F? = null
        private set

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity?.runOnUiThread {
            try {
                //Instantiate and insert the fragment into the container layout
                val manager = activity!!.supportFragmentManager
                val transaction = manager.beginTransaction()
                fragment = fragmentClass.newInstance()
                transaction.replace(R.id.container, fragment!!)
                transaction.commit()
            } catch (e: InstantiationException) {
                Assert.fail(
                    String.format(
                        "%s: Could not insert %s into MainActivity: %s",
                        javaClass.simpleName,
                        fragmentClass.simpleName,
                        e.message
                    )
                )
            } catch (e: IllegalAccessException) {
                Assert.fail(
                    String.format(
                        "%s: Could not insert %s into MainActivity: %s",
                        javaClass.simpleName,
                        fragmentClass.simpleName,
                        e.message
                    )
                )
            }
        }
    }
}