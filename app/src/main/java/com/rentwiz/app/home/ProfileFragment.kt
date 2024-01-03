package com.rentwiz.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rentwiz.app.coreui.utils.Utils
import com.rentwiz.app.databinding.FragmentProfileBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBlocking {
            binding.profile.append("\nMain program starts: ${Thread.currentThread().name}")

            val msgOne: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageOne() }
            val msgTwo: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessageTwo() }
                binding.profile.append("\nThe entire message: ${msgOne.await()} ${msgTwo.await()}")

            binding.profile.append("\nMain program ends: ${Thread.currentThread().name}")
        }
    }

    private suspend fun getMessageOne(): String {
        delay(1000L)
        binding.profile.append("\nAfter working in getMessageOne()")
        return "Hello"
    }

    private suspend fun getMessageTwo(): String {
        delay(1000L)
        binding.profile.append("\nAfter working in getMessageTwo()")
        return "World!"
    }
}