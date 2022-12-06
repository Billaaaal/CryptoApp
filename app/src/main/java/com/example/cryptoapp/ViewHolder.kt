package com.example.cryptoapp

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    var name: TextView = itemView.findViewById(R.id.coin_name)
    var symbol: TextView = itemView.findViewById(R.id.symbol)
    var rank: TextView = itemView.findViewById(R.id.rank)
    var price: TextView = itemView.findViewById(R.id.price)
    var percentage: TextView = itemView.findViewById(R.id.percentage)
    var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    var imageView: ImageView = itemView.findViewById(R.id.imageview)
    var chartView: com.robinhood.spark.SparkView = itemView.findViewById(R.id.chartView)


}