package com.putri.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.putri.cupcake.databinding.FragmentSummaryBinding
import com.putri.cupcake.model.OrderViewModel

// membuat class SummaryFragment
class SummaryFragment : Fragment() {

    // membuat binding yang objek instancenya sesuai dengan layout fragment_summary.xml
    // dengan nilai null
    private var binding: FragmentSummaryBinding? = null

    // menggunakan properti kotlin by activityViewModels
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // onCreateView berjalan saat membuat aktivitas
    // metode ini menerima savedInstanceState yang merupakan objek bundle yang berisi status
    // aktivitas yang sebelumnya disimpan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    // untuk mereferensikan fungsi di class induk
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    // membuat fungsi sendOrder yang nantinya akan mengirimkan detail pesanan jika menekan button
    // submit
    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    // membuat fungsi onDestroyView yang digunakan untuk menghapus atau membersihkan objek
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}