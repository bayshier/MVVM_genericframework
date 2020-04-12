package com.app.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.app.R
import com.app.databinding.FragmentMainBinding
import com.example.navigationdemo.adapter.LOGIN_PAGE_INDEX
import com.example.navigationdemo.adapter.MINE_PAGE_INDEX
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment_Nav : Fragment(){

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewPager2 : ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater,container,false)
//        viewPager2 = binding.mainViewPager
//        bottomNavigationView = binding.bottomBtnView
//        bottomNavigationView.setOnNavigationItemSelectedListener(listener)
//        viewPager2.adapter = MainViewPagerAdapter(this)
        return binding.root
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

        when(menuItem.itemId){
            R.id.home -> setCurrentItem(LOGIN_PAGE_INDEX)
            R.id.mine -> setCurrentItem(MINE_PAGE_INDEX)
        }
        true
    }

    private fun setCurrentItem(position : Int) {
        viewPager2.setCurrentItem(position,false)
    }
}