package com.cibertec.session10app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cibertec.session10app.databinding.ActivityMainBinding
import com.cibertec.session10app.model.RegisterPetRequest
import com.cibertec.session10app.model.UpdatePetRequest
import com.cibertec.session10app.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.btnList.setOnClickListener {
            binding.tvResult.text=""
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    binding.progressBar.visibility = View.VISIBLE
                    val response =withContext(Dispatchers.IO){
                        Api.build().getAllPets()
                    }
                    if(response.isSuccessful){
                        val pets = response.body()
                        pets?.let { pet ->
                        pet.forEach {
                            binding.tvResult.append("${it.code} ${it.customer} ${it.petName} ${it.age}")
                            binding.tvResult.append("\n")
                        }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                catch (ex:IOException){
                    Toast.makeText(this@MainActivity, "Revisa tu conexion a internet", Toast.LENGTH_SHORT).show()
                }
                catch (ex:Exception){
                    Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
                finally {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        binding.btnRegister.setOnClickListener {
            val customer = binding.edtCustomer.text.toString()
            val petName=binding.edtPet.text.toString()
            val age = binding.edtAge.text.toString().toInt()
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    binding.progressBar.visibility = View.VISIBLE
                    val response =withContext(Dispatchers.IO){
                        Api.build().savePets(request = RegisterPetRequest(customer,petName,age))
                    }
                    if(response.isSuccessful){
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@MainActivity, it.result, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                catch (ex:IOException){
                    Toast.makeText(this@MainActivity, "Revisa tu conexion a internet", Toast.LENGTH_SHORT).show()
                }
                catch (ex:Exception){
                    Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
                finally {
                    binding.progressBar.visibility = View.GONE
                    binding.edtCustomer.setText("")
                    binding.edtAge.setText("")
                    binding.edtPet.setText("")
                }
            }
        }

        binding.btnUpdate.setOnClickListener {
            val customer = binding.edtCustomer.text.toString()
            val petName=binding.edtPet.text.toString()
            val age = binding.edtAge.text.toString().toInt()
            val codigo = 42
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    binding.progressBar.visibility = View.VISIBLE
                    val response =withContext(Dispatchers.IO){
                        Api.build().updatePets(request = UpdatePetRequest(codigo,customer,petName,age))
                    }
                    if(response.isSuccessful){
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@MainActivity, it.result, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                catch (ex:IOException){
                    Toast.makeText(this@MainActivity, "Revisa tu conexion a internet", Toast.LENGTH_SHORT).show()
                }
                catch (ex:Exception){
                    Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
                finally {
                    binding.progressBar.visibility = View.GONE
                    binding.edtCustomer.setText("")
                    binding.edtAge.setText("")
                    binding.edtPet.setText("")
                }
            }
        }
        binding.btnDelete.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    binding.progressBar.visibility = View.VISIBLE
                    val response =withContext(Dispatchers.IO){
                        Api.build().deletePets(42)
                    }
                    if(response.isSuccessful){
                        val resultBody = response.body()
                        resultBody?.let {
                            Toast.makeText(this@MainActivity, it.result, Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
                catch (ex:IOException){
                    Toast.makeText(this@MainActivity, "Revisa tu conexion a internet", Toast.LENGTH_SHORT).show()
                }
                catch (ex:Exception){
                    Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_SHORT).show()
                }
                finally {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}