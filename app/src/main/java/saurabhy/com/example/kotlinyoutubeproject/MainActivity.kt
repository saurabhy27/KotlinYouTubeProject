package saurabhy.com.example.kotlinyoutubeproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMainPlaySingle.setOnClickListener(this)
        btnMainStandalone.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent : Intent
        when(v?.id){
            R.id.btnMainPlaySingle -> intent = Intent(this,YoutubeActivity::class.java)
            R.id.btnMainStandalone -> intent = Intent(this,StandaloneActivity::class.java)
            else -> throw IllegalArgumentException("Unknown Argument")
        }
        startActivity(intent)
    }
}
