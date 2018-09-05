package com.saleh.mol5satak

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main4.*
import java.util.*
import kotlin.collections.ArrayList

class Main4Activity : AppCompatActivity() {
    var tableTeams=ArrayList<TableTeam>()
    var adapter:tableAdapter?=null
    var table=ArrayList<TableTeam>()
    var matches=ArrayList<match>()
    var version=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        //Ad initialize
        MobileAds.initialize(this,resources.getString(R.string.banner_ad_app_id))
        val adRequest = AdRequest.Builder().build()
        adView4.loadAd(adRequest)
        getfromFirbase()
        tableTeams=teams()
        for(i in 0 until tableTeams.size) {
            loadFromQuery(i,tableTeams[i].name,"team1")
            loadFromQuery(i,tableTeams[i].name,"team2")
        }
        updateQuery(tableTeams)

        adapter=tableAdapter(this,loadTableFromQuery())
        table_listView.adapter=adapter
        home4.setOnClickListener { this.finish() }
    }

    fun teams():ArrayList<TableTeam>{
        var Teams =ArrayList<TableTeam>()
        Teams.add(TableTeam( 1,GS(R.string.zmlk),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 2,GS(R.string.ahly),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 3,GS(R.string.ptrj),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 4,GS(R.string.ithd),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 5,GS(R.string.mqsa),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 6,GS(R.string.ngom),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 7,GS(R.string.enpi),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 8,GS(R.string.tlae),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam( 9,GS(R.string.smha),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(10,GS(R.string.marb),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(11,GS(R.string.prmd),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(12,GS(R.string.isml),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(13,GS(R.string.msry),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(14,GS(R.string.dgla),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(15,GS(R.string.hars),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(16,GS(R.string.entg),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(17,GS(R.string.dkhl),0,0,0,0,0,0,0,0))
        Teams.add(TableTeam(18,GS(R.string.gona),0,0,0,0,0,0,0,0))
        return Teams
    }

    fun loadFromQuery(index:Int, team:String,selection: String = "team1") {
        var Db = DbManager(this)
        val selectionArgs = arrayOf(team)
        val projection = arrayOf( "team1Score", "team2Score", "winner")
        var cursor = Db.query(projection, "$selection =?", selectionArgs, "ID")
        if (cursor.moveToFirst()) {
            do {
                val winner=cursor.getInt(cursor.getColumnIndex("winner"))
                if(winner !=-1&&winner !=0){
                    var selectedTeam=tableTeams[index]
                    if(selection=="team1") {
                        when (winner) {
                            1 -> { selectedTeam.win += 1; selectedTeam.points+=3 }
                            2 -> { selectedTeam.lose += 1}
                            3 -> { selectedTeam.draw += 1; selectedTeam.points+=1}
                        }
                        selectedTeam.plus +=cursor.getInt(cursor.getColumnIndex("team1Score"))
                        selectedTeam.minus+=cursor.getInt(cursor.getColumnIndex("team2Score"))
                    }
                    else{
                        when (winner) {
                            1 -> { selectedTeam.lose += 1}
                            2 -> { selectedTeam.win += 1; selectedTeam.points+=3}
                            3 -> { selectedTeam.draw += 1; selectedTeam.points+=1}
                        }
                        selectedTeam.plus+=cursor.getInt(cursor.getColumnIndex("team2Score"))
                        selectedTeam.minus+=cursor.getInt(cursor.getColumnIndex("team1Score"))
                    }
                    selectedTeam.playedMatches+=1
                    selectedTeam.farq=selectedTeam.plus-selectedTeam.minus
                }

            } while (cursor.moveToNext())
            cursor.close()
        }

    }

    fun updateQuery(tableTeams: ArrayList<TableTeam>){
        var db=DbManager(this)
        var values=ContentValues()
        for (i in 0 until tableTeams.size) {
            values.clear()
            values.put("teamName",tableTeams[i].name)
            values.put("playedMatches",tableTeams[i].playedMatches)
            values.put("win",tableTeams[i].win)
            values.put("lose",tableTeams[i].lose)
            values.put("draw",tableTeams[i].draw)
            values.put("plus",tableTeams[i].plus)
            values.put("minus",tableTeams[i].minus)
            values.put("farq",tableTeams[i].farq)
            values.put("points",tableTeams[i].points)
            val selectionargs = arrayOf(tableTeams[i].name)
            db.UpdateTable(values, "teamName = ?", selectionargs)
        }
    }

    fun loadTableFromQuery():ArrayList<TableTeam>{
        var table=ArrayList<TableTeam>()
        var Db = DbManager(this)
        //val selectionArgs = arrayOf(null)
        var cursor = Db.queryTable(null, null, null, "points DESC,farq DESC ")
        table.clear()
        if (cursor.moveToFirst()) {
            do {
                table.add(TableTeam(
                        cursor.getInt(cursor.getColumnIndex(   "ID"           )),
                        cursor.getString(cursor.getColumnIndex("teamName"     )),
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
        return table
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
            values.put("team1"       ,matchesList[i].team1)
            values.put("team2"       ,matchesList[i].team2)
            values.put("team1Score"  ,matchesList[i].team1Score)
            values.put("team2Score"  ,matchesList[i].team2Score)
            values.put("team1LogoInt",Image1)
            values.put("team2LogoInt",Image2)
            values.put("winner"      ,matchesList[i].winner)
            values.put("date"        ,matchesList[i].date)
            values.put("time"        ,matchesList[i].time)
            values.put("round"       ,matchesList[i].round)
            values.put("roundId"     ,matchesList[i].roundId)
            values.put("roundMatchId",matchesList[i].roundMatchId)

            Db.insert(values)
        }
    }

    fun deleteall(){
        var Db = DbManager(this)
        Db.deleteall()
    }

    fun getfromFirbase(){
        // Read from the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("matches")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as ArrayList<HashMap<String,String>>
                if(value.size>0) {
                    matches.clear()
                    for (i in 0 until value.size) {
                        matches.add(match(
                                value[i]["roundId"].toString().toInt(),
                                value[i]["roundMatchId"].toString().toInt(),
                                value[i]["team1"].toString(),
                                value[i]["team2"].toString(),
                                value[i]["team1Score"].toString().toInt(),
                                value[i]["team2Score"].toString().toInt(),
                                value[i]["team1LogoInt"].toString().toInt(),
                                value[i]["team2LogoInt"].toString().toInt(),
                                value[i]["winner"].toString().toInt(),
                                value[i]["round"].toString(),
                                value[i]["date"].toString(),
                                value[i]["time"].toString()
                        ))
                    }
                    deleteall()
                    insertToQuery(matches)
                    tableTeams=teams()
                    for(i in 0 until tableTeams.size) {
                        loadFromQuery(i,tableTeams[i].name,"team1")
                        loadFromQuery(i,tableTeams[i].name,"team2")
                    }
                    updateQuery(tableTeams)
                    adapter=tableAdapter(this@Main4Activity,loadTableFromQuery())
                    table_listView.adapter=adapter
                }
                Toast.makeText(this@Main4Activity,"Updated", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

    }

    fun GS(resource:Int) = resources.getString(resource)


}
