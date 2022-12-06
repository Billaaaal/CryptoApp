package com.example.cryptoapp

import com.robinhood.spark.SparkAdapter

class CryptoSparkAdapter(var cryptoData:List<Float>): SparkAdapter() {
    override fun getY(index: Int): Float {
        var chosenData = cryptoData[index]
        return chosenData

    }

    override fun getItem(index: Int) = cryptoData[index]


    override fun getCount() = cryptoData.size


}