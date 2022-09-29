package com.putri.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.putri.cupcake.model.OrderViewModel
import com.putri.cupcake.databinding.FragmentFlavorBinding

// membuat class FlavorFragment
class FlavorFragment : Fragment() {

    // membuat binding yang objek instancenya sesuai dengan layout fragment_flavor.xml
    // dengan nilai null
    private var binding: FragmentFlavorBinding? = null

    // menggunakan properti kotlin by activityViewModels
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // onCreateView berjalan saat membuat aktivitas
    // metode ini menerima savedInstanceState yang merupakan objek bundle yang berisi status
    // aktivitas yang sebelumnya disimpan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    // untuk mereferensikan fungsi di class induk
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // menentukan fragment LifecycleOwner
            lifecycleOwner = viewLifecycleOwner

            // menetapkan viewModelnya dengan sharedViewModel
            viewModel = sharedViewModel

            // menetapkan fragment flavorFragment
            flavorFragment = this@FlavorFragment
        }
    }

    // membuat fungsi goToNextScreen untuk berpindah ke layar berikutnya
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    // membuat fungsi onDestroyView yang digunakan untuk menghapus atau membersihkan objek
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}