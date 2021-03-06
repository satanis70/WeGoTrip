package example.wegotrip.presenters

import android.media.AudioAttributes
import android.media.MediaPlayer
import example.wegotrip.models.Excursions
import example.wegotrip.models.Player
import example.wegotrip.services.RetrofitService
import example.wegotrip.view.MainView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope

@InjectViewState
class MainFragmentPresenter : MvpPresenter<MainView>() {
    lateinit var currentMediaPlayer: MediaPlayer
    suspend fun requestApi(){
        presenterScope.launch {
            val retrofit = RetrofitService.create().getStages()
            if (retrofit.isSuccessful){
                retrofit.body()?.let {
                    viewState.onDataCompleteFromApi(it)
                }

                currentMediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                }

                viewState.playMusic(Player(currentMediaPlayer))
            } else {
                viewState.onDataErrorFromApi(retrofit.message())
            }
        }
    }
}