package com.saleh.mol5satak

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.spinner_ticket.view.*
import java.util.*
import kotlin.collections.ArrayList

class Main3Activity : AppCompatActivity() {

    var matches=ArrayList<match>()
    var adapter:matchAdapter?=null
    var date =""
    var click=0
    var todaydate=""
    var list=ArrayList<String>()
    var selectionPosition=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val calendar = Calendar.getInstance(TimeZone.getDefault())
        // get today date
        todaydate = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)+1}/${calendar.get(Calendar.YEAR)}"
        //add all dates in a list
        list.addAll(resources.getStringArray(R.array.calender))

        DaysSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                day_listView.visibility = View.GONE
                nomatches_tv.visibility=View.VISIBLE
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                date=list[position]
                selectionPosition=position
                loadFromQuery(date)
            }
        }
        //set the spinner to today
        selectionPosition=list.indexOf(todaydate)
        DaysSpinner.setSelection(selectionPosition)

        //next Day Button
        nextButton.setOnClickListener {
            if(selectionPosition==list.size-2){
                selectionPosition += 1
                DaysSpinner.setSelection(selectionPosition)
                nextButton.visibility=View.INVISIBLE
            }else{
                prevButton.visibility=View.VISIBLE
                selectionPosition += 1
                DaysSpinner.setSelection(selectionPosition)
        }
    }

        //previous Day Button
        prevButton.setOnClickListener {
            if (selectionPosition == 1) {selectionPosition -= 1
                DaysSpinner.setSelection(selectionPosition)
                prevButton.visibility=View.INVISIBLE
            }else{
                nextButton.visibility=View.VISIBLE
                selectionPosition -= 1
                DaysSpinner.setSelection(selectionPosition)
            }
        }

        //get From Database
        loadFromQuery(date)

        //get From FireBase
        getfromFirbase()

        //Up Button
        home3.setOnClickListener {this.finish()}

        //Admin Button
        button2.setOnClickListener {
            click++
            if(click==3){
                Toast.makeText(this,"Admin",Toast.LENGTH_SHORT).show()

                var i=Intent(this,AdminLogIn::class.java)
                startActivity(i)

                click=0
            }

        }
    }

    fun loadFromQuery( date:String) {
        var Db = DbManager(this)
        val selectionArgs = arrayOf(date)
        var cursor = Db.query(null, "date =?", selectionArgs, "time")
        if (cursor.moveToFirst()) {
            matches.clear()
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
            day_listView.visibility = View.VISIBLE
            nomatches_tv.visibility=View.GONE
            adapter = matchAdapter(this@Main3Activity, matches)
            day_listView.adapter = adapter
            day_listView.divider=resources.getDrawable(R.color.white)
            day_listView.dividerHeight = 10
        }else {
            day_listView.visibility = View.GONE
            nomatches_tv.visibility=View.VISIBLE
        }
        cursor.close()
    }

    fun getfromFirbase(){
        // Read from the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("matches")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value as ArrayList<HashMap<String, String>>
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
                    loadFromQuery(date)
                }

                Toast.makeText(this@Main3Activity,"Updated", Toast.LENGTH_SHORT).show()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

    }

    fun insertToQuery(matchesList: ArrayList<match>){
        var Db = DbManager(this)
        var values = ContentValues()
        for (i in 0 until matchesList.size) {
            var Image1: Int
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
            val Image2: Int = when(matchesList[i].team2LogoInt) {
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
