package com.putri.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.putri.cupcake.databinding.FragmentStartBinding
import com.putri.cupcake.model.OrderViewModel

// membuat class StartFragment
class StartFragment : Fragment() {

    // membuat binding yang objek instancenya sesuai dengan layout fragment_start.xml.xml
    // dengan nilai null
    private var binding: FragmentStartBinding? = null

    // menggunakan properti kotlin by activityViewModels
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // onCreateView berjalan saat membuat aktivitas
    // metode ini menerima savedInstanceState yang merupakan objek bundle yang berisi status
    // aktivitas yang sebelumnya disimpan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    // untuk mereferensikan fungsi di class induk
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    // membuat fungsi untuk order cupcake
    fun orderCupcake(quantity: Int) {
        // memperbarui model tampilan dengan kuantitas
        sharedViewModel.setQuantity(quantity)

        // menetapkan rasa vanila sebagai rasa default
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // navigasi ke fragment selanjutnya untuk memilih rasa cupcakes
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    // membuat fungsi onDestroyView yang digunakan untuk menghapus atau membersihkan objek
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}