package edu.temple.colorselectionapplication

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val COLORS_KEY = "colors_key"

class SelectionFragment : Fragment() {

    private var colors: Array<String>? = null
    private lateinit var colorViewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        colorViewModel = ViewModelProvider(requireActivity()).get(ColorViewModel::class.java)

        arguments?.let {
            colors = it.getStringArray(COLORS_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selection, container, false) as RecyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (view as RecyclerView) {
            colors?.run {

                val clickEvent = {
                    color:String -> colorViewModel.setSelectedColor(color)
                    (requireActivity() as SelectionFragmentInterface).colorSelected()
                }

                layoutManager = LinearLayoutManager(requireContext())
                adapter = ColorAdapter(this, clickEvent)
            }
        }
    }

    class ColorAdapter(_colors: Array<String>, _clickEvent: (String)->Unit) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>(){

        val colors = _colors
        val clickEvent = _clickEvent

        class ColorViewHolder(_view: View) : RecyclerView.ViewHolder(_view) {
            val view = _view
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
            return ColorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.color_list_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
            holder.view.setBackgroundColor(Color.parseColor(colors[position]))
            holder.view.setOnClickListener { clickEvent(colors[position]) }
        }

        override fun getItemCount(): Int {
            return colors.size
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(colors: Array<String>) =
            SelectionFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(COLORS_KEY, colors)
                }
            }
    }


    interface SelectionFragmentInterface {
        fun colorSelected()
    }
}