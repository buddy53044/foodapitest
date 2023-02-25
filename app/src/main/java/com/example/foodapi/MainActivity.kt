package com.example.foodapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        System.out.println("hello")

        var btn_query = findViewById<Button>(R.id.btn_query)

        btn_query.setOnClickListener {
            //關閉按鈕避免再次查詢
            btn_query.isEnabled = false
            //發送請求
            sendRequest()
        }

    }

    private fun sendRequest() {
        val url = "https://api.edamam.com/api/food-database/v2/parser?app_id=ed8896ab&app_key=35488a643aa2e7b7691d085b31de8587&nutrition-type=cooking"
        val req = Request.Builder()
            .url(url)
            .build()

        //建立 OkHttpClient 物件，藉由 newCall()發送請求，並在 enqueue()接收回傳
        OkHttpClient().newCall(req).enqueue(object : Callback {
            //發送成功執行此方法
            override fun onResponse(call: Call, response: Response) {
                //使用 response.body?.string()取得 JSON 字串
                val json = response.body?.string()
                //建立 Gson 並使用其 fromJson()方法，將 JSON 字串以 MyObject 格式輸出
                val myObject = Gson().fromJson(json, input::class.java)
                //顯示結果
                System.out.println(myObject.hints.get(0).food.knownAs)

            }
            //發送失敗執行此方法
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    //開啟按鈕可再次查詢
                    Toast.makeText(this@MainActivity,
                        "查詢失敗$e", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}
//    class test {
//
//        lateinit var hints: Hint
//
//
//         class Hint(
//            val food: FoodX,
//            val measures: List<Measure>
//        )
//
//
//
//        data class FoodX(
//            val category: String,
//            val categoryLabel: String,
//            val foodId: String,
//            val image: String,
//            val knownAs: String,
//            val label: String,
//            val nutrients: Nutrients
//        )
//
//        data class Measure(
//            val label: String,
//            val qualified: List<Qualified>,
//            val uri: String,
//            val weight: Double
//        )
//
//        data class Nutrients(
//            val CHOCDF: Double,
//            val ENERC_KCAL: Double,
//            val FAT: Double,
//            val FIBTG: Double,
//            val PROCNT: Double
//        )
//
//        data class Qualified(
//            val qualifiers: List<Qualifier>,
//            val weight: Double
//        )
//
//        data class Qualifier(
//            val label: String,
//            val uri: String
//        )
//
//    }