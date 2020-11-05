package net.simplifiedcoding.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.data.network.AuthApi
import net.simplifiedcoding.data.network.Resource
import net.simplifiedcoding.data.repository.AuthRepository
import net.simplifiedcoding.databinding.FragmentLoginEmodBinding
import net.simplifiedcoding.ui.base.BaseFragment
import net.simplifiedcoding.ui.enable
import net.simplifiedcoding.ui.handleApiError
import net.simplifiedcoding.ui.home.HomeActivity
import net.simplifiedcoding.ui.startNewActivity
import net.simplifiedcoding.ui.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginEmodBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.loginButton.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        //  viewModel.saveAuthToken(it.value.user.access_token!!)
                        if (it.value.status.equals("success")) {
                            fun Context.toast(message: CharSequence) =
                                Toast.makeText(this, it.value.admission_date, Toast.LENGTH_SHORT).show()
                            requireActivity().startNewActivity(HomeActivity::class.java)
                        }
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.loginButton.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginEmodBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}