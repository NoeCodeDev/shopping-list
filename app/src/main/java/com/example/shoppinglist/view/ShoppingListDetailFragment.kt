package com.example.shoppinglist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentShoppingListDetailBinding
import com.example.shoppinglist.viewmodel.ShoppingListDetailViewModel

class ShoppingListDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoppingListDetailBinding
    private val viewModel: ShoppingListDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShoppingListDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        arguments?.let { bundle ->
            val args = ShoppingListDetailFragmentArgs.fromBundle(bundle)
            args.shoppingList?.let { viewModel.setup(it) }
        }

        setListeners()
    }

    private fun setListeners() {
        binding.addItemButton.setOnClickListener {
          //  val action =
        }

    }

    private fun observeViewModel() {
        // TODO place here the viewmodel's observers
    }
}