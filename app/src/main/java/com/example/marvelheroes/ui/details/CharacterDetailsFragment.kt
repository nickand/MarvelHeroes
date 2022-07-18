package com.example.marvelheroes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelheroes.R
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.Resource
import com.example.marvelheroes.databinding.FragmentCharacterDetailBinding
import com.example.marvelheroes.ui.details.adapter.CharacterDetailsAdapter
import com.example.marvelheroes.ui.details.adapter.model.AttributesItemView
import com.example.marvelheroes.ui.details.adapter.model.DetailsItemView
import com.example.marvelheroes.ui.details.adapter.model.PictureItemView
import com.example.marvelheroes.ui.main.BaseFragment
import com.example.marvelheroes.ui.main.CharacterViewModel
import com.google.android.material.snackbar.Snackbar

class CharacterDetailsFragment : BaseFragment() {

    private lateinit var characterDetailsAdapter: CharacterDetailsAdapter

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    //private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: CharacterViewModel by hiltNavGraphViewModels(
        R.id.nav_graph
    )
    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root // ---> Previous value: null
    }

    override fun clearBindings() {
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterDetailsAdapter = CharacterDetailsAdapter()


        fetchDetails()
        viewModel.product.observe(viewLifecycleOwner) {
            handleFetchResponse(it)
        }

        binding.toolbar.setTitle(R.string.product_detail_title)

        binding.detailsList.layoutManager = LinearLayoutManager(context)
        binding.detailsList.adapter = characterDetailsAdapter
    }

    private fun handleFetchResponse(resource: Resource<Character>?) {
        resource.let { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.loadingView.root.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    characterDetailsAdapter.items.clear()
                    resource.data?.let { product ->
                        characterDetailsAdapter.titleInfo =
                            DetailsItemView(title = product.name.orEmpty(),
                                permalink = product.resourceURI.orEmpty(),
                                currency = product.modified.orEmpty())

                        characterDetailsAdapter.pictures = PictureItemView(listOf(product.thumbnail?.path.orEmpty()))

                        /*
                       characterDetailsAdapter.attributes =
                            product.attributes?.map { AttributesItemView(it.name, it.value) }

                         */
                    }
                    println("///////////////////////////////////////////////////////////////////")
                    println(characterDetailsAdapter.titleInfo!!.title)
                    println(characterDetailsAdapter.titleInfo!!.permalink)
                    println(characterDetailsAdapter.titleInfo!!.currency)
                    binding.loadingView.root.visibility = View.GONE
                }
                else -> {
                    binding.loadingView.root.visibility = View.GONE
                    displayErrorMessage(
                        view = binding.loadingView.root,
                        message = requireContext().getString(R.string.error_fetch_failed),
                        duration = Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    private fun fetchDetails() {
        viewModel.getCharacterDetails(args.productId)
    }
}