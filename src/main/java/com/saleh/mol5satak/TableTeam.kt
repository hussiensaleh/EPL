package com.saleh.mol5satak

class TableTeam{
    var rank         :Int
    var name         :String
    var playedMatches:Int
    var win          :Int
    var lose         :Int
    var draw         :Int
    var plus         :Int
    var minus        :Int
    var farq         :Int
    var points       :Int
    constructor(rank:Int,name:String,playedMatches:Int,win:Int,lose:Int,draw:Int,plus:Int,minus:Int,farq:Int,points:Int){
        this.rank  =rank
        this.name  =name
        this.playedMatches=playedMatches
        this.win   =win
        this.lose  =lose
        this.draw  =draw
        this.plus  =plus
        this.minus =minus
        this.farq  =farq
        this.points=points
    }
}