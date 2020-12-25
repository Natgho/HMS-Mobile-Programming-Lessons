package yu.yufragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find viewPager
        myPager = findViewById(R.id.myPager);

        //FragmentManager myFragmentManager = getSupportFragmentManager();
        //MyPagerAdapter myPagerAdapter = new MyPagerAdapter(myFragmentManager);

        //Set Adapter
        myPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // Add Listener
        myPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Toast.makeText(this, "onPageScrolled is called with position: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageSelected(int position) {
        //Toast.makeText(this, "onPageSelected is called with position: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //Toast.makeText(this, "onPageScrollStateChanged is called with position: " + state, Toast.LENGTH_LONG).show();
    }
}