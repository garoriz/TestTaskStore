package com.garif.personal_profile_feature.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.garif.core.navigation.navigate
import com.garif.core.util.AppViewModelFactory
import com.garif.personal_profile_feature.R
import com.garif.personal_profile_feature.databinding.FragmentPersonalProfileBinding
import com.garif.personal_profile_feature.di.PersonalProfileFeatureComponentProvider
import javax.inject.Inject

class PersonalProfileFragment : Fragment(R.layout.fragment_personal_profile) {
    @Inject
    lateinit var factory: AppViewModelFactory
    private lateinit var binding: FragmentPersonalProfileBinding
    private val viewModel: PersonalProfileViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as PersonalProfileFeatureComponentProvider)
            .getPersonalProfileFeatureComponent()
            .injectPersonalProfileFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPersonalProfileBinding.bind(view)

        initObservers()

        viewModel.onGetUser()
        viewModel.onGetLikedItem()

        with(binding) {
            cvFavorites.setOnClickListener {
                navigate(R.id.action_navigation_profile_to_favoritesFragment)
            }

            btnExit.setOnClickListener {
                viewModel.onDeleteAllInDb()
                val intent = Intent()
                intent.setClassName(requireActivity(), "com.garif.teststore.MainActivity")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                with(binding) {
                    val name = "${it?.name} ${it?.surname}"
                    tvName.text = name

                    tvPhoneNumber.text = it?.phoneNumber
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }

        viewModel.likedItems.observe(viewLifecycleOwner) { it ->
            it.fold(onSuccess = {
                with(binding) {
                    val itemCount = it.size
                    if (itemCount == 0)
                        tvFavoritesCount.isVisible = false
                    else {
                        val productCount =
                            "$itemCount ${
                                resources.getQuantityString(
                                    R.plurals.product,
                                    itemCount
                                )
                            }"
                        tvFavoritesCount.text = productCount
                    }
                }
            }, onFailure = {
                Log.e("e", it.message.toString())
            })
        }


        viewModel.error.observe(viewLifecycleOwner) {
            Log.e("e", it.message.toString())
        }
    }
}