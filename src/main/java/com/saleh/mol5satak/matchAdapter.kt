package com.saleh.mol5satak

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.ticket.view.*

class matchAdapter: BaseAdapter {

    var context: Context
    var matches=ArrayList<match>()
    constructor(context:Context,matches:ArrayList<match>){
        this.context=context
        this.matches=matches

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var layoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var myView=layoutInflater.inflate(R.layout.z_ticket,null)
        myView.team1_tv.text=matches[position].team1
        myView.team2_tv.text=matches[position].team2
        myView.score1_tv.text=matches[position].team1Score.toString()
        myView.score2_tv.text=matches[position].team2Score.toString()
        myView.logo1.setBackgroundResource(matches[position].team1LogoInt)
        myView.logo2.setBackgroundResource(matches[position].team2LogoInt)
        myView.date_tv.text=matches[position].date
        myView.time_tv.text=matches[position].time
        when(matches[position].winner){
/*notPlayed*/(-1)-> {
                myView.score1_tv.visibility= View.GONE
                myView.score2_tv.visibility= View.GONE
                myView.status_tv.text = "لم تبدأ بعد"
        }
/*team1Win */  1 ->{myView.status_tv.text = "  انتهت  " }
/*team2Win */  2 ->{myView.status_tv.text = "  انتهت  " }
/*Draw     */  3 ->{myView.status_tv.text = "  انتهت  " }
/*isPlaying*/  0 ->{myView.status_tv.text = "   الان   " }
        }
        if (matches[position].winner==-1) {
            myView.goals.visibility = View.GONE
        }
        myView.goals.setOnClickListener {
            if (matches[position].winner!=-1) {
                if(matches[position].team1Score==0&&matches[position].team2Score==0){
                    Toast.makeText(context,"لا يوجد اهداف ",Toast.LENGTH_SHORT).show()
                }else {
                    var i = Intent(context, MatchDetailsActivity::class.java)
                    i.putExtra("roundId", matches[position].roundId)
                    i.putExtra("roundMatchId", matches[position].roundMatchId)
                    context.startActivity(i)
                }
            }
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


}