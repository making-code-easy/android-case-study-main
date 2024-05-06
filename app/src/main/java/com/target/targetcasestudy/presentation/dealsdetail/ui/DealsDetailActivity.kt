package com.target.targetcasestudy.presentation.dealsdetail.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.ActivityDealsDeatilBinding
import com.target.targetcasestudy.presentation.common.utils.toDP
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealsDetailActivity : AppCompatActivity() {

    private val viewBinding: ActivityDealsDeatilBinding by lazy {
        ActivityDealsDeatilBinding.inflate(
            LayoutInflater.from(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        supportActionBar?.setTitle(R.string.deal_detail_page_name)
        viewBinding.toolbar.title = "Details"
        viewBinding.toolbar.setTitleTextColor(Color.BLACK)
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 5.toDP.toFloat()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.deals__details_list_container, DetailPageFragment.newInstance(intent.extras))
            .commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}