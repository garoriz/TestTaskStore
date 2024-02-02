package com.garif.teststore.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.garif.core.util.AppViewModelFactory
import com.garif.core.util.isValidName
import com.garif.core.util.isValidPhoneNumber
import com.garif.database.model.User
import com.garif.teststore.MainActivityWithMenu
import com.garif.teststore.R
import com.garif.teststore.databinding.FragmentRegistrationBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as com.garif.teststore.di.RegistrationFeatureComponentProvider)
            .getRegistrationFeatureComponent()
            .injectRegistrationFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegistrationBinding.bind(view)

        initObservers()

        viewModel.onGetUser()

        with(binding) {
            setListeners(tilName, tietName, com.garif.core.R.string.name)
            setListeners(tilSurname, tietSurname, com.garif.registration_feature.R.string.surname)
            tietPhoneNumber.doOnTextChanged { text, _, _, _ ->
                if (text != null) {
                    if (text.isNotEmpty()) {
                        tilPhoneNumber.hint = ""
                        tilPhoneNumber.endIconDrawable = ContextCompat.getDrawable(
                            requireContext(),
                            com.garif.core.R.drawable.cross
                        )
                    } else {
                        tilPhoneNumber.endIconDrawable = null
                    }
                }
                checkFields()
            }
            tilPhoneNumber.setEndIconOnClickListener {
                tilPhoneNumber.editText?.setText("")
            }

            btnSignIn.setOnClickListener {
                viewModel.onSaveUser(
                    User(
                        tilName.editText?.text.toString(),
                        tilSurname.editText?.text.toString(),
                        tilPhoneNumber.editText?.text.toString()
                    )
                )
                val intent = Intent(requireActivity(), MainActivityWithMenu::class.java)
                intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                if (it != null) {
                    val intent = Intent(requireActivity(), MainActivityWithMenu::class.java)
                    intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra(getString(com.garif.core.R.string.is_load_catalog), true)
                    startActivity(intent)
                } else {
                    binding.fragment.isVisible = true
                }
            }, onFailure = {
                binding.fragment.isVisible = true
                Log.e("e", it.message.toString())
            })
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Log.e("e", it.message.toString())
        }
    }

    private fun setListeners(til: TextInputLayout, tiet: TextInputEditText, stringId: Int) {
        tiet.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) til.hint = "" else {
                if (til.editText?.text?.isNotEmpty() == true) {
                    til.hint = ""
                } else {
                    til.hint = getString(stringId)
                }
            }
        }
        tiet.doOnTextChanged { text, _, _, _ ->
            if (!text.toString().isValidName())
                til.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.pale_red)
            else
                til.boxBackgroundColor =
                    ContextCompat.getColor(requireContext(), com.garif.core.R.color.light_grey)
            if (text != null) {
                if (text.isNotEmpty()) {
                    til.endIconDrawable = ContextCompat.getDrawable(
                        requireContext(),
                        com.garif.core.R.drawable.cross
                    )
                } else {
                    til.endIconDrawable = null
                }
            }
            checkFields()
        }
        til.setEndIconOnClickListener {
            til.editText?.setText("")
            til.hint = getString(stringId)
        }
    }

    private fun checkFields() {
        with(binding) {
            if (tilName.editText?.text.toString()
                    .isValidName() && tilSurname.editText?.text.toString()
                    .isValidName() && tilPhoneNumber.editText?.text.toString().isValidPhoneNumber()
            ) {
                btnSignIn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), com.garif.core.R.color.pink)
                btnSignIn.isClickable = true
            } else {
                btnSignIn.backgroundTintList =
                    ContextCompat.getColorStateList(
                        requireContext(),
                        com.garif.core.R.color.light_pink
                    )
                btnSignIn.isClickable = false
            }
        }
    }
}