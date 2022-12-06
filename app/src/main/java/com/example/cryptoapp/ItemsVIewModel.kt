package com.example.cryptoapp

import android.content.Context
import com.chaquo.python.PyObject


data class ItemsViewModel(var image: Int, var coin_name: String, var coin_symbol: String, var coin_rank: String, var coin_price: String, var coin_change_percentage: String, var change_status:String, var coin_image: String, var sparkline: List<Float>, val contexte:Context, var symboltoadd:String) {
}
