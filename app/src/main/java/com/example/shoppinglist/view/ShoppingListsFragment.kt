package com.example.shoppinglist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.node.getOrAddAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentShoppingListsBinding
import com.example.shoppinglist.model.ShoppingList
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
        setListeners()

        viewModel.setup()
    }

    private fun setListeners() {
        binding.addButton.setOnClickListener {
            val action = ShoppingListsFragmentDirections.actionShoppingListsFragmentToShoppingListDetailFragment(null)
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingSpinner.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.shoppingLists.observe(viewLifecycleOwner) {
            renderCards(it)
        }
    }

    private fun renderCards(lists: List<ShoppingList>) {
        lists.forEach { shoppingList ->
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.item_card, null, false)
            val textView = view.findViewById<TextView>(R.id.cardMainContent)
            textView.text = shoppingList.name
            textView.setOnClickListener {
                val action = ShoppingListsFragmentDirections.actionShoppingListsFragmentToShoppingListDetailFragment(shoppingList)
                findNavController().navigate(R.id.action_ShoppingListsFragment_to_ShoppingListDetailFragment)
            }
            binding.listContainer.addView(view)
        }
    }
}