package com.putri.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// menetapkan harga untuk satu cupcake yaitu 2.00
private const val PRICE_PER_CUPCAKE = 2.00

// menetapkan biaya tambahan untuk pengambilan pesanan di hari yang sama yaitu 3.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

// membuat class OrderViewModel untuk menyimpan informasi mengenai pesanan cupcake
// seperti kuantitas, rasa, dan tanggal pengambilan.
// dan menghitung total harga
class OrderViewModel : ViewModel() {

    // deklarasy kuantitas atau jumlah kue yang dibeli
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // deklarasi rasa cupcake
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // untuk mengatur opsi tanggal
    val dateOptions: List<String> = getPickupOptions()

    // deklarasi tanggal pengambilan
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // mengatur harga pesanan cupcake
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        // format harga ke dalam mata uang lokal dan dikembalikan ke LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // menetapkan nilai awal pesanan cupcake
        resetOrder()
    }

    // mengatur jumlah pesanan cupcakes
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    // mengatur rasa pesanan cupcake, yang hanya bisa memilih satu rasa untuk seluruh pesanan
    // menggunakan tipe data strng
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // untuk menetapkan tanggal pengambilan pesanan
    // menggunakan tipe data String
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    // mengembalikan nilai true jika rasa belum dipilih untuk pesanan
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    // mengatur ulang tampilan pesanan dengan menggunakan nilai default untuk kuantitas, rasa,
    // tanggal dan harga
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    // untuk memperbarui harga berdasarkan detail pesanan cupcake
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    // mengembalikan daftar opsi tanggal saat pemesanan dan 3 tanggal berikutnya yang terletk pada
    // repeat(tmies: 4)
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}