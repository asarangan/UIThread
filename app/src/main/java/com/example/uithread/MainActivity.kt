package com.example.uithread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start some dummy thread that is different from UI thread
        Thread(Runnable {
            // performing some dummy time taking operation
            var i=0;
            while(i<100){
                i++

                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    val id = resources.getIdentifier("textview_msg", "id", this.packageName)
                    val btn = findViewById<TextView>(id)
                    btn.setBackgroundResource(R.drawable.red)})
                    sleep(250)
                    this@MainActivity.runOnUiThread(java.lang.Runnable {
                        val id = resources.getIdentifier("textview_msg", "id", this.packageName)
                        val btn = findViewById<TextView>(id)
                        btn.setBackgroundResource(R.drawable.green)})
                        sleep(250)

            }

            // try to touch View of UI thread
            //this@MainActivity.runOnUiThread(java.lang.Runnable {
               // this.textview_msg.text = "Updated from other Thread"
            //})
        }).start()
    }
}
