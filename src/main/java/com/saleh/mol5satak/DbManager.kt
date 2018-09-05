package com.saleh.mol5satak

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

class DbManager {

    val DbName="Matches"
    val Dbtable1="matshes"
    val col_ID="ID"
    val col_team1     ="team1"
    val col_team2     ="team2"
    val col_team1Score="team1Score"
    val col_team2Score="team2Score"
    var team1LogoInt  ="team1LogoInt"
    var team2LogoInt  ="team2LogoInt"
    var winner        ="winner"
    var date          ="date"
    var time          ="time"
    var round         ="round"
    var roundId       ="roundId"
    var roundMatchId  ="roundMatchId"
    val DbVersion = 2
    val mySqlStatement = "create table IF NOT EXISTS $Dbtable1 (" +
            "$col_ID INTEGER primary Key ," +
            "$col_team1 TEXT," +
            "$col_team2 TEXT," +
            "$col_team1Score TEXT," +
            "$col_team2Score TEXT," +
            "$team1LogoInt INTEGER,"+
            "$team2LogoInt INTEGER,"+
            "$winner INTEGER,"+
            "$date TEXT," +
            "$time TEXT,"+
            "$round TEXT,"+
            "$roundId INTEGER,"+
            "$roundMatchId INTEGER"+
            ");"
    /*------------------------------------------------------------------*/
    var Dbtable2="leagueTable"
    var col_ID2          ="ID"
    var col_name         ="teamName"
    var col_playedMatches="playedMatches"
    var col_win   ="win"
    var col_lose  ="lose"
    var col_draw  ="draw"
    var col_plus  ="plus"
    var col_minus ="minus"
    var col_farq  ="farq"
    var col_points="points"
    val mySqlStatement2 = "create table IF NOT EXISTS $Dbtable2 (" +
            "$col_ID2 INTEGER primary Key ," +
            "$col_name TEXT ," +
            "$col_playedMatches INTEGER ," +
            "$col_win    INTEGER," +
            "$col_lose   INTEGER ," +
            "$col_draw   INTEGER ," +
            "$col_plus   INTEGER ," +
            "$col_minus  INTEGER ," +
            "$col_farq   INTEGER ," +
            "$col_points INTEGER " +
            ");"

    var sqlDB:SQLiteDatabase?=null

    constructor(context: Context){
        val db=DbHelper(context)
        this.sqlDB =db.writableDatabase
    }


    inner class DbHelper : SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context) : super(context, DbName, null, DbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(mySqlStatement)
            db.execSQL(mySqlStatement2)
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table if EXISTS Dbtable1")
            db  .execSQL("Drop table if EXISTS Dbtable2")

        }

    }
    fun insert(value:ContentValues):Long{
        val ID=sqlDB!!.insert(Dbtable1,"UnKnown ",value)
        return ID
    }
    fun query(projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sorOrder:String): Cursor {
        val qb= SQLiteQueryBuilder()
        qb.tables=Dbtable1
        var cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
        return cursor
    }
    fun Update(values:ContentValues, selection: String?, selectionargs: Array<String>?):Int{

        val count=sqlDB!!.update(Dbtable1,values,selection,selectionargs)
        return count
    }
    fun deleteall(){
        sqlDB!!.delete(Dbtable1,null,null)
    }


    fun insertTable(value:ContentValues):Long{
        val ID=sqlDB!!.insert(Dbtable2,"UnKnown ",value)
        return ID
    }
    fun queryTable(projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sorOrder:String): Cursor {
        val qb= SQLiteQueryBuilder()
        qb.tables=Dbtable2
        var cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sorOrder)
        return cursor
    }
    fun UpdateTable(values:ContentValues, selection: String?, selectionargs: Array<String>?):Int{

        val count=sqlDB!!.update(Dbtable2,values,selection,selectionargs)
        return count
    }
    fun deleteallTable(){
        sqlDB!!.delete(Dbtable2,null,null)
    }

}