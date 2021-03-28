package com.otex.homamuser.view.restaurantitem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.otex.homamuser.databinding.ActivityRestaurantItemBinding
import com.otex.homamuser.utlitites.Constant
import com.otex.homamuser.utlitites.PrefsUtil
import com.otex.homamuser.view.baseActivity.BaseActivity
import com.otex.homamuser.view.cart.CartActivity
import com.otex.homamuser.view.myorder.MyOrderMapActivity
import com.otex.homamuser.view.restaurantitem.model.Addition
import com.otex.homamuser.view.restaurantitem.model.Option
import com.otex.homamuser.view.restaurantitem.model.Product
import com.otex.homamuser.view.restaurantprofile.RestaurantProfileActivity
import com.otex.homamuser.view.restaurantitem.adapter.AdditionsAdapter
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter
import es.dmoral.toasty.Toasty

class RestaurantItemActivity : BaseActivity() {
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1
    var productId=""
    var pricevalue:Int=0
    var message:String=""
    private var resturantItemViewModel: ResturantItemViewModel? = null
    var listid:ArrayList<Addition>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        click()
    }
    @SuppressLint("SetTextI18n")
    private fun click() {
        binding.quantity.text = (num).toString()
        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.addToCart.setOnClickListener {

             message=binding.editMessage.text.toString()

            if(message==""){
                binding.editMessage.error="Add Message"
            }else{
                make_Order()
            }

        }

        binding.add.setOnClickListener {
            binding.quantity.text = (num++).toString()
            binding.txtPrice.text=(num*pricevalue).toString()+"ج"
        }
        binding.minues.setOnClickListener {
            if (num>1){
                binding.quantity.text = (num--).toString()

            }
            binding.txtPrice.text = (pricevalue*num).toString()+"ج"
        }

    }

    private fun make_Order() {
        PrefsUtil.with(this).add("msg",message).apply()
        val map = HashMap<String, String?>()
        map.put("restId",intent.getStringExtra(Constant.restID)!!)
        map.put("product_id",productId)
        map.put("quantity",binding.quantity.text.toString())
        if (!PrefsUtil.with(this).get(Constant.optionId,"0").equals("0")){
            map.put("options[0]",PrefsUtil.with(this).get(Constant.optionId,"0"))

        }


        for (i in listid?.indices!!){
            map.put("additions[$i]", listid!![i].id)
        }



        resturantItemViewModel?.addToBasket(this,map)
    }

    private fun initialize() {
        PrefsUtil.with(this).add(Constant.additionId,"0").apply()
        PrefsUtil.with(this).add(Constant.optionId,"0").apply()


        resturantItemViewModel = ViewModelProvider(this).get(ResturantItemViewModel::class.java)
        resturantItemViewModel!!.basketlivedata.observe(this) {


            Toasty.success(this, it.message, Toast.LENGTH_SHORT, true).show()
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)



        }


            val list = intent.getParcelableExtra<Product>("menuItemList")
            productId= list?.id.toString()
            binding.foodName.text= list?.name
            binding.description.text=list?.description

            setUpRecyclerSize(list?.options!!)

            setUpRecyclerAdditions(list.additions)


    }

    private fun setUpRecyclerAdditions(additions: List<Addition>) {

        val layoutManager_horizontal = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerAddition.layoutManager = layoutManager_horizontal
        val adapter_addition =
                AdditionsAdapter(this, additions,object : AdditionsAdapter.Clickvaluelistener {
                    override fun click(idlist: ArrayList<Addition>) {
                        listid=idlist
                    }

                })
        binding.recyclerAddition.adapter = adapter_addition

    }

    private fun setUpRecyclerSize(options: List<Option>) {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerSize.layoutManager = layoutManager
        val adapter =
                ChooseSizeAdapter(this,options,object : ChooseSizeAdapter.Clickvaluelistener {
                    @SuppressLint("SetTextI18n")
                    override fun click(price: Int) {
                        pricevalue=price
                        binding.txtPrice.text=price.toString()+"ج"
                    }

                })
        binding.recyclerSize.adapter = adapter
    }




}