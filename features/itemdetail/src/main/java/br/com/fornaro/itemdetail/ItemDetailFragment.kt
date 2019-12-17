package br.com.fornaro.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fornaro.android.extensions.observe
import br.com.fornaro.android.extensions.toast
import kotlinx.android.synthetic.main.fragment_item_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class ItemDetailFragment : Fragment() {

    private val viewModel: ItemDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSaveButton()
        setupViewModel()
    }

    private fun setupSaveButton() = with(saveButton) {
        setOnClickListener {
            viewModel.save(
                titleText.text.toString(),
                descriptionText.text.toString()
            )
        }
    }

    private fun setupViewModel() = with(viewModel) {
        error.observe(viewLifecycleOwner) { handleError(it) }
    }

    private fun handleError(error: ItemDetailError) = when (error) {
        TitleAndDescriptionAreEmpty -> toast(R.string.error_save_item_detail_title_description_empty)
    }
}
