package com.example.uithread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start some dummy thread that is different from UI thread
        Thread(Runnable {
            // performing some dummy time taking operation
            var i=0;
            while(i<1000){
                i++
                sleep(10)
                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    this.textview_msg.text = i.toString()
                })
            }

            // try to touch View of UI thread
            //this@MainActivity.runOnUiThread(java.lang.Runnable {
               // this.textview_msg.text = "Updated from other Thread"
            //})
        }).start()
    }
}
