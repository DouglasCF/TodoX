package br.com.fornaro.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.android.extensions.observe
import br.com.fornaro.domain.model.TodoItem
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private val viewAdapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupErrorLayout()
    }

    private fun setupViewModel() = with(viewModel) {
        result.observe(viewLifecycleOwner) { updateData(it) }
        progress.observe(viewLifecycleOwner) { updateProgress(it) }
        error.observe(viewLifecycleOwner) { updateError(it) }
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = viewAdapter
    }

    private fun setupErrorLayout() {
        errorLayout.findViewById<TextView>(R.id.errorText).text =
            getString(R.string.error_loading_items)
        errorLayout.findViewById<ImageView>(R.id.errorImage)
            .setImageResource(R.drawable.ic_sentiment_dissatisfied_black)
        errorLayout.findViewById<Button>(R.id.errorButton).apply {
            text = getString(R.string.retry)
            setOnClickListener { viewModel.loadTodoItems() }
        }
    }

    private fun updateData(data: List<TodoItem>) {
        contentHome.isVisible = true
        viewAdapter.setData(data)
    }

    private fun updateError(enabled: Boolean) {
        errorLayout.isVisible = enabled
    }

    private fun updateProgress(progress: Boolean) {
        progressBar.isVisible = progress
    }
}
