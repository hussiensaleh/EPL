package com.saleh.mol5satak

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    var matches=ArrayList<match>()
    var tableTeams=ArrayList<TableTeam>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@SplashActivity, LuncherActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, 1500
        )

        try {
            loadFromQuery()
            loadFromQuery2()
        }catch (e:Exception){}
        if(matches.size==0) {
            matches()
        }
        else{
            if (tableTeams.size==0){
                this.deleteDatabase("Matches")
                insertToQuery(matches)
            }
        }
        if (tableTeams.size==0){
            teams()
        }
    }

    fun matches(){
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(1,1,GS(R.string.ithd),GS(R.string.marb),0,0,R.drawable.ithd_logo,R.drawable.marb_logo,-1,"الاولي","31/7/2018","18:00"))
        matches.add(match(1,2,GS(R.string.ptrj),GS(R.string.zmlk),0,0,R.drawable.ptrj_logo,R.drawable.zmlk_logo,-1,"الاولي","31/7/2018","21:00"))
        matches.add(match(1,3,GS(R.string.dkhl),GS(R.string.dgla),0,0,R.drawable.dkhl_logo,R.drawable.dgla_logo,-1,"الاولي","1/8/2018" ,"16:00"))
        matches.add(match(1,4,GS(R.string.entg),GS(R.string.hars),0,0,R.drawable.entg_logo,R.drawable.hars_logo,-1,"الاولي","1/8/2018" ,"18:15"))
        matches.add(match(1,5,GS(R.string.ngom),GS(R.string.tlae),0,0,R.drawable.ngom_logo,R.drawable.tlae_logo,-1,"الاولي","1/8/2018" ,"21:00"))
        matches.add(match(1,6,GS(R.string.gona),GS(R.string.msry),0,0,R.drawable.gona_logo,R.drawable.msry_logo,-1,"الاولي","2/8/2018" ,"18:00"))
        matches.add(match(1,7,GS(R.string.ahly),GS(R.string.isml),0,0,R.drawable.ahly_logo,R.drawable.isml_logo,-1,"الاولي","2/8/2018" ,"21:00"))
        matches.add(match(1,8,GS(R.string.mqsa),GS(R.string.smha),0,0,R.drawable.mqsa_logo,R.drawable.smha_logo,-1,"الاولي","3/8/2018" ,"18:00"))
        matches.add(match(1,9,GS(R.string.enpi),GS(R.string.prmd),0,0,R.drawable.enpi_logo,R.drawable.prmd_logo,-1,"الاولي","3/8/2018" ,"21:00"))
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(2,1,GS(R.string.zmlk),GS(R.string.ithd),0,0,R.drawable.zmlk_logo,R.drawable.ithd_logo,-1,"الثانية","4/8/2018","20:00"))
        matches.add(match(2,2,GS(R.string.hars),GS(R.string.dkhl),0,0,R.drawable.hars_logo,R.drawable.dkhl_logo,-1,"الثانية","6/8/2018","16:30"))
        matches.add(match(2,3,GS(R.string.isml),GS(R.string.ptrj),0,0,R.drawable.isml_logo,R.drawable.ptrj_logo,-1,"الثانية","6/8/2018","18:45"))
        matches.add(match(2,4,GS(R.string.dgla),GS(R.string.gona),0,0,R.drawable.dgla_logo,R.drawable.gona_logo,-1,"الثانية","6/8/2018","21:00"))
        matches.add(match(2,5,GS(R.string.msry),GS(R.string.ahly),0,0,R.drawable.msry_logo,R.drawable.ahly_logo,-1,"الثانية","7/8/2018","20:00"))
        matches.add(match(2,6,GS(R.string.prmd),GS(R.string.entg),0,0,R.drawable.prmd_logo,R.drawable.entg_logo,-1,"الثانية","8/8/2018","16:30"))
        matches.add(match(2,7,GS(R.string.smha),GS(R.string.ngom),0,0,R.drawable.smha_logo,R.drawable.ngom_logo,-1,"الثانية","8/8/2018","18:45"))
        matches.add(match(2,8,GS(R.string.marb),GS(R.string.mqsa),0,0,R.drawable.marb_logo,R.drawable.mqsa_logo,-1,"الثانية","8/8/2018","18:45"))
        matches.add(match(2,9,GS(R.string.tlae),GS(R.string.enpi),0,0,R.drawable.tlae_logo,R.drawable.enpi_logo,-1,"الثانية","8/8/2018","21:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(3,1,GS(R.string.gona),GS(R.string.hars),0,0,R.drawable.gona_logo,R.drawable.hars_logo,-1,"الثالثة","14/8/2018","16:00"))
        matches.add(match(3,2,GS(R.string.dkhl),GS(R.string.entg),0,0,R.drawable.dkhl_logo,R.drawable.entg_logo,-1,"الثالثة","14/8/2018","16:00"))
        matches.add(match(3,3,GS(R.string.ptrj),GS(R.string.msry),0,0,R.drawable.ptrj_logo,R.drawable.msry_logo,-1,"الثالثة","14/8/2018","18:15"))
        matches.add(match(3,4,GS(R.string.enpi),GS(R.string.smha),0,0,R.drawable.enpi_logo,R.drawable.smha_logo,-1,"الثالثة","14/8/2018","18:15"))
        matches.add(match(3,5,GS(R.string.tlae),GS(R.string.prmd),0,0,R.drawable.tlae_logo,R.drawable.prmd_logo,-1,"الثالثة","14/8/2018","21:00"))
        matches.add(match(3,6,GS(R.string.ngom),GS(R.string.marb),0,0,R.drawable.ngom_logo,R.drawable.marb_logo,-1,"الثالثة","15/8/2018","20:00"))
        matches.add(match(3,7,GS(R.string.ithd),GS(R.string.isml),0,0,R.drawable.ithd_logo,R.drawable.isml_logo,-1,"الثالثة","16/8/2018","18:00"))
        matches.add(match(3,8,GS(R.string.mqsa),GS(R.string.zmlk),0,0,R.drawable.mqsa_logo,R.drawable.zmlk_logo,-1,"الثالثة","16/8/2018","21:00"))
        matches.add(match(3,9,GS(R.string.ahly),GS(R.string.dgla),0,0,R.drawable.ahly_logo,R.drawable.dgla_logo,-1,"الثالثة","21/8/2018","20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(4,1,GS(R.string.dgla),GS(R.string.ptrj),0,0,R.drawable.dgla_logo,R.drawable.ptrj_logo,-1,"الرابعة","10/8/2018","20:00"))
        matches.add(match(4,2,GS(R.string.marb),GS(R.string.enpi),0,0,R.drawable.marb_logo,R.drawable.enpi_logo,-1,"الرابعة","24/8/2018","16:00"))
        matches.add(match(4,3,GS(R.string.isml),GS(R.string.mqsa),0,0,R.drawable.isml_logo,R.drawable.mqsa_logo,-1,"الرابعة","24/8/2018","18:15"))
        matches.add(match(4,4,GS(R.string.hars),GS(R.string.ahly),0,0,R.drawable.hars_logo,R.drawable.ahly_logo,-1,"الرابعة","24/8/2018","21:00"))
        matches.add(match(4,5,GS(R.string.msry),GS(R.string.ithd),0,0,R.drawable.msry_logo,R.drawable.ithd_logo,-1,"الرابعة","25/8/2018","17:00"))
        matches.add(match(4,6,GS(R.string.smha),GS(R.string.tlae),0,0,R.drawable.smha_logo,R.drawable.tlae_logo,-1,"الرابعة","26/8/2018","16:00"))
        matches.add(match(4,7,GS(R.string.prmd),GS(R.string.dkhl),0,0,R.drawable.prmd_logo,R.drawable.dkhl_logo,-1,"الرابعة","26/8/2018","18:15"))
        matches.add(match(4,8,GS(R.string.entg),GS(R.string.gona),0,0,R.drawable.entg_logo,R.drawable.gona_logo,-1,"الرابعة","26/8/2018","21:00"))
        matches.add(match(4,9,GS(R.string.zmlk),GS(R.string.ngom),0,0,R.drawable.zmlk_logo,R.drawable.ngom_logo,-1,"الرابعة","27/8/2018","20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(5,1,GS(R.string.gona),GS(R.string.dkhl),0,0,R.drawable.gona_logo,R.drawable.dkhl_logo,-1,"الخامسة","31/8/2018","15:30"))
        matches.add(match(5,2,GS(R.string.ptrj),GS(R.string.hars),0,0,R.drawable.ptrj_logo,R.drawable.hars_logo,-1,"الخامسة","31/8/2018","18:00"))
        matches.add(match(5,3,GS(R.string.ngom),GS(R.string.isml),0,0,R.drawable.ngom_logo,R.drawable.isml_logo,-1,"الخامسة","31/8/2018","18:00"))
        matches.add(match(5,4,GS(R.string.tlae),GS(R.string.marb),0,0,R.drawable.tlae_logo,R.drawable.marb_logo,-1,"الخامسة","31/8/2018","21:00"))
        matches.add(match(5,5,GS(R.string.ithd),GS(R.string.dgla),0,0,R.drawable.ithd_logo,R.drawable.dgla_logo,-1,"الخامسة","28/9/2018","17:00"))
        matches.add(match(5,6,GS(R.string.enpi),GS(R.string.zmlk),0,0,R.drawable.enpi_logo,R.drawable.zmlk_logo,-1,"الخامسة","1/9/2018" ,"21:00"))
        matches.add(match(5,7,GS(R.string.smha),GS(R.string.prmd),0,0,R.drawable.smha_logo,R.drawable.prmd_logo,-1,"الخامسة","2/9/2018" ,"18:00"))
        matches.add(match(5,8,GS(R.string.ahly),GS(R.string.entg),0,0,R.drawable.ahly_logo,R.drawable.entg_logo,-1,"الخامسة","2/9/2018" ,"21:00"))
        matches.add(match(5,9,GS(R.string.mqsa),GS(R.string.msry),0,0,R.drawable.mqsa_logo,R.drawable.msry_logo,-1,"الخامسة","2/9/2018" ,"20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(6,1,GS(R.string.msry),GS(R.string.ngom),0,0,R.drawable.msry_logo,R.drawable.ngom_logo,-1,"السادسة","11/9/2018","18:00"))
        matches.add(match(6,2,GS(R.string.dkhl),GS(R.string.ahly),0,0,R.drawable.dkhl_logo,R.drawable.ahly_logo,-1,"السادسة","11/9/2018","21:00"))
        matches.add(match(6,3,GS(R.string.entg),GS(R.string.ptrj),0,0,R.drawable.entg_logo,R.drawable.ptrj_logo,-1,"السادسة","12/9/2018","15:30"))
        matches.add(match(6,4,GS(R.string.hars),GS(R.string.ithd),0,0,R.drawable.hars_logo,R.drawable.ithd_logo,-1,"السادسة","12/9/2018","15:30"))
        matches.add(match(6,5,GS(R.string.marb),GS(R.string.smha),0,0,R.drawable.marb_logo,R.drawable.smha_logo,-1,"السادسة","12/9/2018","18:00"))
        matches.add(match(6,6,GS(R.string.dgla),GS(R.string.mqsa),0,0,R.drawable.dgla_logo,R.drawable.mqsa_logo,-1,"السادسة","12/9/2018","21:00"))
        matches.add(match(6,7,GS(R.string.prmd),GS(R.string.gona),0,0,R.drawable.prmd_logo,R.drawable.gona_logo,-1,"السادسة","13/9/2018","15:30"))
        matches.add(match(6,8,GS(R.string.isml),GS(R.string.enpi),0,0,R.drawable.isml_logo,R.drawable.enpi_logo,-1,"السادسة","13/9/2018","18:00"))
        matches.add(match(6,9,GS(R.string.zmlk),GS(R.string.tlae),0,0,R.drawable.zmlk_logo,R.drawable.tlae_logo,-1,"السادسة","13/9/2018","21:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(7,1,GS(R.string.mqsa),GS(R.string.hars),0,0,R.drawable.mqsa_logo,R.drawable.hars_logo,-1,"السابعة","17/9/2018","15:30"))
        matches.add(match(7,2,GS(R.string.ithd),GS(R.string.entg),0,0,R.drawable.ithd_logo,R.drawable.entg_logo,-1,"السابعة","17/9/2018","18:00"))
        matches.add(match(7,3,GS(R.string.ptrj),GS(R.string.dkhl),0,0,R.drawable.ptrj_logo,R.drawable.dkhl_logo,-1,"السابعة","17/9/2018","18:00"))
        matches.add(match(7,4,GS(R.string.marb),GS(R.string.prmd),0,0,R.drawable.marb_logo,R.drawable.prmd_logo,-1,"السابعة","17/9/2018","21:00"))
        matches.add(match(7,5,GS(R.string.tlae),GS(R.string.isml),0,0,R.drawable.tlae_logo,R.drawable.isml_logo,-1,"السابعة","18/9/2018","15:30"))
        matches.add(match(7,6,GS(R.string.smha),GS(R.string.zmlk),0,0,R.drawable.smha_logo,R.drawable.zmlk_logo,-1,"السابعة","18/9/2018","18:00"))
        matches.add(match(7,7,GS(R.string.ahly),GS(R.string.gona),0,0,R.drawable.ahly_logo,R.drawable.gona_logo,-1,"السابعة","18/9/2018","21:00"))
        matches.add(match(7,8,GS(R.string.enpi),GS(R.string.msry),0,0,R.drawable.enpi_logo,R.drawable.msry_logo,-1,"السابعة","19/9/2018","18:00"))
        matches.add(match(7,9,GS(R.string.ngom),GS(R.string.dgla),0,0,R.drawable.ngom_logo,R.drawable.dgla_logo,-1,"السابعة","19/9/2018","21:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(8,1,GS(R.string.dkhl),GS(R.string.ithd),0,0,R.drawable.dkhl_logo,R.drawable.ithd_logo,-1,"الثامنة","21/9/2018","15:30"))
        matches.add(match(8,2,GS(R.string.entg),GS(R.string.mqsa),0,0,R.drawable.entg_logo,R.drawable.mqsa_logo,-1,"الثامنة","21/9/2018","18:00"))
        matches.add(match(8,3,GS(R.string.ptrj),GS(R.string.gona),0,0,R.drawable.ptrj_logo,R.drawable.gona_logo,-1,"الثامنة","22/9/2018","15:30"))
        matches.add(match(8,4,GS(R.string.isml),GS(R.string.smha),0,0,R.drawable.isml_logo,R.drawable.smha_logo,-1,"الثامنة","22/9/2018","18:00"))
        matches.add(match(8,5,GS(R.string.zmlk),GS(R.string.marb),0,0,R.drawable.zmlk_logo,R.drawable.marb_logo,-1,"الثامنة","22/9/2018","21:00"))
        matches.add(match(8,6,GS(R.string.hars),GS(R.string.ngom),0,0,R.drawable.hars_logo,R.drawable.ngom_logo,-1,"الثامنة","23/9/2018","15:30"))
        matches.add(match(8,7,GS(R.string.dgla),GS(R.string.enpi),0,0,R.drawable.dgla_logo,R.drawable.enpi_logo,-1,"الثامنة","23/9/2018","18:00"))
        matches.add(match(8,8,GS(R.string.prmd),GS(R.string.ahly),0,0,R.drawable.prmd_logo,R.drawable.ahly_logo,-1,"الثامنة","23/9/2018","21:00"))
        matches.add(match(8,9,GS(R.string.msry),GS(R.string.tlae),0,0,R.drawable.msry_logo,R.drawable.tlae_logo,-1,"الثامنة","28/9/2018","19:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(9,1,GS(R.string.ngom),GS(R.string.entg),0,0,R.drawable.ngom_logo,R.drawable.entg_logo,-1,"التاسعة","30/9/2018","18:00"))
        matches.add(match(9,2,GS(R.string.mqsa),GS(R.string.dkhl),0,0,R.drawable.mqsa_logo,R.drawable.dkhl_logo,-1,"التاسعة","30/9/2018","21:00"))
        matches.add(match(9,3,GS(R.string.enpi),GS(R.string.hars),0,0,R.drawable.enpi_logo,R.drawable.hars_logo,-1,"التاسعة","1/10/2018","18:30"))
        matches.add(match(9,4,GS(R.string.marb),GS(R.string.isml),0,0,R.drawable.marb_logo,R.drawable.isml_logo,-1,"التاسعة","1/10/2018","21:00"))
        matches.add(match(9,5,GS(R.string.ithd),GS(R.string.gona),0,0,R.drawable.ithd_logo,R.drawable.gona_logo,-1,"التاسعة","2/10/2018","15:00"))
        matches.add(match(9,6,GS(R.string.ptrj),GS(R.string.ahly),0,0,R.drawable.ptrj_logo,R.drawable.ahly_logo,-1,"التاسعة","2/10/2018","18:00"))
        matches.add(match(9,7,GS(R.string.zmlk),GS(R.string.prmd),0,0,R.drawable.zmlk_logo,R.drawable.prmd_logo,-1,"التاسعة","2/10/2018","21:00"))
        matches.add(match(9,8,GS(R.string.smha),GS(R.string.msry),0,0,R.drawable.smha_logo,R.drawable.msry_logo,-1,"التاسعة","3/10/2018","18:00"))
        matches.add(match(9,9,GS(R.string.tlae),GS(R.string.dgla),0,0,R.drawable.tlae_logo,R.drawable.dgla_logo,-1,"التاسعة","3/10/2018","21:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(10,1,GS(R.string.dkhl),GS(R.string.ngom),0,0,R.drawable.dkhl_logo,R.drawable.ngom_logo,-1,"العاشرة","5/10/2018","15:00"))
        matches.add(match(10,2,GS(R.string.entg),GS(R.string.enpi),0,0,R.drawable.entg_logo,R.drawable.enpi_logo,-1,"العاشرة","5/10/2018","18:00"))
        matches.add(match(10,3,GS(R.string.gona),GS(R.string.mqsa),0,0,R.drawable.gona_logo,R.drawable.mqsa_logo,-1,"العاشرة","6/10/2018","15:00"))
        matches.add(match(10,4,GS(R.string.isml),GS(R.string.zmlk),0,0,R.drawable.isml_logo,R.drawable.zmlk_logo,-1,"العاشرة","6/10/2018","18:00"))
        matches.add(match(10,5,GS(R.string.ahly),GS(R.string.ithd),0,0,R.drawable.ahly_logo,R.drawable.ithd_logo,-1,"العاشرة","6/10/2018","21:00"))
        matches.add(match(10,6,GS(R.string.hars),GS(R.string.tlae),0,0,R.drawable.hars_logo,R.drawable.tlae_logo,-1,"العاشرة","7/10/2018","15:00"))
        matches.add(match(10,7,GS(R.string.prmd),GS(R.string.ptrj),0,0,R.drawable.prmd_logo,R.drawable.ptrj_logo,-1,"العاشرة","7/10/2018","18:00"))
        matches.add(match(10,8,GS(R.string.msry),GS(R.string.marb),0,0,R.drawable.msry_logo,R.drawable.marb_logo,-1,"العاشرة","7/10/2018","18:00"))
        matches.add(match(10,9,GS(R.string.dgla),GS(R.string.smha),0,0,R.drawable.dgla_logo,R.drawable.smha_logo,-1,"العاشرة","7/10/2018","21:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(11,1,GS(R.string.smha),GS(R.string.hars),0,0,R.drawable.smha_logo,R.drawable.hars_logo,-1,"الحادية عشر","18/10/2018","15:00"))
        matches.add(match(11,2,GS(R.string.tlae),GS(R.string.entg),0,0,R.drawable.tlae_logo,R.drawable.entg_logo,-1,"الحادية عشر","18/10/2018","15:00"))
        matches.add(match(11,3,GS(R.string.enpi),GS(R.string.dkhl),0,0,R.drawable.enpi_logo,R.drawable.dkhl_logo,-1,"الحادية عشر","18/10/2018","18:00"))
        matches.add(match(11,4,GS(R.string.marb),GS(R.string.dgla),0,0,R.drawable.marb_logo,R.drawable.dgla_logo,-1,"الحادية عشر","18/10/2018","21:00"))
        matches.add(match(11,5,GS(R.string.ngom),GS(R.string.gona),0,0,R.drawable.ngom_logo,R.drawable.gona_logo,-1,"الحادية عشر","19/10/2018","15:00"))
        matches.add(match(11,6,GS(R.string.isml),GS(R.string.prmd),0,0,R.drawable.isml_logo,R.drawable.prmd_logo,-1,"الحادية عشر","19/10/2018","15:00"))
        matches.add(match(11,7,GS(R.string.mqsa),GS(R.string.ahly),0,0,R.drawable.mqsa_logo,R.drawable.ahly_logo,-1,"الحادية عشر","19/10/2018","18:00"))
        matches.add(match(11,8,GS(R.string.zmlk),GS(R.string.msry),0,0,R.drawable.zmlk_logo,R.drawable.msry_logo,-1,"الحادية عشر","19/10/2018","21:00"))
        matches.add(match(11,9,GS(R.string.ithd),GS(R.string.ptrj),0,0,R.drawable.ithd_logo,R.drawable.ptrj_logo,-1,"الحادية عشر","20/10/2018","19:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(12,1,GS(R.string.hars),GS(R.string.marb),0,0,R.drawable.hars_logo,R.drawable.marb_logo,-1,"الثانية عشر","1/11/2018","15:00"))
        matches.add(match(12,2,GS(R.string.ptrj),GS(R.string.mqsa),0,0,R.drawable.ptrj_logo,R.drawable.mqsa_logo,-1,"الثانية عشر","1/11/2018","17:30"))
        matches.add(match(12,3,GS(R.string.dgla),GS(R.string.zmlk),0,0,R.drawable.dgla_logo,R.drawable.zmlk_logo,-1,"الثانية عشر","1/11/2018","20:30"))
        matches.add(match(12,4,GS(R.string.gona),GS(R.string.enpi),0,0,R.drawable.gona_logo,R.drawable.enpi_logo,-1,"الثانية عشر","2/11/2018","14:45"))
        matches.add(match(12,5,GS(R.string.dkhl),GS(R.string.tlae),0,0,R.drawable.dkhl_logo,R.drawable.tlae_logo,-1,"الثانية عشر","2/11/2018","14:45"))
        matches.add(match(12,6,GS(R.string.entg),GS(R.string.smha),0,0,R.drawable.entg_logo,R.drawable.smha_logo,-1,"الثانية عشر","2/11/2018","17:00"))
        matches.add(match(12,7,GS(R.string.prmd),GS(R.string.ithd),0,0,R.drawable.prmd_logo,R.drawable.ithd_logo,-1,"الثانية عشر","2/11/2018","20:00"))
        matches.add(match(12,8,GS(R.string.msry),GS(R.string.isml),0,0,R.drawable.msry_logo,R.drawable.isml_logo,-1,"الثانية عشر","3/11/2018","17:00"))
        matches.add(match(12,9,GS(R.string.ahly),GS(R.string.ngom),0,0,R.drawable.ahly_logo,R.drawable.ngom_logo,-1,"الثانية عشر","3/11/2018","20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(13,1,GS(R.string.zmlk),GS(R.string.hars),0,0,R.drawable.zmlk_logo,R.drawable.hars_logo,-1,"الثالثة عشر","5/11/2018","19:00"))
        matches.add(match(13,2,GS(R.string.ngom),GS(R.string.ptrj),0,0,R.drawable.ngom_logo,R.drawable.ptrj_logo,-1,"الثالثة عشر","6/11/2018","14:45"))
        matches.add(match(13,3,GS(R.string.smha),GS(R.string.dkhl),0,0,R.drawable.smha_logo,R.drawable.dkhl_logo,-1,"الثالثة عشر","6/11/2018","17:00"))
        matches.add(match(13,4,GS(R.string.tlae),GS(R.string.gona),0,0,R.drawable.tlae_logo,R.drawable.gona_logo,-1,"الثالثة عشر","6/11/2018","17:00"))
        matches.add(match(13,5,GS(R.string.marb),GS(R.string.entg),0,0,R.drawable.marb_logo,R.drawable.entg_logo,-1,"الثالثة عشر","6/11/2018","20:00"))
        matches.add(match(13,6,GS(R.string.isml),GS(R.string.dgla),0,0,R.drawable.isml_logo,R.drawable.dgla_logo,-1,"الثالثة عشر","7/11/2018","14:45"))
        matches.add(match(13,7,GS(R.string.msry),GS(R.string.prmd),0,0,R.drawable.msry_logo,R.drawable.prmd_logo,-1,"الثالثة عشر","7/11/2018","17:00"))
        matches.add(match(13,8,GS(R.string.enpi),GS(R.string.ahly),0,0,R.drawable.enpi_logo,R.drawable.ahly_logo,-1,"الثالثة عشر","7/11/2018","20:00"))
        matches.add(match(13,9,GS(R.string.mqsa),GS(R.string.ithd),0,0,R.drawable.mqsa_logo,R.drawable.ithd_logo,-1,"الثالثة عشر","8/11/2018","19:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(14,1,GS(R.string.gona),GS(R.string.smha),0,0,R.drawable.gona_logo,R.drawable.smha_logo,-1,"الرابعة عشر","10/11/2018","14:45"))
        matches.add(match(14,2,GS(R.string.entg),GS(R.string.zmlk),0,0,R.drawable.entg_logo,R.drawable.zmlk_logo,-1,"الرابعة عشر","10/11/2018","18:00"))
        matches.add(match(14,3,GS(R.string.hars),GS(R.string.isml),0,0,R.drawable.hars_logo,R.drawable.isml_logo,-1,"الرابعة عشر","11/11/2018","14:45"))
        matches.add(match(14,4,GS(R.string.dgla),GS(R.string.msry),0,0,R.drawable.dgla_logo,R.drawable.msry_logo,-1,"الرابعة عشر","11/11/2018","17:00"))
        matches.add(match(14,5,GS(R.string.ptrj),GS(R.string.enpi),0,0,R.drawable.ptrj_logo,R.drawable.enpi_logo,-1,"الرابعة عشر","11/11/2018","17:00"))
        matches.add(match(14,6,GS(R.string.ahly),GS(R.string.tlae),0,0,R.drawable.ahly_logo,R.drawable.tlae_logo,-1,"الرابعة عشر","11/11/2018","20:00"))
        matches.add(match(14,7,GS(R.string.dkhl),GS(R.string.marb),0,0,R.drawable.dkhl_logo,R.drawable.marb_logo,-1,"الرابعة عشر","12/11/2018","14:45"))
        matches.add(match(14,8,GS(R.string.ithd),GS(R.string.ngom),0,0,R.drawable.ithd_logo,R.drawable.ngom_logo,-1,"الرابعة عشر","12/11/2018","17:00"))
        matches.add(match(14,9,GS(R.string.prmd),GS(R.string.mqsa),0,0,R.drawable.prmd_logo,R.drawable.mqsa_logo,-1,"الرابعة عشر","12/11/2018","20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(15,1,GS(R.string.marb),GS(R.string.gona),0,0,R.drawable.marb_logo,R.drawable.gona_logo,-1,"الخامسة عشر","20/11/2018","14:45"))
        matches.add(match(15,2,GS(R.string.tlae),GS(R.string.ptrj),0,0,R.drawable.tlae_logo,R.drawable.ptrj_logo,-1,"الخامسة عشر","20/11/2018","17:00"))
        matches.add(match(15,3,GS(R.string.enpi),GS(R.string.ithd),0,0,R.drawable.enpi_logo,R.drawable.ithd_logo,-1,"الخامسة عشر","20/11/2018","20:00"))
        matches.add(match(15,4,GS(R.string.ngom),GS(R.string.mqsa),0,0,R.drawable.ngom_logo,R.drawable.mqsa_logo,-1,"الخامسة عشر","21/11/2018","14:45"))
        matches.add(match(15,5,GS(R.string.msry),GS(R.string.hars),0,0,R.drawable.msry_logo,R.drawable.hars_logo,-1,"الخامسة عشر","21/11/2018","17:15"))
        matches.add(match(15,6,GS(R.string.isml),GS(R.string.entg),0,0,R.drawable.isml_logo,R.drawable.entg_logo,-1,"الخامسة عشر","21/11/2018","20:00"))
        matches.add(match(15,7,GS(R.string.dgla),GS(R.string.prmd),0,0,R.drawable.dgla_logo,R.drawable.prmd_logo,-1,"الخامسة عشر","21/11/2018","20:00"))
        matches.add(match(15,8,GS(R.string.smha),GS(R.string.ahly),0,0,R.drawable.smha_logo,R.drawable.ahly_logo,-1,"الخامسة عشر","22/11/2018","17:00"))
        matches.add(match(15,9,GS(R.string.zmlk),GS(R.string.dkhl),0,0,R.drawable.zmlk_logo,R.drawable.dkhl_logo,-1,"الخامسة عشر","22/11/2018","20:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\
        matches.add(match(16,1,GS(R.string.ithd),GS(R.string.tlae),0,0,R.drawable.ithd_logo,R.drawable.tlae_logo,-1,"السادسة عشر","24/11/2018","19:00"))
        matches.add(match(16,2,GS(R.string.ngom),GS(R.string.prmd),0,0,R.drawable.ngom_logo,R.drawable.prmd_logo,-1,"السادسة عشر","25/11/2018","14:45"))
        matches.add(match(16,3,GS(R.string.entg),GS(R.string.msry),0,0,R.drawable.entg_logo,R.drawable.msry_logo,-1,"السادسة عشر","25/11/2018","17:00"))
        matches.add(match(16,4,GS(R.string.hars),GS(R.string.dgla),0,0,R.drawable.hars_logo,R.drawable.dgla_logo,-1,"السادسة عشر","25/11/2018","17:00"))
        matches.add(match(16,5,GS(R.string.mqsa),GS(R.string.enpi),0,0,R.drawable.mqsa_logo,R.drawable.enpi_logo,-1,"السادسة عشر","25/11/2018","20:00"))
        matches.add(match(16,6,GS(R.string.gona),GS(R.string.zmlk),0,0,R.drawable.gona_logo,R.drawable.zmlk_logo,-1,"السادسة عشر","26/11/2018","14:45"))
        matches.add(match(16,7,GS(R.string.ptrj),GS(R.string.smha),0,0,R.drawable.ptrj_logo,R.drawable.smha_logo,-1,"السادسة عشر","26/11/2018","17:00"))
        matches.add(match(16,8,GS(R.string.ahly),GS(R.string.marb),0,0,R.drawable.ahly_logo,R.drawable.marb_logo,-1,"السادسة عشر","26/11/2018","20:00"))
        matches.add(match(16,9,GS(R.string.dkhl),GS(R.string.isml),0,0,R.drawable.dkhl_logo,R.drawable.isml_logo,-1,"السادسة عشر","27/11/2018","19:00"))
        //----------------------------------------------------------------------------------------------------------------------------------------------\\

        insertToQuery(matches)
    }

    fun teams(){
        tableTeams.add(TableTeam( 1,GS(R.string.zmlk),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 2,GS(R.string.ahly),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 3,GS(R.string.ptrj),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 4,GS(R.string.ithd),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 5,GS(R.string.mqsa),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 6,GS(R.string.ngom),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 7,GS(R.string.enpi),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 8,GS(R.string.tlae),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam( 9,GS(R.string.smha),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(10,GS(R.string.marb),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(11,GS(R.string.prmd),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(12,GS(R.string.isml),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(13,GS(R.string.msry),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(14,GS(R.string.dgla),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(15,GS(R.string.hars),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(16,GS(R.string.entg),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(17,GS(R.string.dkhl),0,0,0,0,0,0,0,0))
        tableTeams.add(TableTeam(18,GS(R.string.gona),0,0,0,0,0,0,0,0))

        isertToQuery2(tableTeams)
    }

    fun insertToQuery(matchesList:ArrayList<match>){
        var Db = DbManager(this)
        var values = ContentValues()
        for (i in 0 until matchesList.size) {
            var Image1=0
            Image1=when(matchesList[i].team1LogoInt){
                2131165281->R.drawable.ithd_logo                2131165282->R.drawable.marb_logo
                2131165299->R.drawable.ptrj_logo                2131165304->R.drawable.zmlk_logo
                2131165273->R.drawable.dkhl_logo                2131165272->R.drawable.dgla_logo
                2131165275->R.drawable.entg_logo                2131165277->R.drawable.hars_logo
                2131165285->R.drawable.ngom_logo                2131165301->R.drawable.tlae_logo
                2131165276->R.drawable.gona_logo                2131165284->R.drawable.msry_logo
                2131165268->R.drawable.ahly_logo                2131165280->R.drawable.isml_logo
                2131165283->R.drawable.mqsa_logo                2131165300->R.drawable.smha_logo
                2131165274->R.drawable.enpi_logo                2131165298->R.drawable.prmd_logo
                else->R.drawable.dawry_logo
            }
            var Image2=0
            Image2=when(matchesList[i].team2LogoInt) {
                2131165281 -> R.drawable.ithd_logo                2131165282 -> R.drawable.marb_logo
                2131165299 -> R.drawable.ptrj_logo                2131165304 -> R.drawable.zmlk_logo
                2131165273 -> R.drawable.dkhl_logo                2131165272 -> R.drawable.dgla_logo
                2131165275 -> R.drawable.entg_logo                2131165277 -> R.drawable.hars_logo
                2131165285 -> R.drawable.ngom_logo                2131165301 -> R.drawable.tlae_logo
                2131165276 -> R.drawable.gona_logo                2131165284 -> R.drawable.msry_logo
                2131165268 -> R.drawable.ahly_logo                2131165280 -> R.drawable.isml_logo
                2131165283 -> R.drawable.mqsa_logo                2131165300 -> R.drawable.smha_logo
                2131165274 -> R.drawable.enpi_logo                2131165298 -> R.drawable.prmd_logo
                else->R.drawable.dawry_logo
            }
            values.clear()
            values.put("roundId", matchesList[i].round)
            values.put("team1"       ,matchesList[i].team1)
            values.put("team2"       ,matchesList[i].team2)
            values.put("team1Score"  ,matchesList[i].team1Score)
            values.put("team2Score"  ,matchesList[i].team2Score)
            values.put("team1LogoInt",matchesList[i].team1LogoInt)
            values.put("team2LogoInt",matchesList[i].team2LogoInt)
            values.put("winner"      ,matchesList[i].winner)
            values.put("date"        ,matchesList[i].date)
            values.put("time"        ,matchesList[i].time)
            values.put("round"       ,matchesList[i].round)
            values.put("roundId"     ,matchesList[i].roundId)
            values.put("roundMatchId",matchesList[i].roundMatchId)

            Db.insert(values)
        }
    }

    fun isertToQuery2(tableTeams:ArrayList<TableTeam>){
        var Db = DbManager(this)
        var values = ContentValues()
        for (i in 0 until  tableTeams.size){
            values.clear()
            values.put("teamName"         ,tableTeams[i].name)
            values.put("playedMatches",tableTeams[i].playedMatches)
            values.put("win"          ,tableTeams[i].win)
            values.put("lose"         ,tableTeams[i].lose)
            values.put("draw"         ,tableTeams[i].draw)
            values.put("plus"         ,tableTeams[i].plus)
            values.put("minus"        ,tableTeams[i].minus)
            values.put("farq"         ,tableTeams[i].farq)
            values.put("points"       ,tableTeams[i].points)

            Db.insertTable(values)
        }
    }

    fun loadFromQuery( sort: String = "ID") {
        var Db = DbManager(this)
        //val selectionArgs = arrayOf(null)
        val projection = arrayOf("roundId","roundMatchId","team1","team2","team1Score","team2Score","team1LogoInt"
                ,"team2LogoInt","winner","round","date","time")
        var cursor = Db.query(projection, null, null, sort)
        if (cursor.moveToFirst()) {
            do {

                matches.add(match(
                        cursor.getInt(cursor.getColumnIndex("roundId"     )),
                        cursor.getInt(cursor.getColumnIndex("roundMatchId")),
                        cursor.getString(cursor.getColumnIndex("team1"    )),
                        cursor.getString(cursor.getColumnIndex("team2"    )),
                        cursor.getInt(cursor.getColumnIndex("team1Score"  )),
                        cursor.getInt(cursor.getColumnIndex("team2Score"  )),
                        cursor.getInt(cursor.getColumnIndex("team1LogoInt")),
                        cursor.getInt(cursor.getColumnIndex("team2LogoInt")),
                        cursor.getInt(cursor.getColumnIndex("winner"      )),
                        cursor.getString(cursor.getColumnIndex("round"    )),
                        cursor.getString(cursor.getColumnIndex("date"     )),
                        cursor.getString(cursor.getColumnIndex("time"     ))
                ))

            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    fun loadFromQuery2( sort: String = "points") {
        var Db = DbManager(this)
        //val selectionArgs = arrayOf(null)
        val projection = arrayOf("ID"  , "teamName"  , "playedMatches", "win"
                , "lose", "draw"  , "plus"         , "minus"
                , "farq", "points")
        var cursor = Db.queryTable(projection, null, null, sort)
        if (cursor.moveToFirst()) {
            do {
                tableTeams.add(TableTeam(
                        cursor.getInt(cursor.getColumnIndex(   "ID"           )),
                        cursor.getString(cursor.getColumnIndex("teamName"         )),
                        cursor.getInt(cursor.getColumnIndex(   "playedMatches")),
                        cursor.getInt(cursor.getColumnIndex(   "win"          )),
                        cursor.getInt(cursor.getColumnIndex(   "lose"         )),
                        cursor.getInt(cursor.getColumnIndex(   "draw"         )),
                        cursor.getInt(cursor.getColumnIndex(   "plus"         )),
                        cursor.getInt(cursor.getColumnIndex(   "minus"        )),
                        cursor.getInt(cursor.getColumnIndex(   "farq"         )),
                        cursor.getInt(cursor.getColumnIndex(   "points"       ))
                ))



            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    fun GS(resource:Int) = resources.getString(resource)
}
