package com.ab.exomind

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ab.exomind.ui.views.listAlbum.adapter.AlbumListAdapter
import org.hamcrest.core.AllOf
import org.hamcrest.core.StringEndsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Aya Boussaadia on 23,February,2021
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class UITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        Espresso.onView(withId(R.id.user_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test1() {
        Espresso.onView(withId(R.id.user_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.ViewHolder>(
                    0,
                    ViewActions.click()
                )


            )
        Espresso.onView(AllOf.allOf(withId(R.id.albumList), ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.albumList)).perform(ViewActions.swipeUp())

        Espresso.onView(withId(R.id.albumList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.ViewHolder>(
                    0,
                    ViewActions.click()
                )


            )
        Espresso.onView(ViewMatchers.withText(StringEndsWith.endsWith("11")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.pressBack()


    }




    @Test
    fun test2() {

        Espresso.onView(withId(R.id.user_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.ViewHolder>(
                    2,
                    ViewActions.click()
                )


            )
        Espresso.onView(AllOf.allOf(withId(R.id.albumList), ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.albumList)).perform(ViewActions.swipeUp())
        Espresso.onView(withId(R.id.albumList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AlbumListAdapter.ViewHolder>(
                    2,
                    ViewActions.click()
                )


            )
        Espresso.onView(ViewMatchers.withText(StringEndsWith.endsWith("mod")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))




    }


}
