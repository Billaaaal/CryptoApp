package com.example.cryptoapp

import android.os.Bundle
import android.support.annotation.ColorInt
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.squareup.picasso.Picasso
import java.util.*


class ActivityCrypto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CryptoApp)
        setContentView(R.layout.activity_crypto)
        supportActionBar?.hide()


        //var scrollView = findViewById<ScrollView>(R.id.scrollView)


        var coinnamecrypto = findViewById<TextView>(R.id.coinnamecrypto)

        var dateText = findViewById<TextView>(R.id.date)

        var imageviewSingle = findViewById<ImageView>(R.id.imageviewSingle)

        var scrubval = findViewById<TextView>(R.id.scrubval)

        var pricesingle = findViewById<TextView>(R.id.pricesingle)

        var extrasFromItem = intent.extras


        var coinname = extrasFromItem!!.getString("coin_name")

        var symboltoadd = extrasFromItem!!.getString("currency")

        var coin_symbol = extrasFromItem!!.getString("coin_symbol")

        //var cryptodataEntire = extrasFromItem!!.getFloatArray("spark")!!.toList()

        var get = getChart(coin_symbol, symboltoadd).toList()

        var coin_chart_ = get[0].asList()
        var cryptodataEntire = coin_chart_.map { it.toFloat() }.toList()

        //Log.i("TOAST", cryptodataEntire.toString())


        var CoinImageSingle = extrasFromItem!!.getString("coin_image")

        var price = extrasFromItem!!.getString("coin_price")

        pricesingle.text = price

        Picasso
            .get()
            .load(CoinImageSingle)
            .into(imageviewSingle)


        when (symboltoadd) {
            "eur" -> symboltoadd = " €"
            "usd" ->  symboltoadd = " $"
            "btc" ->  symboltoadd = " ₿"

        }

        coinnamecrypto.text = coinname

        Log.i("TOAST","HELLO"+coinnamecrypto.text.toString())

        var chartViewEntire: com.robinhood.spark.SparkView = findViewById(R.id.chartViewEntire)

        chartViewEntire.bringToFront()








        //Log.i("HO", cryptodataEntire.toString())


        //Log.i("OKI", cryptodataEntire.joinToString(""))

        if (cryptodataEntire.joinToString("")=="0.00.0"){
            cryptodataEntire = extrasFromItem!!.getFloatArray("spark")!!.toList()
            Log.i("HEYY", "ANCIEN GECKO")

        }else{
            Log.i("HEYY", "NOUVEAU")

        }
        var sparkAdapterEntire = CryptoSparkAdapter(cryptodataEntire)

        chartViewEntire.adapter = sparkAdapterEntire

        var colorRes: Int

        if(cryptodataEntire?.first()  < cryptodataEntire!!.last()){
            colorRes = R.color.green

        } else if (cryptodataEntire!!.first() > cryptodataEntire!!.last()){
            colorRes = R.color.red



        }
        else{
            colorRes = R.color.green


        }

        @ColorInt var colorInt= ContextCompat.getColor(this, colorRes)

        chartViewEntire.lineColor = colorInt


        chartViewEntire.setScrubListener {itemData->
            //currentdata = itemData!!.toString()


            //Log.i("scrub", itemData.toString())
            if(itemData == null){

                //scrubInfoTextView.setText(R.string.scrub_empty);

            }else{
                scrubval.text = itemData!!.toString() + symboltoadd
                //scrubval.setText(itemData!!.toString())

            }

            //if ((currentdata.toFloat()-old)>0.5){


            //}else{

            //}
            //var old = currentdata

            //scrubval.text = currentdata

        }

        //var seven = getDaysAgo(7).toString()
        //var ho = seven.split(" ")
        /////var ouiii = ho[0]
        //Log.i("TOAST",ho[1] +" "+ho[2] +" ")

        var test = getDaysAgo(0).toString()
        var test2 = test.split(" ")
        var test3 = test2[3].split(":")
        //Log.i("TOAST",test3[0].toString())

        var n = 0

        var spacement = ""

        if (test3[0].toInt()>12){
            n = 7
            spacement = "     "
        }
        else{
            n = 6
            spacement = "        "


        }








        for (i in n downTo 0){
            var seven = getDaysAgo(i).toString()
            var ho = seven.split(" ")
            //Log.i("TOAST",ho[1] +" "+ho[2] +" ")
            ///Log.i("TOAST",ho[1].split(" ")[1].toString())
            Log.i("TOAST",ho[2] +" "+ho[1])

            //if (){

            //}
            if (i==n){
                dateText.text = ho[2] + " " + ho[1]

            }else{
                dateText.text = dateText.text.toString() + spacement + ho[2] + " " + ho[1]

            }





        }

        Log.i("TOAST",dateText.text.toString())




        //Log.i("TOAST", getDaysAgo(7).toString()+getDaysAgo(6).toString()+getDaysAgo(5).toString()+getDaysAgo(4).toString()+getDaysAgo(3).toString()+getDaysAgo(2).toString()+getDaysAgo(1).toString()+getDaysAgo(0).toString())




        //@ColorInt var colorInt= ContextCompat.getColor(ItemsViewModel.contexte, colorRes)

        //holder.chartView.lineColor = colorInt










    }

    private fun getChart(coin:String?, currency:String?): MutableList<PyObject> {
        var python = Python.getInstance()
        var pythonFile = python.getModule("crypto_get")
       // var toreturnnow = pythonFile.callAttr("singlechart_get", coin, currency).toAr
        //Log.i("TOAST", pythonFile.callAttr("singlechart_get", "btc", "eur").toString())
        //var returnable = toreturnnow.map { it.toFloat() }.toList()
        ///return pythonFile.callAttr("singlechart_get", coin, currency).asList()//return pythonFile.callAttr("main_request", userInput).toString()

        //Log.i("TOAST", coin_to_send.toString())
        //Log.i("TOAST", currency_to_send.toString())

        return pythonFile.callAttr("singlechart_get", coin, currency).asList()

    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }
}