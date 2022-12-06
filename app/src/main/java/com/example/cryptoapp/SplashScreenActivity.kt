package com.example.cryptoapp


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.util.*
import kotlin.collections.ArrayList


class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        supportActionBar?.hide()



        Thread {
            Looper.prepare()
            initPython()


            var he = getRequest(49, "eur")

            if (he.joinToString("") == "error") {

                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) this, MainActivity::class.java
                        //intent.putStringArrayListExtra("returned", list)


                        //intent.putParcelableArrayListExtra("VIDEOS", ArrayList(list))
                        var intent = Intent(this@SplashScreenActivity, connectionErrorActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                        finish()
                    }
                }, 2000)


            } else {

                var intent = Intent(this@SplashScreenActivity, MainActivity::class.java)


                var list = ArrayList<String>()
                //for (i in 1..2){
                //    intent.putStringArrayListExtra("$i", list)

                //}

                var rnk = 0

                for (crypto in he) {
                    rnk = rnk + 1
                    for (item in crypto.asList()) {
                        //Log.i("OKI", item.toString())
                        list.add(item.toString())
                        //Log.i("OKI", list.toString())


                    }
                    intent.putStringArrayListExtra("$rnk", list)

                    list = ArrayList<String>() // enlever le "var"
                    //Log.i("OKI", list[7]::class.simpleName.toString())


                    //Log.i("OKI", list.toString())


                }


                //Log.d("LOOOL", he[0].toString())
                //Toast.makeText(this, he.toString(), Toast.LENGTH_SHORT).show()
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) this, MainActivity::class.java
                        //intent.putStringArrayListExtra("returned", list)


                        //intent.putParcelableArrayListExtra("VIDEOS", ArrayList(list))
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                        finish()
                    }
                }, 2000)
            }


            }.start()



    }

    private fun initPython(){
        if (!Python.isStarted()){
            Python.start(AndroidPlatform(this))

        }

    }

    private fun getRequest(number_of_elements:Int, currency:String): MutableList<PyObject> {
        var python = Python.getInstance()
        var pythonFile = python.getModule("crypto_get")
        return pythonFile.callAttr("get_crypto", number_of_elements, currency).asList() //return pythonFile.callAttr("main_request", userInput).toString()




    }
}





