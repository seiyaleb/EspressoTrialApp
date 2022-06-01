package com.example.espressotrialapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static kotlin.jvm.internal.Intrinsics.checkNotNull;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void validate_view_display_first() {

        //Droidフラグメント起動
        FragmentScenario.launchInContainer(DroidFragment.class);

        //初回起動で、Viewの表示・非表示を検証
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_display)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.btn_no_display)).check(matches(isDisplayed()));
    }

    @Test
    public void validate_view_display_on_click_btn() {

        //Droidフラグメント起動
        FragmentScenario.launchInContainer(DroidFragment.class);

        //非表示ボタンをタップ
        onView(withId(R.id.btn_no_display)).perform(click());

        //Viewの表示・非表示を検証
        onView(withId(R.id.imageView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.btn_display)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_no_display)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        //表示ボタンをタップ
        onView(withId(R.id.btn_display)).perform(click());

        //Viewの表示・非表示を検証
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_display)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.btn_no_display)).check(matches(isDisplayed()));
    }

    @Test(expected = PerformException.class)
    public void validate_does_not_text_on_scroll() {

        //RecyclerViewフラグメント起動
        FragmentScenario.launchInContainer(RecyclerViewFragment.class);

        //存在しないアイテムをスクロールした場合を検証
        //エラーが出れば正常
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("not in the list"))));
    }

    @Test
    public void validate_text_view_on_click_item() {

        //RecyclerViewフラグメント起動
        FragmentScenario.launchInContainer(RecyclerViewFragment.class);

        //指定したpositionのアイテム内にあるテキストを検証
        onView(withId(R.id.recyclerView)).check(matches(atPosition(1, hasDescendant(withText("フロイドメイウェザー")))));
        onView(withId(R.id.recyclerView)).check(matches(atPosition(3, hasDescendant(withText("フェデラー")))));
    }

    //カスタムMatcher
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) { checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    @Test
    public void validate_navigation_transition() {

        //TestNavHostControllerインスタンス作成
        TestNavHostController navController = new TestNavHostController(ApplicationProvider.getApplicationContext());

        //Droidフラグメント起動
        FragmentScenario<DroidFragment> scenario = FragmentScenario.launchInContainer(DroidFragment.class);

        //TestNavHostControllerをDroidフラグメントに割り当て
        scenario.onFragment(droidFragment -> {
            navController.setGraph(R.navigation.nav_graph);
            Navigation.setViewNavController(droidFragment.requireView(), navController);
        });

        //次へボタンをクリックし、遷移状態を検証
        onView(withId(R.id.btn_next)).perform(click());
        assertEquals(R.id.recyclerViewFragment,navController.getCurrentDestination().getId());
    }
}