package com.rentwiz.app.home.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rentwiz.app.BuildConfig
import com.rentwiz.app.databinding.FragmentCartBinding
import com.rentwiz.app.state.ResponseState
import com.rentwiz.app.state.UserState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val fooViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        //fooViewModel.getUserDetails()
        fooViewModel.getDetails()
        BuildConfig.BUILD_TYPE
    }

    private fun setUpObservers() {
        fooViewModel.cartSate.observe(viewLifecycleOwner) {
            handleResponse(it)
        }
    }

    private fun handleResponse(responseState: ResponseState<Nothing>) {
        when(responseState) {
            is UserState.DetailsSuccess -> {
                binding.cart.text = "Field Injection \n ${responseState.response}"
            }
            else -> {}
        }
    }
}