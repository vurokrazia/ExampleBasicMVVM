package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel


class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null

    private var rvCoupons: androidx.recyclerview.widget.RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: com.anncode.offersandcoupons.databinding.ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.setModel(couponViewModel);
        setupListUpdate()
    }

    fun setupListUpdate() {
        //CallCoupons
        couponViewModel?.callCoupons()
        //getCoupons - Lista de cupones
        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            Log.w("Coupon", coupons.get(0).title)
            couponViewModel?.setCouponsInRecyclerViewAdapter(coupons)
        })
    }

}
