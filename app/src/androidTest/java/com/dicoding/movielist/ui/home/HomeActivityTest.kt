package com.dicoding.movielist.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.movielist.R
import com.dicoding.movielist.utils.DataDummy
import com.dicoding.movielist.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withText("Movies")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_film)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_film)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size-1))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withText("Movies")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_film)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_title)).check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        Espresso.onView(withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_date)).check(ViewAssertions.matches(withText(dummyMovie[0].releaseYear.toString())))
        Espresso.onView(withId(R.id.text_genrefilm)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_genrefilm)).check(ViewAssertions.matches(withText(dummyMovie[0].genre)))
        Espresso.onView(withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_description)).check(ViewAssertions.matches(withText(dummyMovie[0].overview)))
        Espresso.onView(withId(R.id.text_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_rating)).check(ViewAssertions.matches(withText("rating: " + dummyMovie[0].imdbScore.toString() + "%")))
        Espresso.onView(withId(R.id.text_duration)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_duration)).check(ViewAssertions.matches(withText("duration: " + dummyMovie[0].duration.toString() + " minutes")))
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(withText("TV Shows")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size-1))
    }

    @Test
    fun loadDetailTvShows() {
        Espresso.onView(withText("TV Shows")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_title)).check(ViewAssertions.matches(withText(dummyTvShow[0].title)))
        Espresso.onView(withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_date)).check(ViewAssertions.matches(withText(dummyTvShow[0].releaseYear.toString())))
        Espresso.onView(withId(R.id.text_genrefilm)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_genrefilm)).check(ViewAssertions.matches(withText(dummyTvShow[0].genre)))
        Espresso.onView(withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_description)).check(ViewAssertions.matches(withText(dummyTvShow[0].overview)))
        Espresso.onView(withId(R.id.text_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_rating)).check(ViewAssertions.matches(withText("rating: " + dummyTvShow[0].imdbScore.toString() + "%")))
        Espresso.onView(withId(R.id.text_duration)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.text_duration)).check(ViewAssertions.matches(withText("duration: " + dummyTvShow[0].duration.toString() + " minutes")))
    }
}