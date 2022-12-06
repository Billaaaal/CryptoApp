package com.example.cryptoapp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import java.util.*


//import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        var mPrefs = getSharedPreferences("THEME", 0)
        var light_mode = mPrefs.getBoolean("light_mode", true)




        if (light_mode){

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


        }
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_CryptoApp)




        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        var SwitchButton = findViewById<Switch>(R.id.switch2)

        if (light_mode){
            SwitchButton.isChecked = false


        }else{
            SwitchButton.isChecked = true


        }





        //setContentView(R.layout.activity_main)

        //var mPrefs = getSharedPreferences("THEME", 0)
        //val mEditor = mPrefs.edit()
        //mEditor.putString("light_mode", "HELLO WORLD").commit();


        //var light_mode = mPrefs.getString("light_mode", "default")
        //Toast.makeText(this, light_mode.toString(), Toast.LENGTH_SHORT).show()

        //var mPrefs = getSharedPreferences("THEME", 0)





        //Toast.makeText(this, "Activity main started", Toast.LENGTH_SHORT).show()

        ///////initPython() PEUT ETRE A RAJOUTER
        var current = ""

        var currency = "eur"




        //setTheme(R.style.Theme_CryptoAppDark)


        var recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        var button = findViewById<Button>(R.id.button)

        var connectionimage = findViewById<ImageView>(R.id.imageconnection)

        var textViewOops = findViewById<TextView>(R.id.textViewConnection)

        var detailsconnection = findViewById<TextView>(R.id.details)



        var darkMode = true

        if (darkMode==true){
            //set Darkmode
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            // setTheme(R.style.Theme_CryptoAppDark)
            //setContentView(R.layout.activity_main)

        }

        SwitchButton.setOnClickListener {
            var checked = (it as Switch).isChecked

            if (checked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                var mEditor = mPrefs.edit()
                mEditor.putBoolean("light_mode", false).commit();


                //setTheme(R.style.Theme_CryptoAppDark)
                //recreate()
                //.makeText(this@MainActivity, "Checked !", Toast.LENGTH_SHORT).show()
                //setTheme(R.style.Theme_CryptoAppDark)
                //super.onCreate(savedInstanceState)
                // Call setTheme before creation of any(!) View.
                //setTheme(R.style.Theme_CryptoAppDark)

                //super.recreate()

                // ...
                //setContentView(R.layout.activity_main)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                var mEditor = mPrefs.edit()
                mEditor.putBoolean("light_mode", true).commit();
                //super.onCreate(savedInstanceState)
                // Call setTheme before creation of any(!) View.
                //setTheme(R.style.Theme_CryptoApp)

                // ...
                //setContentView(R.layout.activity_main)
                //Toast.makeText(this@MainActivity, "Not checked !", Toast.LENGTH_SHORT).show()
                //setTheme(R.style.Theme_CryptoApp)
                //super.onCreate(savedInstanceState)
            }
            //val intent = intent
            //overridePendingTransition(0, 0)
            //intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT       )
            // finish()
            //overridePendingTransition(0, 0)
            //startActivity(intent)
        }



        button.text = currency





        //Toast.makeText(this@MainActivity, button.text, Toast.LENGTH_SHORT).show()

        //var adapter: CustomAdapter? = null

        var number_of_elements = 49



        var toreturned = initialize(number_of_elements, currency)

        var frominitialize = true



        //Toast.makeText(this@MainActivity, toreturned.toString(), Toast.LENGTH_SHORT).show()

        var data = toreturned.data

        current = toreturned.returned

        var adapter = data?.let { CustomAdapter(it) }



        //current = initialize()

        //Log.i("INITIAL", current.toString())

        //Toast.makeText(this@MainActivity, current, Toast.LENGTH_SHORT).show()

        var refresh = findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.refresh)

        var restartbutton = findViewById<Button>(R.id.restart)



        //val button = findViewById<Button>(R.id.button)

        // button.setOnClickListener {
        //    initPython()
        //    refresh_list()

        //}





        button.setOnClickListener {
            //Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            var oldCurrency = currency
            when (currency) {
                "eur" -> currency = "usd"
                "usd" ->  currency = "btc"
                "btc" ->  currency = "eur"

            }



            //frominitialize = false //très important

            var returned = getRequest(number_of_elements, currency)


            if(returned.joinToString("")=="error"){
                Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_SHORT).show()
                currency = oldCurrency
                button.text = currency

            }else{
                button.text = currency

                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        returned = getRequest(number_of_elements, currency)
                        //var returned = getRequest(number_of_elements, currency)
                        //var returned = getRequest(number_of_elements, currency)

                        data?.clear()

                        for (crypto in returned){

                            var crypto = crypto.asList()

                            var coin_name = crypto[0].toString()
                            var coin_symbol = crypto[1].toString()
                            var coin_rank = crypto[2].toString()
                            var coin_price = crypto[3].toString()
                            var coin_change_percentage = crypto[4].toString()
                            var change_status = crypto[5].toString()
                            var coin_image = crypto[6].toString()
                            var coin_chart_ = crypto[7].asList()
                            var coin_chart = coin_chart_.map { it.toFloat() }.toList()//toTypedArray()

                            //Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()

                            //var coin_name = "Name"
                            //var coin_symbol ="Symbol"
                            //var coin_rank = "R"
                            //var coin_price = "Price"
                            //var coin_change_percentage = "Perc."
                            //var coin_image = " "



                            //Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()


                            //Toast.makeText(this@MainActivity, coin_name, Toast.LENGTH_SHORT).show()



                            data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image, coin_chart, this@MainActivity, currency))
                        }
                        runOnUiThread {
                            recyclerview.adapter!!.notifyDataSetChanged()
                            current = getRequest(number_of_elements, currency).toString()
                        }

                    }
                }, 0)

            }


        }

        refresh.setOnRefreshListener {
            //Log.i("INITIAL", returned.toString())
            //Log.i("NEW", getRequest().toString())

            //current = initialize()
            //Toast.makeText(this@MainActivity, data.toString(), Toast.LENGTH_SHORT).show()



            //recyclerview.adapter = adapter

            //Toast.makeText(this@MainActivity, data.toString(), Toast.LENGTH_SHORT).show()

            //change getrequest
            //if (current == getRequest(number_of_elements, currency).toString()){
            //    Toast.makeText(this@MainActivity, "Up to date", Toast.LENGTH_SHORT).show()
                //Snackbar.make(findViewById<RecyclerView>(R.id.linearlayout),"Up to date", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.rgb(0, 212,255)).show()
            //    refresh.isRefreshing = false
            ////Toast.makeText(this@MainActivity, "Not up to date", Toast.LENGTH_SHORT).show()
            //}
            //else{
                //Toast.makeText(this@MainActivity, "Not up to date", Toast.LENGTH_SHORT).show()
                //data?.clear() IMPORTANT A ENLEVER OU RAJOUTER

                //var data = ArrayList<ItemsViewModel>()

                //data!!.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, "coin_name", "coin_symbol", "coin_rank", "coin_price", "coin_change_percentage","change_status", "https://images.genius.com/071a819f088609ee9d24b2dba4546b23.1000x1000x1.jpg"))
            //var changed = false
            //var ee = 0

            var returned = getRequest(number_of_elements, currency)




            if (returned.joinToString("")=="error"){
                frominitialize = false
                refresh.isRefreshing = false
                Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_SHORT).show()
            }else{


                if (frominitialize == true){
                    frominitialize = false
                    refresh.isRefreshing = false
                    Toast.makeText(this@MainActivity, "Up to date", Toast.LENGTH_SHORT).show()
                    var current = getRequest(number_of_elements, currency)


                }else {
                    //éviter les freeze lors du rafraichissement en mettant getRequest dans un Thread{}.start()

                    //val foo = Foo()
                    //val thread = Thread(foo)
                    //thread.start()
                    //thread.join()
                    //val value = foo.value



                    if (current == returned.toString()) {
                        refresh.isRefreshing = false
                        Toast.makeText(this@MainActivity, "Up to date", Toast.LENGTH_SHORT).show()

                    } else {

                        for (crypto in returned) {

                            var crypto = crypto.asList()

                            var coin_name = crypto[0].toString()
                            var coin_symbol = crypto[1].toString()
                            var coin_rank = crypto[2].toString()
                            var coin_price = crypto[3].toString()
                            var coin_change_percentage = crypto[4].toString()
                            var change_status = crypto[5].toString()
                            var coin_image = crypto[6].toString()
                            var coin_chart_ = crypto[7].asList()
                            var coin_chart = coin_chart_.map { it.toFloat() }.toList()//toTypedArray()

                            //val objectList: MutableList<PyObject> = List<String>(coin_chart_)
                            //var coin_chart_ = crypto[7].toString()
                            //var coin_chart = coin_chart_.split(",").map(String::toFloat)

                            //Toast.makeText(this@MainActivity, ints[0].toString(), Toast.LENGTH_SHORT).show()

                            //Toast.makeText(this@MainActivity, "Were here !".toString(), Toast.LENGTH_SHORT).show()


                            //var coin_name = "Name"
                            //var coin_symbol ="Symbol"
                            //var coin_rank = "R"
                            //var coin_price = "Price"
                            //var coin_change_percentage = "Perc."
                            //var coin_image = " "


                            if (coin_name in current && coin_rank in current && coin_price in current && coin_change_percentage in current) {
                                //Toast.makeText(this@MainActivity, "$coin_name est présent", Toast.LENGTH_SHORT).show()
                                refresh.isRefreshing = false


                            } else {

                                data.add(
                                    ItemsViewModel(
                                        R.drawable.ic_baseline_folder_24,
                                        coin_name,
                                        coin_symbol,
                                        coin_rank,
                                        coin_price,
                                        coin_change_percentage,
                                        change_status,
                                        coin_image,
                                        coin_chart,
                                        this@MainActivity,
                                        currency
                                    )
                                )
                                //adapter.notifyItemChanged(coin_rank.toInt())

                                //changed = true
                                adapter.notifyItemChanged(coin_rank.toInt())
                                refresh.isRefreshing = false
                                //ee = ee + 1
                                //Log.i("TEST", "$ee")

                            }
                        }


                        //Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()


                        //Toast.makeText(this@MainActivity, coin_name, Toast.LENGTH_SHORT).show()


                        //data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image, coin_chart, this@MainActivity))

                        //verifier dans la data si element présent

                        //data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image))


                    }


                    //adapter.notifyItemChanged(row)


                    //recyclerview.adapter!!.notifyDataSetChanged() //à remplacer par adapter.notifyItemChanged(row)

                    //if (changed ==false){
                    //    Toast.makeText(this@MainActivity, "Up to date", Toast.LENGTH_SHORT).show()
                    //}


                    current = returned.toString()

                    //recyclerview.post(){
                    //Toast.makeText(this@MainActivity, "Complete !", Toast.LENGTH_SHORT).show()
                    //    refresh.isRefreshing = false
                    //}
                    //current = getRequest(number_of_elements, currency).toString()
                    //current = initialize().returned
                    //refresh.isRefreshing = false


                    //    Toast.makeText(this@MainActivity, "Up to date".toString(), Toast.LENGTH_SHORT).show()
                    //    refresh.isRefreshing = false
                    //
                    //}else{
                    //    //refresh()
                    //    initialize()
                    //    refresh.isRefreshing = false

                    //}
                }
            }


        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    //Toast.makeText(this@MainActivity, "Last", Toast.LENGTH_SHORT).show()
                    var returned = addCrypto(number_of_elements, currency)

                    if(returned.joinToString("")=="error"){
                        Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_SHORT).show()

                    }else{

                        for (crypto in returned){

                            var crypto = crypto.asList()

                            var coin_name = crypto[0].toString()
                            var coin_symbol = crypto[1].toString()
                            var coin_rank = crypto[2].toString()
                            var coin_price = crypto[3].toString()
                            var coin_change_percentage = crypto[4].toString()
                            var change_status = crypto[5].toString()
                            var coin_image = crypto[6].toString()
                            var coin_chart_ = crypto[7].asList()
                            var coin_chart = coin_chart_.map { it.toFloat() }.toList()//toTypedArray()

                            //Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()


                            //var coin_name = "Name"
                            //var coin_symbol ="Symbol"
                            //var coin_rank = "R"
                            //var coin_price = "Price"
                            //var coin_change_percentage = "Perc."
                            //var coin_image = " "



                            //Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()


                            //Toast.makeText(this@MainActivity, coin_name, Toast.LENGTH_SHORT).show()



                            data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image, coin_chart,this@MainActivity, currency))



                            //data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image))


                        }


                        for (i in number_of_elements+1..number_of_elements + 20){
                            recyclerview.adapter!!.notifyItemInserted(i)
                            //recyclerview.adapter!!.notifyItemInserted(i)

                        }

                        //recyclerview.adapter!!.notifyDataSetChanged()

                        current = getRequest(number_of_elements, currency).toString()

                        number_of_elements = number_of_elements + 20
                    }


                }


            }
        })



    }

    data class Toreturn(val returned: String, val data: ArrayList<ItemsViewModel>)

    fun initialize(number_of_elements: Int, currency:String): Toreturn {//:MutableList<PyObject>{
        // getting the recyclerview by its id




        // This loop will create 20 Views containing
        // the image with the count of view
        //for (i in 1..20) {
        //data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, "Item $i"))
        //}

        val extras = intent.extras
        //var returned: MutableList<Any> = ArrayList()


        //for (i in 1..49){
            //Toast.makeText(this@MainActivity, extras!!.getStringArrayList("$i").toString(), Toast.LENGTH_SHORT).show()
            //returned.add(extras!!.getStringArrayList("$i")!!)


        //}



       // var returned = getRequest(number_of_elements, currency)

        var data = ArrayList<ItemsViewModel>()



        var recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel

        //('Ethereum', 'eth', 2, 2457.99, 'https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880', -0.1565)



        var button = findViewById<Button>(R.id.button)


        //val he = returned[0]

        //val hez = he[0]

        //var nameOfClass = he::class.simpleName

        //Toast.makeText(this@MainActivity, sar, Toast.LENGTH_SHORT).show()

        var returned = ""

        //Toast.makeText(this@MainActivity, , Toast.LENGTH_SHORT).show()

        if (extras!!.getStringArrayList("1")!!.joinToString("")+extras!!.getStringArrayList("2")!!.joinToString("")+extras!!.getStringArrayList("3")!!.joinToString("")+extras!!.getStringArrayList("4")!!.joinToString("")+extras!!.getStringArrayList("5")!!.joinToString("")=="error"){
            //findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.refresh).setVisibility(View.VISIBLE)
            //connectionimage.setVisibility(View.VISIBLE)
            //textViewOops.setVisibility(View.VISIBLE)
            //detailsconnection.setVisibility(View.VISIBLE)
            //restartbutton.setVisibility(View.VISIBLE)
            //button.setVisibility(View.INVISIBLE)
            //Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_SHORT).show()
            setContentView(R.layout.connection_error_layout)
            return Toreturn(returned.toString(), data)
        }else{

            for (i in 1..49){
                //Toast.makeText(this@MainActivity, extras!!.getStringArrayList("$i").toString(), Toast.LENGTH_SHORT).show()
                var he = extras!!.getStringArrayList("$i")!!

                returned = returned + he.toString()



                var crypto = he

                var coin_name = crypto[0].toString()
                var coin_symbol = crypto[1].toString()
                var coin_rank = crypto[2].toString()
                var coin_price = crypto[3].toString()
                var coin_change_percentage = crypto[4].toString()
                var change_status = crypto[5].toString()
                var coin_image = crypto[6].toString()
                var coin_chart_ = crypto[7].replace("[", "").replace("]", "")
                var coin_chart: List<Float> = coin_chart_.split(", ").map{ it.trim().toFloat() }

                //Log.i("CHART", coin_chart.toString())



                //var coin_chart = coin_chart_.map { it.toFloat() }.toList()//toTypedArray()

                //val objectList: MutableList<PyObject> = List<String>(coin_chart_)
                //var coin_chart_ = crypto[7].toString()
                //var coin_chart = coin_chart_.split(",").map(String::toFloat)

                //Toast.makeText(this@MainActivity, ints[0].toString(), Toast.LENGTH_SHORT).show()

                //Toast.makeText(this@MainActivity, ints[0]::class.simpleName.toString(), Toast.LENGTH_SHORT).show()

                //Toast.makeText(this@MainActivity, coin_chart_[0]::class.simpleName.toString(), Toast.LENGTH_SHORT).show()
                //println()
                //var coin_chart = coin_chart_.split(",").map(String::toFloat)

                //Toast.makeText(this@MainActivity, coin_chart[0].toString(), Toast.LENGTH_SHORT).show()


                //var coin_name = "Name"
                //var coin_symbol ="Symbol"
                //var coin_rank = "R"
                //var coin_price = "Price"
                //var coin_change_percentage = "Perc."
                //var coin_image = " "



                //Toast.makeText(this@MainActivity, coin_chart[0].toString(), Toast.LENGTH_SHORT).show()


                //Toast.makeText(this@MainActivity, coin_name, Toast.LENGTH_SHORT).show()

                //var coooinChart: List<Float> = listOf(1.12f, 2.22f, 3.34f)



                data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image, coin_chart, this@MainActivity, currency))



                //data.add(ItemsViewModel(R.drawable.ic_baseline_folder_24, coin_name, coin_symbol, coin_rank, coin_price, coin_change_percentage,change_status, coin_image))



                //returned[0].asList()[1].toString()





                //Log.i("Vive", data.size.toString())
                // This will pass the ArrayList to our Adapter
                var adapter = CustomAdapter(data)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

                //var returned = getRequest()

                //var current = getRequest()

                //return "PASSED"

                //return getRequest().toString()



                //return returned.toString()






                //val he = returned[0]

                //val hez = he[0]

                //var nameOfClass = he::class.simpleName

                //for (crypto in returned){

                //    var crypto = crypto.asList()

                //    Toast.makeText(this@MainActivity, crypto[0].toString(), Toast.LENGTH_SHORT).show()

                //}

                //Toast.makeText(this@MainActivity, returned.toString(), Toast.LENGTH_SHORT).show()
                //Log.i("TAG", returned.toString())


            }
        }

        return Toreturn(returned.toString(), data)




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

    private fun addCrypto(range:Int, currency:String): MutableList<PyObject> {
        var python = Python.getInstance()
        var pythonFile = python.getModule("crypto_get")
        return pythonFile.callAttr("add_crypto", range, currency).asList() //return pythonFile.callAttr("main_request", userInput).toString()

    }

    private fun checkConnection(): Boolean{
        var python = Python.getInstance()
        var pythonFile = python.getModule("crypto_get")
        return pythonFile.callAttr("add_crypto").toBoolean() //return pythonFile.callAttr("main_request", userInput).toString()

    }






}
