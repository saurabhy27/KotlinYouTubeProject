package saurabhy.com.example.kotlinyoutubeproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_youtube.*

const val YOUTUBE_VIDEO_ID = "MNbDddab1tY"
const val YOUTUBE_PLAYLIST = "PL22YQ4Q9H_21rOLVlFYzOyhzntZiDIyol"

class YoutubeActivity : YouTubeBaseActivity() {

    private val DEBUG_RESPONCE_CODE = 1
    private val tag = "YoutubeActivity"
    val youTubePlayer: YouTubePlayerView by lazy { YouTubePlayerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        youTubePlayer.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        activity_youtube.addView(youTubePlayer)
        youTubePlayer.initialize(getString(R.string.GOOGLE_API_KEY), listner)
    }

    val listner: YouTubePlayer.OnInitializedListener =
        object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                returnState: Boolean
            ) {
                if (!returnState) {
                    player?.loadVideo(YOUTUBE_VIDEO_ID)
                } else {
                    player?.play()
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {
                if (result?.isUserRecoverableError == true) {
                    result.getErrorDialog(this@YoutubeActivity, DEBUG_RESPONCE_CODE).show()
                } else {
                    Toast.makeText(
                        this@YoutubeActivity,
                        "Failed to Initialize YoutubePlayer ${result.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(
            tag,
            "OnActivityResult called with responce code $requestCode and result code $resultCode"
        )
        if (requestCode == DEBUG_RESPONCE_CODE) {
            youTubePlayer.initialize(getString(R.string.GOOGLE_API_KEY), listner)
        }
    }

}
