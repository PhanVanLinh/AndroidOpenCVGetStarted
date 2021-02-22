package com.example.androidopencvgetstarted

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var srcBitmap: Bitmap
    lateinit var dstBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        findViewById<TextView>(R.id.myTextView).text = stringFromJNI()

        srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.photo)
        dstBitmap = srcBitmap.copy(srcBitmap.config, true)

        findViewById<ImageView>(R.id.myImage).setImageBitmap(dstBitmap)

        findViewById<Button>(R.id.button_flip).setOnClickListener {
            doFlip()
        }

        findViewById<Button>(R.id.button_blur).setOnClickListener {
            doBlur()
        }
    }

    private fun doFlip() {
        myFlip(srcBitmap, dstBitmap)
    }

    /**
     * Sigma from 0.1 - 10
     */
    private fun doBlur() {
        myBlur(srcBitmap, dstBitmap, 9f)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    external fun myFlip(bitmapIn: Bitmap, bitmapOut: Bitmap)
    external fun myBlur(bitmapIn: Bitmap, bitmapOut: Bitmap, sigma: Float)

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}