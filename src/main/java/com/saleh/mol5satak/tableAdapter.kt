package com.saleh.mol5satak

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.table_ticket.view.*

class tableAdapter: BaseAdapter {

    var context: Context
    var teams=ArrayList<TableTeam>()
    constructor(context:Context,teams:ArrayList<TableTeam>){
        this.context=context
        this.teams=teams

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var layoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var myView=layoutInflater.inflate(R.layout.table_ticket,null)
        var team=teams[position]
        myView.rank_tv.text=(position+1).toString()
        myView.team_tv.text=team.name
        myView.played_tv.text=team.playedMatches.toString()
        myView.win_tv.text=team.win.toString()
        myView.draw_tv.text=team.draw.toString()
        myView.lose_tv.text=team.lose.toString()
        myView.plus_tv.text=team.plus.toString()
        myView.minus_tv.text=team.minus.toString()
        myView.farq_tv.text=team.farq.toString()
        myView.points_tv.text=team.points.toString()
        return myView
    }

    override fun getItem(position: Int): Any {
        return teams[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return teams.size
    }


}