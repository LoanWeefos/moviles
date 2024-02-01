package com.example.form_233267

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.example.form_233267.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        configSpinner()

        binding.submitButton.setOnClickListener { submitForm() }
    }

    private fun configSpinner(){
        val spinner: Spinner = findViewById(R.id.genreSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.genres_array,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.prompt = getString(R.string.seleccioneungenero)
        }
    }

    private fun submitForm()
    {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.phoneContainer.helperText = validPhone()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null

        if (validEmail && validPassword && validPhone)
            resetForm()
        else
            invalidForm()
    }

    private fun invalidForm()
    {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nCorreo electrónico: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nContraseña: " + binding.passwordContainer.helperText
        if(binding.phoneContainer.helperText != null)
            message += "\n\nTeléfono: " + binding.phoneContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Form inválido")
            .setMessage(message)
            .setPositiveButton("Aceptar"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        val spinner: Spinner = findViewById(R.id.genreSpinner)
        val dialogView = layoutInflater.inflate(R.layout.imagen, null)
        var message = "Correo electrónico: " + binding.emailEditText.text
        message += "\nContraseña: " + binding.passwordEditText.text
        message += "\nTeléfono: " + binding.phoneEditText.text
        message += "\nGénero: " + spinner.selectedItem
        AlertDialog.Builder(this)
            .setTitle("Formulario guardado")
            .setMessage(message)
            .setPositiveButton("Aceptar"){ _,_ ->
                binding.emailEditText.text = null
                binding.passwordEditText.text = null
                binding.phoneEditText.text = null

                binding.emailContainer.helperText = getString(R.string.required)
                binding.passwordContainer.helperText = getString(R.string.required)
                binding.phoneContainer.helperText = getString(R.string.required)
            }
            .setView(dialogView)
            .show()
    }

    private fun emailFocusListener()
    {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Dirección de correo electrónico inválido"
        }
        return null
    }

    private fun passwordFocusListener()
    {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {
        val passwordText = binding.passwordEditText.text.toString()
        if(passwordText.length < 8)
        {
            return "Mínimo contraseña de 8 caracteres"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Debe contener 1 letra mayúscula"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Debe contener 1 letra minúscula"
        }
        if(!passwordText.matches(".*[@#\$%^&+=.!¡].*".toRegex()))
        {
            return "Debe contener 1 caracter especial (@#\$%^&+=.!¡)"
        }
        if (passwordText.contains("\\s".toRegex())) {
            return "No se permiten espacios"
        }

        return null
    }

    private fun phoneFocusListener()
    {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String?
    {
        val phoneText = binding.phoneEditText.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 10)
        {
            return "Must be 10 Digits"
        }
        return null
    }
}