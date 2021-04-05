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
import com.otex.homamuser.view.restaurantitem.adapter.AdditionsAdapter
import com.otex.homamuser.view.restaurantitem.model.Addition
import com.otex.homamuser.view.restaurantitem.model.Option
import com.otex.homamuser.view.restaurantitem.model.Product
import com.softray_solutions.newschoolproject.ui.activities.chart.adapter.ChooseSizeAdapter
import es.dmoral.toasty.Toasty

class RestaurantItemActivity : BaseActivity() {
    lateinit var binding: ActivityRestaurantItemBinding
    var num:Int=1
    var productId=""
    var message:String=""
    var priceAddition:Int=0
    var priceOption:Int=0
    private var resturantItemViewModel: ResturantItemViewModel? = null
    var listid:ArrayList<Addition>?=ArrayList()

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


                make_Order()

        }

        binding.add.setOnClickListener {
            num += 1
            binding.quantity.text = (num).toString()
            val total=(priceOption+priceAddition)*num
            binding.txtPriceValue.text=total.toString()

        }
        binding.minues.setOnClickListener {
            if (num>1){
                num -= 1
                binding.quantity.text = (num).toString()
                val total=(priceOption+priceAddition)*num
                binding.txtPriceValue.text=total.toString()
            }
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
            map.put("additions[$i]", listid!![i].id.toString())
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


        if(list?.options!!.isNotEmpty()){
            priceOption=list?.options!![0].price
            binding.txtPriceValue.text= priceOption.toString()
        }
            setUpRecyclerSize(list.options)

            setUpRecyclerAdditions(list.additions)


    }

    private fun setUpRecyclerAdditions(additions: List<Addition>) {

        val layoutManager_horizontal = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerAddition.layoutManager = layoutManager_horizontal
        val adapter_addition =
                AdditionsAdapter(this, additions,object : AdditionsAdapter.Clickvaluelistener {
                    override fun click(idlist: ArrayList<Addition>) {
                        priceAddition=0
                        listid=idlist
                        if (listid!=null&& listid!!.size>0) {
                            for (i in 0 until listid!!.size) {
                                priceAddition += listid!![i].price
                            }
                        }else{
                            priceAddition=0
                        }


                        val total=(priceOption+priceAddition)*num
                        binding.txtPriceValue.text=total.toString()
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
                        priceOption=price
                        val total=(priceOption+priceAddition)*num
                        binding.txtPriceValue.text=total.toString()
                    }

                })
        binding.recyclerSize.adapter = adapter
    }



}