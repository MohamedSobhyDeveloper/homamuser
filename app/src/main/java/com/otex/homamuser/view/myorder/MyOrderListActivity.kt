package com.otex.homamuser.view.myorder

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityMyOrderListBinding
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.myorder.adapter.MyOrderListAdapter
import java.util.HashMap

class MyOrderListActivity : BaseActivity() {
    private lateinit var binding : ActivityMyOrderListBinding

    private var myOrderViewModel : MyOrderViewModel? = null
    private var loading = false
    private var nextPage = ""
    var adapter: MyOrderListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        getmyOrder()
        click()
    }
    private fun click() {
        binding.backbtn.setOnClickListener {
            finish()
        }
    }
    private fun getmyOrder() {

      myOrderViewModel?.myOrderList(this,null)

    }
    private fun initialize() {

        myOrderViewModel = ViewModelProvider(this).get(MyOrderViewModel::class.java)

        binding.nestedScroll!!.viewTreeObserver.addOnScrollChangedListener {
            val view = binding.nestedScroll!!.getChildAt(binding.nestedScroll!!.childCount - 1)
            val diff = view.bottom - (binding.nestedScroll!!.height + binding.nestedScroll!!
                    .getScrollY())
            if (loading) {
                if (diff == 0) {
                    setupPagination()
                    loading = false
                }
            }
        }
        myOrderViewModel!!.myOrderListViewModel.observe(this) {

            if(it.next_page_url!=null && it.next_page_url.isNotEmpty() ){

                loading=true
                nextPage=it.next_page_url

            }
            val layoutManager = LinearLayoutManager(this)
            binding.recOrder.layoutManager = layoutManager
             adapter =
                    MyOrderListAdapter(this,it.data)
            binding.recOrder.adapter = adapter

        }

        myOrderViewModel!!.urlOrderListViewModel.observe(this) {
            adapter?.addList(it.data)

            if(it.next_page_url!=null && it.next_page_url .isNotEmpty() ){

                loading=true
                nextPage=it.next_page_url

            }
        }
    }

    private fun setupPagination() {
        val map = HashMap<String, String?>()
        map.put("url",nextPage)
        myOrderViewModel?.UrlOrderList(this,map)

    }
}