package com.saleh.mol5satak

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap





class MainActivity : AppCompatActivity() {
    var matches=ArrayList<match>()
    var adapter:matchAdapter?=null
    var Position=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //up Button
        button3.setOnClickListener { this.finish() }
        //retrieve from Fire base
        getfromFirbase()

        roundsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Position=position
                adapter = matchAdapter(this@MainActivity, loadFromQuery("$position"))
                matchesList.adapter = adapter
                matchesList.divider = resources.getDrawable(R.color.white)
                matchesList.dividerHeight = 10

            }
        }

    }
    fun loadFromQuery(roundId:String ):ArrayList<match> {
        val Db = DbManager(this)
        var roundMatches=ArrayList<match>()
        val projectionArg=arrayOf(roundId)
        val cursor = Db.query(null, "roundId Like ?", projectionArg, "roundMatchId")
        if (cursor.moveToFirst()) {
            roundMatches.clear()
            do {
                roundMatches.add(match(
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
        return roundMatches
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
                    adapter = matchAdapter(this@MainActivity, loadFromQuery("$Position"))
                    matchesList.adapter = adapter
                    matchesList.divider = resources.getDrawable(R.color.white)
                    matchesList.dividerHeight = 4
                }
                Toast.makeText(this@MainActivity,"Updated", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

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






}