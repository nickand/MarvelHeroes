package com.example.memefon.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memefon.data.model.Resource
import com.example.memefon.R
import com.example.memefon.databinding.FragmentCharacterListBinding
import com.example.memefon.domain.MemefonModel
import com.example.memefon.ui.main.adapter.MemefonListAdapter
import com.google.android.material.snackbar.Snackbar

/**
 * Fragment for searching and displaying results in a list.
 */
class CharacterListFragment : BaseFragment() {

    private lateinit var navController: NavController
    private lateinit var memefonListAdapter: MemefonListAdapter

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val memefonViewModel: MemefonViewModel by hiltNavGraphViewModels(
        R.id.nav_graph
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        memefonListAdapter = MemefonListAdapter {
            val action =
                CharacterListFragmentDirections.actionMainFragmentToDetailFragment(it.id)
            navController.navigate(action)
        }

        //Toolbar setup
        binding.toolbar.inflateMenu(R.menu.main_fragment)
        binding.toolbar.setTitle(R.string.app_name)

        //RecyclerView setup
        binding.charactersList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.charactersList.adapter = memefonListAdapter

        memefonViewModel.getMemefons()

        memefonViewModel.memefonList.observe(viewLifecycleOwner) {
            handleFetchResponse(it)
        }

        showEmptySearch(true)
    }

    private fun handleFetchResponse(memefonList: Resource<List<MemefonModel>>) {
        when (memefonList) {
            is Resource.Loading -> {
                showEmptySearch(false)
                binding.loadingView.root.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                showEmptySearch(false)
                memefonListAdapter.memefonList = memefonList.data
                binding.loadingView.root.visibility = View.GONE
            }
            else -> {
                showEmptySearch(true)
                binding.loadingView.root.visibility = View.GONE
                displayErrorMessage(
                    view = binding.loadingView.root,
                    message = requireContext().getString(R.string.error_fetch_failed),
                    duration = Snackbar.LENGTH_LONG
                )
            }
        }
    }

    override fun clearBindings() {
        _binding = null
    }

    private fun showEmptySearch(isShow: Boolean) {
        if (isShow) {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            binding.emptyMessage.visibility = View.VISIBLE
            binding.animationView.alpha = 0f
            binding.animationView.visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            binding.animationView.animate().alpha(1f).duration = 600
            binding.animationView.setAnimation("json/search_empty.json")
            binding.animationView.repeatCount = Animation.INFINITE
            binding.animationView.playAnimation()
        } else {
            // Animate the loading view to 0% opacity. After the animation ends,
            // set its visibility to GONE as an optimization step (it won't
            // participate in layout passes, etc.)
            binding.emptyMessage.visibility = View.GONE
            binding.animationView.animate()
                .alpha(0f)
                .setDuration(600)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        binding.animationView.visibility = View.GONE
                    }
                })
        }
    }
}
