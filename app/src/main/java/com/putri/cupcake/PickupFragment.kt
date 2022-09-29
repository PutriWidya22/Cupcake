package com.putri.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.putri.cupcake.databinding.FragmentPickupBinding
import com.putri.cupcake.model.OrderViewModel

// membuat class PickupFragment
class PickupFragment : Fragment() {

    // membuat binding yang objek instancenya sesuai dengan layout fragment_pickup.xml
    // dengan nilai null
    private var binding: FragmentPickupBinding? = null

    // menggunakan properti kotlin by activityViewModels
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // onCreateView berjalan saat membuat aktivitas
    // metode ini menerima savedInstanceState yang merupakan objek bundle yang berisi status
    // aktivitas yang sebelumnya disimpan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    // untuk mereferensikan fungsi di class induk
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    // membuat fungsi goToNextScreen untuk berpindah ke layar berikutnya
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    // membuat fungsi onDestroyView yang digunakan untuk menghapus atau membersihkan objek
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}