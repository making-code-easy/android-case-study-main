package com.target.targetcasestudy.presentation.dealslist.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.ActivityDealsListBinding
import com.target.targetcasestudy.presentation.common.utils.toDP
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsListActivity : AppCompatActivity() {

    private val viewBinding: ActivityDealsListBinding by lazy {
        ActivityDealsListBinding.inflate(
            LayoutInflater.from(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.toolbar.title = "List"
        viewBinding.toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.elevation = 5.toDP.toFloat()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.deals_list_container, DealListFragment.newInstance())
            .commit()


    }
}