package com.example.cryptoapp


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.annotation.ColorInt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso



class CustomAdapter(var mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, mList)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.progressBar.visibility = View.VISIBLE
        var ItemsViewModel = mList[position]
        holder.name.text = ItemsViewModel.coin_name
        holder.symbol.text = ItemsViewModel.coin_symbol
        holder.rank.text = ItemsViewModel.coin_rank
        holder.percentage.text = ItemsViewModel.coin_change_percentage
        holder.price.text = ItemsViewModel.coin_price
        
        
        var CryptoData = ItemsViewModel.sparkline
        
        when (ItemsViewModel.change_status) {
            "+" -> holder.percentage.setTextColor(Color.parseColor("#61FF00"))
            "-" -> holder.percentage.setTextColor(Color.parseColor("#FF0000"))

            else -> {

            }
        }


        var sparkAdapter = CryptoSparkAdapter(CryptoData)

        holder.chartView.adapter = sparkAdapter

        var colorRes: Int

        if(CryptoData.first() < CryptoData.last()){
            colorRes = R.color.green

        } else if (CryptoData.first() > CryptoData.last()){
            colorRes = R.color.red



        }
        else{
            colorRes = R.color.green


        }




        @ColorInt var colorInt= ContextCompat.getColor(ItemsViewModel.contexte, colorRes)

        holder.chartView.lineColor = colorInt














        //holder.imageView.setImageResource(ItemsViewModel.image)

        Picasso
            .get()
            .load(ItemsViewModel.coin_image)
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE


                }

                override fun onError(e: Exception?) {
                    TODO("Not yet implemented")
                }

            })
    }





    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, var mList: List<ItemsViewModel>) : RecyclerView.ViewHolder(ItemView){


        var name: TextView = itemView.findViewById(R.id.coin_name)
        var symbol: TextView = itemView.findViewById(R.id.symbol)
        var rank: TextView = itemView.findViewById(R.id.rank)
        var price: TextView = itemView.findViewById(R.id.price)
        var percentage: TextView = itemView.findViewById(R.id.percentage)
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        var imageView: ImageView = itemView.findViewById(R.id.imageview)
        var chartView: com.robinhood.spark.SparkView = itemView.findViewById(R.id.chartView)


        init {
            //Toast.makeText(itemView.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
            ItemView.setOnClickListener() { v: View ->
                var position = adapterPosition
                var ItemsViewModel = mList[position]
                var sparkline = ItemsViewModel.sparkline.toFloatArray()
                var symbol = ItemsViewModel.symboltoadd
                var iconimage = ItemsViewModel.coin_image
                var price = ItemsViewModel.coin_price
                var symbolThre = ItemsViewModel.coin_symbol
                //Toast.makeText(itemView.context, ItemsViewModel.sparkline.toString(), Toast.LENGTH_SHORT).show()
                var intentItem = Intent(v.context, ActivityCrypto::class.java)
                intentItem.putExtra("coin_name", name.text)
                intentItem.putExtra("coin_image", iconimage)
                intentItem.putExtra("coin_price", price)
                intentItem.putExtra("spark", sparkline)
                intentItem.putExtra("currency", symbol)
                intentItem.putExtra("coin_symbol", symbolThre)

                v.getContext().startActivity(intentItem)
                //(this as MainActivity).finish()
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                //(this as Activity).overridePendingTransition(R.anim.slide_in, R.anim.slide_out)

                //finish()

                //Toast.makeText(itemView.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()

            }
        }





    }
}


