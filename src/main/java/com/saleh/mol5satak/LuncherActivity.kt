package com.saleh.mol5satak

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_luncher.*
import kotlin.collections.ArrayList

class LuncherActivity : AppCompatActivity() {
    var matches=ArrayList<match>()
    var version=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luncher)
        MobileAds.initialize(this,resources.getString(R.string.banner_ad_app_id))
        val adRequest = AdRequest.Builder().build()
        adView1.loadAd(adRequest)

        dates.setOnClickListener {
            var i=Intent(this,Main3Activity::class.java)
            startActivity(i)
        }
        rounds.setOnClickListener {
            var i=Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        teams.setOnClickListener {
            var i=Intent(this,Main2Activity::class.java)
            startActivity(i)
        }
        table.setOnClickListener{
            var i=Intent(this,Main4Activity::class.java)
            startActivity(i)
        }
    }

    fun animation(Xto:Float,Yto:Float){

        var animation:Animation=TranslateAnimation(150f,Xto,0f,Yto)
        animation.duration=1000
        animation.fillAfter=true
        home0.startAnimation(animation)
    }

    override fun onResume() {
        adView1.resume()
        super.onResume()
    }

    override fun onPause() {
        adView1.pause()
        super.onPause()
    }


}
