package com.example.calculadora;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by William_ST on 05/03/19.
 */
@RunWith(JUnitParamsRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
/*
    @Test
    @Parameters(method = "getValidOperandButtonData")
    public void onClickButtonShouldAddExpectedValueToOperationsViews(int buttonId, String expectedValue) {
        //Clic en el botón 1
        onView(withId(buttonId)).perform(click());

        //Comprueba si la pantalla de operaciones muestra el 1
        onView(withId(R.id.operations)).check(matches(withText(expectedValue)));

    }

    private static Object[] getValidOperandButtonData() {
        return new Object[]{
                new Object[]{R.id.bt1, "1"},
                new Object[]{R.id.bt2, "2"},
                new Object[]{R.id.bt3, "3"},
                new Object[]{R.id.bt4, "4"},
                new Object[]{R.id.bt5, "5"},
                new Object[]{R.id.bt6, "6"},
                new Object[]{R.id.bt7, "7"},
                new Object[]{R.id.bt8, "8"},
                new Object[]{R.id.bt9, "9"},
                new Object[]{R.id.bt0, "0"},
                new Object[]{R.id.bt00, "00"},
                new Object[]{R.id.bt_addition, " + "},
                new Object[]{R.id.bt_subtraction, " - "},
                new Object[]{R.id.bt_multiplication, " x "},
                new Object[]{R.id.bt_division, " / "},
                new Object[]{R.id.bt_exponentiation, " ^ "},
                new Object[]{R.id.bt_factorial, " fact ("},
                new Object[]{R.id.bt_square_root, " sqrt ("},
                new Object[]{R.id.bt_dot, "."},
                new Object[]{R.id.bt_parenthesis_start, " ("},
                new Object[]{R.id.bt_parenthesis_end, ") "}
        };
    }

    @Test
    @Parameters(method = "getValidOperationsData")
    public void onOperationsViewChangedShouldUpdateResultsView(String operations, String result) {
        onView(withId(R.id.operations)).perform(setText(operations));
        onView(allOf(withParent(withId(R.id.result)), isCompletelyDisplayed())).check(matches(withText(result)));
    }

    private static Object[] getValidOperationsData() {
        return new Object[]{
                new Object[]{"2+2", "4"},
                new Object[]{"sqrt(9)", "3"},
                new Object[]{"5/2", "2.5"},
                new Object[]{"(4/4)", "1"},
                new Object[]{"100-90", "10"},
                new Object[]{"10x20", "200"},
                new Object[]{"8^2", "64"},
                new Object[]{"9+2", "11"}
        };
    }

    @Test
    @Parameters(method = "getValidOperationsRemoveLastButtonData")
    public void onOperationsViewShouldRemoveLastButtonView(String operations, String result) {
        onView(withId(R.id.operations)).perform(setText(operations));
        onView(withId(R.id.bt_remove_last)).perform(click());
        onView(withId(R.id.operations)).check(matches(withText(result)));
    }

    private static Object[] getValidOperationsRemoveLastButtonData() {
        return new Object[]{
                new Object[]{"5/2", "5 / "},
                new Object[]{"(4/4)", " (4 / 4"},
                new Object[]{"100-90", "100 - 9"},
                new Object[]{"10x20", "10 x 2"},
                new Object[]{"8^2", "8 ^ "},
                new Object[]{"9+2", "9 + "}
        };
    }

    */

    @Test
    @Parameters(method = "getValidOperationsClearButtonData")
    public void onOperationsViewShouldRemoveLastButtonView(String operations, String result) {
        onView(withId(R.id.operations)).perform(setText(operations));
        onView(withId(R.id.bt_clear)).perform(click());
        onView(allOf(withParent(withId(R.id.result)), isCompletelyDisplayed())).check(matches(withText(result)));
    }

    private static Object[] getValidOperationsClearButtonData() {
        return new Object[]{
                new Object[]{"5/2", ""},
                new Object[]{"(4/4)", ""},
                new Object[]{"100-90", ""},
                new Object[]{"10x20", ""},
                new Object[]{"8^2", ""},
                new Object[]{"9+2", ""}
        };
    }



    public static ViewAction setText(final String value) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };


    }

}
