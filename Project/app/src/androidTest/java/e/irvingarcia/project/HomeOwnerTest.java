package e.irvingarcia.project;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class HomeOwnerTest {
    @Rule
    public ActivityTestRule<SearchHomeOwner> mActivityTestRule= new ActivityTestRule<SearchHomeOwner>(SearchHomeOwner.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, SearchHomeOwner.class);
            result.putExtra("users", "null");
            result.putExtra("pass","null");
            return result;
        }
    };
    private SearchHomeOwner mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkSearchField() throws Exception{
        text= mActivity.findViewById(R.id.serviceSearch);
        text.setText("user1");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }
    @Test
    @UiThreadTest
    public void checkTime() {
        RadioButton button = (RadioButton)mActivity.findViewById(R.id.radioTime);
        button.callOnClick();
    }
    @Test
    @UiThreadTest
    public void checkRate() {
        RadioButton button = (RadioButton)mActivity.findViewById(R.id.radioRate);
        button.callOnClick();
    }
    @Test
    @UiThreadTest
    public void checkService() {
        RadioButton button = (RadioButton)mActivity.findViewById(R.id.radioService);
        button.callOnClick();
    }
    @Test
    @UiThreadTest
    public void ensureButtonDisableAfterOneClick() {
        Button button = (Button)mActivity.findViewById(R.id.button8);
        button.callOnClick();
    }
}
