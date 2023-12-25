package com.example.qranimated

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.example.qranimated.databinding.ActivityMainBinding
import kotlin.math.max

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun StartScanner(view: View) {

    }


    /**
     * A native method that is implemented by the 'qranimated' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun myFlip(bitmapIn: Bitmap, bitmapOut: Bitmap)
    external fun myBlur(bitmapIn: Bitmap, bitmapOut: Bitmap, sigma : Float)

    companion object {
        // Used to load the 'qranimated' library on application startup.
        init {
            System.loadLibrary("qranimated")
        }
    }
}