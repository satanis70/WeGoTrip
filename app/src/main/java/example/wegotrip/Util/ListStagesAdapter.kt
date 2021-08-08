package example.wegotrip.Util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import example.wegotrip.R
import example.wegotrip.models.Excursion
import example.wegotrip.models.Stage

class ListStagesAdapter(private val list: List<Stage>, private val listener: onItemClickListener)  : RecyclerView.Adapter<ListStagesAdapter.StagesViewHolder>(){

    inner class StagesViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        private val stagesName = view.findViewById<TextView>(R.id.text_view_stage_list)

        init {
            view.setOnClickListener (this)
        }

        override fun onClick(v: View?) {
        }

        fun bind(name: String){
            stagesName.text = name
            stagesName.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StagesViewHolder {
        return StagesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stages_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: StagesViewHolder, position: Int) {
        holder.bind(list[position].nameStages)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}