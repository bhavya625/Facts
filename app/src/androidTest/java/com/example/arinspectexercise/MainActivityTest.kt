package com.example.arinspectexercise

import android.arch.lifecycle.MutableLiveData
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.model.network.Resource
import com.example.arinspectexercise.viewmodel.FactsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*

import org.mockito.Mockito.`when`
import org.hamcrest.CoreMatchers.not
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    private var viewModel = mock(FactsViewModel::class.java)
    private val factsData = MutableLiveData<Resource<Facts>>()

    @Before
    fun init() {
        `when`(viewModel.getFacts()).thenReturn(factsData)
        (activityRule.activity as MainActivity).setViewModelForTesting(viewModel)
    }

    @Test
    fun success() {
        factsData.postValue(Resource.success(null))
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.error_message)).check(matches(not(isDisplayed())))
        assert(activityRule.activity.title == "About Canada")
    }

    @Test
    fun error() {
        val error = "Unable to resolve host \"dl.dropboxusercontent.com\": No address associated with hostname"
        factsData.postValue(Resource.error(error, null))
        onView(withId(R.id.error_message)).check(matches(isDisplayed()))
        onView(withId(R.id.error_message)).check(matches(withText(error)))
    }
}