package com.arnawajuan.rumah_makan_cilik;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MenuTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void menuTest() {
        onView(isRoot()).perform(waitFor(5000));
        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.inputEmail),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("kurohaku12@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.inputPassword),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("kill12"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnLogin), withText("LOGIN"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3)));
        appCompatButton.perform(scrollTo(), click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.cardMenu),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        linearLayout.perform(scrollTo(), click());
        onView(isRoot()).perform(waitFor(1000));
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.FoodRecyclerView),
                        childAtPosition(
                                withId(R.id.swipeRefresh),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(waitFor(1000));
        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.update_foodname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_foodname_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("burger king"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.update_foodname), withText("burger king"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_foodname_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.update_price),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_price_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText(""));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.update_price),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_price_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnU_update), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.update_price),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_price_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("15000"), closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.update_desc),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_desc_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("Makanan kaya rasa"));

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.update_desc),

                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.update_desc_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btnU_update), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.FoodRecyclerView),
                        childAtPosition(
                                withId(R.id.swipeRefresh),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnU_delete), withText("delete"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());
        onView(isRoot()).perform(waitFor(1500));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.btnA_food),
                        childAtPosition(
                                allOf(withId(R.id.mainContainer),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        floatingActionButton.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnA_food2), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.add_foodname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_foodname_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(click());

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.add_foodname),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_foodname_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("Pizza Goreng"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btnA_food2), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton6.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction textInputEditText12 = onView(
                allOf(withId(R.id.add_price),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_price_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText12.perform(replaceText("50000"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btnA_food2), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton7.perform(click());
        onView(isRoot()).perform(waitFor(1000));

        ViewInteraction textInputEditText13 = onView(
                allOf(withId(R.id.add_desc),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.add_desc_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText13.perform(replaceText("Pizza Joss"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btnA_food2), withText("save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());
        onView(isRoot()).perform(waitFor(1000));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }

            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }
}
