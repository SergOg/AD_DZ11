package ru.gb.dz11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gb.dz11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val textForSave = binding.editText.text
            repository.saveText(textForSave.toString(), this)
            val textFromRepo = repository.getText(this)
            binding.textView.text = textFromRepo
        }

        binding.clearButton.setOnClickListener {
            repository.clearText(this)
            binding.textView.text = repository.getText(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val textFromRepo = repository.getText(this)
        binding.textView.text = textFromRepo
    }
}