package saurabhy.com.example.kotlinyoutubeproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandaloneActivity : AppCompatActivity() {
    private val tag = "StandaloneActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlayVideo.setOnClickListener(listener)
        btnPlaylist.setOnClickListener(listener)
    }
    val listener = View.OnClickListener { v ->
        val intent: Intent
        when (v.id) {
            R.id.btnPlayVideo -> intent = YouTubeStandalonePlayer.createVideoIntent(
                this, getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_VIDEO_ID, 0, true, true
            )
            R.id.btnPlaylist -> intent = YouTubeStandalonePlayer.createPlaylistIntent(
                this, getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_PLAYLIST, 0, 0, true, false
            )
            else -> throw IllegalArgumentException("Unknown Argument")
        }
        try{
            startActivity(intent)
        } catch (e: ActivityNotFoundException){
            e.printStackTrace()
            Log.d(tag,"ActivityNotFoundException Called with ${e.message}")
        }
    }
}
