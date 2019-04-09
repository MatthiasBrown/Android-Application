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

public class HomeOwnertTest2 {
    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule= new ActivityTestRule<Welcome>(Welcome.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, Welcome.class);
            result.putExtra("users", "Test");
            result.putExtra("pass","Test");
            return result;
        }
    };
    private Welcome mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView27));
    }
    @Test
    @UiThreadTest
    public void checkAddress() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView28));
    }
    @Test
    @UiThreadTest
    public void checkService() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView29));
    }
    @Test
    @UiThreadTest
    public void checkRole() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView30));
    }
    @Test
    @UiThreadTest
    public void ensureButtonDisableAfterOneClick() {
        Button button = (Button)mActivity.findViewById(R.id.button6);
        button.callOnClick();
    }
}
