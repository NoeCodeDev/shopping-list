package com.example.shoppinglist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentShoppingListsBinding
import com.example.shoppinglist.viewmodel.ShoppingListsViewModel

class ShoppingListsFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListsBinding
    private val viewModel: ShoppingListsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.setup()
    }

    private fun observeViewModel() {
        // TODO place the viewmodel's observers here
    }
}