package e.irvingarcia.project;

import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class MainActivityTest {

    @Rule
    public ActivityTestRule<ServiceCreation> mActivityTestRule= new ActivityTestRule<ServiceCreation>(ServiceCreation.class);
    private ServiceCreation mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkServiceName() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textAdd));
        text= mActivity.findViewById(R.id.editAddService);
        text.setText("user1");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }
    @Test
    @UiThreadTest
    public void checkHour() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textAdd));
        text= mActivity.findViewById(R.id.editAddHour);
        text.setText("15.1");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }
    @Test
    @UiThreadTest
    public void checkEditService() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textAdd));
        text= mActivity.findViewById(R.id.editService);
        text.setText("user");
        String name= text.getText().toString();
        assertNotEquals("user1",name);
    }
    @Test
    @UiThreadTest
    public void checkDeleteService() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textAdd));
        text= mActivity.findViewById(R.id.editDeleteService);
        text.setText("user");
        String name= text.getText().toString();
        assertNotEquals("user1",name);
    }
    @Test
    @UiThreadTest
    public void checkEditServiceName() throws Exception{
        //assertNotNull(mActivity.findViewById(R.id.textAdd));
        text= mActivity.findViewById(R.id.editDeleteService);
        text.setText("user");
        String name= text.getText().toString();
        assertNotEquals("user1",name);
    }

}