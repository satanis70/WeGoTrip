package example.wegotrip.view

import android.media.MediaPlayer
import example.wegotrip.models.Excursions
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : MvpView {
    fun onDataCompleteFromApi(excursions: Excursions)
    fun onDataErrorFromApi(message: String)
    fun playMusic(mediaPlayer: MediaPlayer)
}