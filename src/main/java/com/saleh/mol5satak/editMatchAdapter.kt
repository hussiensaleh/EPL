package com.saleh.mol5satak

import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.edit_ticket.view.*
import kotlinx.android.synthetic.main.ticket.view.*

class editMatchAdapter: BaseAdapter {

    var context: Context
    var matches=ArrayList<match>()
    var result1=0
    var result2=0
    var result3=0
    constructor(context:Context,matches:ArrayList<match>){
        this.context=context
        this.matches=matches

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var layoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var myView=layoutInflater.inflate(R.layout.edit_ticket,null)
        myView.Eteam1_tv.text=matches[position].team1
        myView.Eteam2_tv.text=matches[position].team2
        myView.Elogo1.setBackgroundResource(matches[position].team1LogoInt)
        myView.Elogo2.setBackgroundResource(matches[position].team2LogoInt)
        myView.Edate_tv.text=matches[position].date
        myView.Etime_tv.text=matches[position].time
        spinnerAdapter(myView.Escore1_tv,R.array.Score,"score")
        myView.Escore1_tv.setSelection(matches[position].team1Score)
        result1=matches[position].team1Score
        spinnerAdapter(myView.Escore2_tv,R.array.Score,"score")
        myView.Escore2_tv.setSelection(matches[position].team2Score)
        result2=matches[position].team2Score
        spinnerAdapter(myView.Estatus_tv,R.array.status,"status")
        result3=matches[position].winner
        myView.Estatus_tv.setSelection(
            when(matches[position].winner){
                (-1)->0
                0   ->1
                1   ->2
                2   ->2
                3   ->2
                else->0
        })
        myView.done_tv.setOnClickListener{
            matches[position].team1Score=result1
            matches[position].team2Score=result2
            matches[position].winner    =result3
            var values = ContentValues()
            var Db = DbManager(context)
            var selectionArgs= arrayOf(matches[position].roundId.toString(),matches[position].roundMatchId.toString())
            values.clear()
            values.put("team1"       ,matches[position].team1)
            values.put("team2"       ,matches[position].team2)
            values.put("team1Score"  ,matches[position].team1Score)
            values.put("team2Score"  ,matches[position].team2Score)
            values.put("team1LogoInt",matches[position].team1LogoInt)
            values.put("team2LogoInt",matches[position].team2LogoInt)
            values.put("winner"      ,matches[position].winner)
            values.put("date"        ,matches[position].date)
            values.put("time"        ,matches[position].time)
            values.put("round"       ,matches[position].round)
            values.put("roundId"     ,matches[position].roundId)
            values.put("roundMatchId",matches[position].roundMatchId)
            Db.Update(values,"roundId=? and  roundMatchId=?",selectionArgs)
            addtofirebase(loadFromQuery())
        }

        return  myView
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return matches.size
    }

    fun spinnerAdapter(spinner: Spinner,array:Int,type:String):Int{
        var result=0
        val adapter1 = ArrayAdapter.createFromResource(context,
                array, android.R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter1
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                when(type) {
                    "score" -> {
                        result = 0
                    }
                    "status" -> {
                        result = -1

                    }
                }
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(type) {
                    "score"-> {
                        when (position) {
                            0->result=0
                            1->result=1
                            2->result=2
                            3->result=3
                            4->result=4
                            5->result=5
                            6->result=6
                            7->result=7
                            8->result=8
                            9->result=9
                        }
                    }
                    "status"->{
                        when (position) {
                            0->result=-1//not yet
                            1->result= 0//is playing
                            2->{
                                result = when {
                                    result1>result2 -> 1 //team1 win
                                    result2>result1 -> 2 //team2 win
                                    else -> 3            //draw
                                }
                            }

                        }

                    }
                }
                when(spinner.id){
                    R.id.Escore1_tv->result1=result
                    R.id.Escore2_tv->result2=result
                    R.id.Estatus_tv->result3=result
                }
            }

        }

        return result
    }

    fun loadFromQuery( sort: String = "ID"):ArrayList<match> {
        var Matches=ArrayList<match>()
        var Db = DbManager(context)
        //val selectionArgs = arrayOf(null)
        val projection = arrayOf("roundId","roundMatchId","team1","team2","team1Score","team2Score","team1LogoInt"
                ,"team2LogoInt","winner","round","date","time")
        var cursor = Db.query(projection, null, null, sort)
        if (cursor.moveToFirst()) {
            do {
                var Image1=0
                Image1=when( cursor.getInt(cursor.getColumnIndex("team1LogoInt"))){
                    R.drawable.ithd_logo   ->2131165281;                 R.drawable.marb_logo->2131165282
                    R.drawable.ptrj_logo   ->2131165299;                 R.drawable.zmlk_logo->2131165304
                    R.drawable.dkhl_logo   ->2131165273;                 R.drawable.dgla_logo->2131165272
                    R.drawable.entg_logo   ->2131165275;                 R.drawable.hars_logo->2131165277
                    R.drawable.ngom_logo   ->2131165285;                 R.drawable.tlae_logo->2131165301
                    R.drawable.gona_logo   ->2131165276;                 R.drawable.msry_logo->2131165284
                    R.drawable.ahly_logo   ->2131165268;                 R.drawable.isml_logo->2131165280
                    R.drawable.mqsa_logo   ->2131165283;                 R.drawable.smha_logo->2131165300
                    R.drawable.enpi_logo   ->2131165274;                 R.drawable.prmd_logo->2131165298
                    else->R.drawable.dawry_logo
                }
                var Image2=0
                Image2=when(cursor.getInt(cursor.getColumnIndex("team2LogoInt"))) {
                    R.drawable.ithd_logo   ->2131165281;                 R.drawable.marb_logo->2131165282
                    R.drawable.ptrj_logo   ->2131165299;                 R.drawable.zmlk_logo->2131165304
                    R.drawable.dkhl_logo   ->2131165273;                 R.drawable.dgla_logo->2131165272
                    R.drawable.entg_logo   ->2131165275;                 R.drawable.hars_logo->2131165277
                    R.drawable.ngom_logo   ->2131165285;                 R.drawable.tlae_logo->2131165301
                    R.drawable.gona_logo   ->2131165276;                 R.drawable.msry_logo->2131165284
                    R.drawable.ahly_logo   ->2131165268;                 R.drawable.isml_logo->2131165280
                    R.drawable.mqsa_logo   ->2131165283;                 R.drawable.smha_logo->2131165300
                    R.drawable.enpi_logo   ->2131165274;                 R.drawable.prmd_logo->2131165298
                    else->R.drawable.dawry_logo
                }
                Matches.add(match(
                        cursor.getInt(cursor.getColumnIndex("roundId"     )),
                        cursor.getInt(cursor.getColumnIndex("roundMatchId")),
                        cursor.getString(cursor.getColumnIndex("team1"    )),
                        cursor.getString(cursor.getColumnIndex("team2"    )),
                        cursor.getInt(cursor.getColumnIndex("team1Score"  )),
                        cursor.getInt(cursor.getColumnIndex("team2Score"  )),
                        Image1,
                        Image2,
                        cursor.getInt(cursor.getColumnIndex("winner"      )),
                        cursor.getString(cursor.getColumnIndex("round"    )),
                        cursor.getString(cursor.getColumnIndex("date"     )),
                        cursor.getString(cursor.getColumnIndex("time"     ))
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return Matches
    }

    fun addtofirebase(list: ArrayList<match>){
// Write a message to the database
        val database = FirebaseDatabase.getInstance()
        var myRef=database.getReference("matches")
        myRef.setValue(list)
        Toast.makeText(context,"تم الحفظ",Toast.LENGTH_SHORT).show()
    }


}