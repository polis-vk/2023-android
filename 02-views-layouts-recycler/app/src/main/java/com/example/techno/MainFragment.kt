package com.example.techno

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techno.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val _binding: FragmentMainBinding
        get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(_binding) {
            val movies = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelableArrayList(LIST_MOVIES_KEY, Movie::class.java)!!
            } else {
                arguments?.getParcelableArrayList(LIST_MOVIES_KEY)!!
            }
            val mainAdapter = MainAdapter(movies)
            rv.adapter = mainAdapter
            rv.layoutManager = LinearLayoutManager(rv.context)
            fb.setOnClickListener {
                mainAdapter.addMovie(
                    Movie(
                        0,
                        "Новый титл",
                        "Новое описание"
                    )
                )
            }
            fb.setOnLongClickListener {
                activity?.supportFragmentManager?.let {
                    it.setFragmentResult(
                        MAIN_FRAGMENT_RESULT_KEY,
                        Bundle().apply {
                            putString(MAIN_FRAGMENT_RESULT_TEXT_KEY, "Main Fragment is done")
                        }
                    )
                    it.beginTransaction().remove(this@MainFragment).commit()
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}