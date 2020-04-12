package com.example.navigationdemo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.mine.mvvm.view.MineFragment
import com.login.mvvm.view.LoginFragment

const val LOGIN_PAGE_INDEX = 0
const val MINE_PAGE_INDEX = 1

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments: Map<Int, () -> Fragment> = mapOf(
            LOGIN_PAGE_INDEX to { LoginFragment() },
            MINE_PAGE_INDEX to { MineFragment() }
    )

    override fun createFragment(position: Int): Fragment {
        return fragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    override fun getItemCount(): Int = fragments.size

}