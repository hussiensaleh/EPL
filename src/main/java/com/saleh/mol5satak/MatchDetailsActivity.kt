package com.saleh.mol5satak

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_match_details.*
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.goals_ticket.view.*
import java.util.*


class MatchDetailsActivity : YouTubeBaseActivity() {
    var match:match?=null
    var roundId=""
    var roundMatchId=""
    var goalsURl:String=""
    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)
        MobileAds.initialize(this,resources.getString(R.string.banner_ad_app_id))
        val adRequest = AdRequest.Builder().build()
        adView2.loadAd(adRequest)
        adView3.loadAd(adRequest)
        var i=intent
        roundId=i.getIntExtra("roundId",0).toString()
        roundMatchId=i.getIntExtra("roundMatchId",0).toString()

        loadFromQuery(roundId,roundMatchId)
        Interface()
        getGoals(roundId, roundMatchId)

        home4.setOnClickListener { this.finish() }
    }

    fun playVideo(videoId: String, youTubePlayerView: YouTubePlayerView ) {
        //initialize youtube player view
        youTubePlayerView.initialize(resources.getString(R.string.Api),
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(provider: YouTubePlayer.Provider,youTubePlayer: YouTubePlayer, b: Boolean) {
                            youTubePlayer.cueVideo(videoId)


                    }

                    override fun onInitializationFailure(provider: YouTubePlayer.Provider,
                                                         youTubeInitializationResult: YouTubeInitializationResult) {

                    }
                })
    }

    fun loadFromQuery( roundId: String,roundMatchId:String,sort: String = "roundMatchId") {
        var Db = DbManager(this)
        val selectionArgs = arrayOf(roundId,roundMatchId)
        val projection = arrayOf("roundId","roundMatchId","team1","team2","team1Score","team2Score","team1LogoInt"
                ,"team2LogoInt","winner","round","date","time")
        var cursor = Db.query(projection, "roundId =? and roundMatchId =? ", selectionArgs, sort)
        if (cursor.moveToFirst()) {
            do {
                match=match(
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
                )

            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    fun Interface(){
        d_team1_tv.text=match!!.team1
        d_team2_tv.text=match!!.team2
        d_score1_tv.text=match!!.team1Score.toString()
        d_score2_tv.text=match!!.team2Score.toString()
        d_logo1.setBackgroundResource(match!!.team1LogoInt)
        d_logo2.setBackgroundResource(match!!.team2LogoInt)
        d_time_tv.text=match!!.time
        d_date_tv.text=match!!.date
        d_status_tv.text=when(match!!.winner){
            (-1)->"لم تبدأ بعد " // had not start yet
            0->"   الان   "//is playing
            1->"  انتهت  "//team1 win
            2->"  انتهت  "//team2 win
            3->"  انتهت  "//draw
            else->{ "لم تبدأ بعد "}
        }
    }

    fun getGoals(roundId: String,roundMatchId: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("goals").child(roundId).child(roundMatchId)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as String
                goalsURl=value
                playVideo(goalsURl,youTubePlayer)
            }
        })
    }

    inner class GoalsAdapter:BaseAdapter {


        var context: Context
        var goals=ArrayList<String>()
        constructor(context:Context,goals:ArrayList<String>){
            this.context=context
            this.goals=goals

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView =layoutInflater.inflate(R.layout.goals_ticket,null)
            myView.textView5.text= "الاهداف"
            myView.setOnClickListener {playVideo(goals[position],youTubePlayer)}

            return myView
        }

        override fun getItem(position: Int): Any {
            return goals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
        return goals.size
        }

    }

}
