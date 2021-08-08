package example.wegotrip.ui

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomappbar.BottomAppBar
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import example.wegotrip.R
import example.wegotrip.Util.ListStagesAdapter
import example.wegotrip.Util.MainPagerAdapter
import example.wegotrip.models.Excursion
import example.wegotrip.models.Excursions
import example.wegotrip.models.Stage
import example.wegotrip.presenters.MainFragmentPresenter
import example.wegotrip.view.MainView
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MainFragment : MvpAppCompatFragment(), MainView  {

    @InjectPresenter
    lateinit var mainFragmentPresenter: MainFragmentPresenter
    val args: MainFragmentArgs by navArgs()
    private lateinit var mainPagerAdapter: MainPagerAdapter
    val listImageUri = ArrayList<Uri>()
    var numberStage : Int? = null
    var urlMusic = String()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listImageUri.clear()
        navController = Navigation.findNavController(view)
        CoroutineScope(Dispatchers.IO).launch {
            mainFragmentPresenter.requestApi()
        }

        numberStage = args.stageNumber

    }

    override fun onDataCompleteFromApi(excursions: Excursions) {
        urlMusic = excursions.excursion[0].stages[numberStage!!].audio
        for (i in excursions.excursion[0].stages[numberStage!!].images){
            listImageUri.add(i.toUri())
        }
        mainPagerAdapter = MainPagerAdapter(listImageUri)
        view_pager_image.adapter = mainPagerAdapter
        textView_name_stage.text = excursions.excursion[0].stages[numberStage!!].nameStages
        text_view_stage_text.text = excursions.excursion[0].stages[numberStage!!].text
        imageButton_dehaze.setOnClickListener {
            if (slide_up.panelState == SlidingUpPanelLayout.PanelState.EXPANDED){
                slide_up.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                imageButton_dehaze.setImageDrawable(resources.getDrawable(R.drawable.i))
            } else{
                slide_up.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                imageButton_dehaze.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_arrow_downward_24))
            }
        }


        imageButton_list_stages.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToListStagesFragment(excursions.excursion[0])
            navController.navigate(action)
        }
    }

    override fun onDataErrorFromApi(message: String) {
        Log.i("TagError", message)
        Toast.makeText(requireContext(), R.string.error_api, Toast.LENGTH_LONG).show()
    }

    override fun playMusic(mediaPlayer: MediaPlayer) {
        mediaPlayer.setDataSource(urlMusic)
        mediaPlayer.prepare()
        imageButton_play.setOnClickListener {
            mediaPlayer.apply {
                if (isPlaying){
                    pause()
                    imageButton_play.setImageDrawable(resources.getDrawable(R.drawable.play))
                } else {
                    start()
                    imageButton_play.setImageDrawable(resources.getDrawable(R.drawable.paus))
                }
            }
        }
        imageButton_replay_back.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition-5000)
        }
        imageButton_flash_forward.setOnClickListener {
            mediaPlayer.seekTo(mediaPlayer.currentPosition+5000)
        }
    }

}