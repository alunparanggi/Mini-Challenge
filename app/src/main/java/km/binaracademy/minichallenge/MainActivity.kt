package km.binaracademy.minichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import km.binaracademy.minichallenge.databinding.ActivityMainBinding
import km.binaracademy.minichallenge.ui.location.LocationFragment
import km.binaracademy.minichallenge.ui.main.ViewPagerAdapter
import km.binaracademy.minichallenge.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPagerAdapter.addFragment(ProfileFragment(),"PROFILE")
        viewPagerAdapter.addFragment(LocationFragment(),"CURRENT LOCATION")

        binding.vpTask.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tlTask,binding.vpTask,true){ tab,pos->
            tab.text = viewPagerAdapter.getPageTitle(pos)
        }.attach()
    }


}