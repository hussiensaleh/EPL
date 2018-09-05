package com.saleh.mol5satak


class match {

    var team1 = ""
    var team2 = ""
    var team1Score= 0
    var team2Score= 0
    var team1LogoInt:Int=0
    var team2LogoInt:Int=0
    var winner = -1
    var date=""
    var time=""
    var round=""
    var roundId=1
    var roundMatchId=1
    var stadium=""

    constructor(roundId:Int,roundMatchId:Int,team1: String, team2: String, team1Score: Int, team2Score: Int, team1LogoInt: Int
                , team2LogoInt: Int, winner: Int, round:String,date:String,time:String) {
        this.roundId=roundId
        this.roundMatchId=roundMatchId
        this.team1 = team1
        this.team2 = team2
        this.team1Score = team1Score
        this.team2Score = team2Score
        this.team1LogoInt = team1LogoInt
        this.team2LogoInt = team2LogoInt
        this.winner = winner
        this.round=round
        this.date=date
        this.time=time
    }
    constructor(roundId:Int,roundMatchId:Int,team1: String, team2: String, team1Score: Int, team2Score: Int, team1LogoInt: Int
                , team2LogoInt: Int, winner: Int, round:String,date:String,time:String,stadium:String) {
        this.roundId=roundId
        this.roundMatchId=roundMatchId
        this.team1 = team1
        this.team2 = team2
        this.team1Score = team1Score
        this.team2Score = team2Score
        this.team1LogoInt = team1LogoInt
        this.team2LogoInt = team2LogoInt
        this.winner = winner
        this.round=round
        this.date=date
        this.time=time
        this.stadium=stadium
    }
    constructor(stadium: String){
        this.stadium=stadium
    }
}