package example.wegotrip.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import example.wegotrip.Util.ListStagesAdapter
import example.wegotrip.models.Stage
import kotlinx.android.synthetic.main.fragment_list_stages.*
import androidx.recyclerview.widget.DividerItemDecoration
import android.annotation.SuppressLint
import example.wegotrip.R


class ListStagesFragment : Fragment(), ListStagesAdapter.onItemClickListener {

    val args: ListStagesFragmentArgs by navArgs()
    private lateinit var listStagesAdapter: ListStagesAdapter
    val listStages = ArrayList<Stage>()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(example.wegotrip.R.layout.fragment_list_stages, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        for (i in args.listStages.stages){
            listStages.add(i)
        }

        listStagesAdapter = ListStagesAdapter(listStages, this)
        recycler_view_stages.layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration =
            DividerItemDecoration(recycler_view_stages.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
        recycler_view_stages.addItemDecoration(itemDecoration)
        recycler_view_stages.adapter = listStagesAdapter
    }

    override fun onItemClick(position: Int) {
        val action = ListStagesFragmentDirections.actionListStagesFragmentToMainFragment(position)
        navController.navigate(action)
    }
}