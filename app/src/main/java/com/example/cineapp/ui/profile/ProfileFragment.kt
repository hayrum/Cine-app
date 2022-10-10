package com.example.cineapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.cineapp.BuildConfig
import com.example.cineapp.R
import com.example.cineapp.databinding.FragmentProfileBinding
import com.example.cineapp.services.Status
import com.example.cineapp.services.objects.account.Account
import com.example.cineapp.services.realmDB.RealmConfig
import com.example.cineapp.utils.ConnectivityUtils
import com.google.android.material.snackbar.Snackbar
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.SingleQueryChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

/**
 * @author Hayrum Vega
 * @since 08/10/2022
 * @description This fragment class is the view of the detail of the profile.
 */
class ProfileFragment : Fragment() {

    // Binding
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ViewModel
    private lateinit var profileViewModel: ProfileViewModel

    // realmDB
    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Init viewModel's
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeVariables()
    }

    /**
     * Initialize values of the variables.
     */
    private fun initializeVariables() {
        // Open connection Realm
        realm = RealmConfig.getInstanceRealm()
        profileViewModel = ViewModelProvider(this@ProfileFragment)[ProfileViewModel::class.java]
        if (ConnectivityUtils.isNetworkAvailable(requireContext())) {
            getInfoOfProfile()
        } else {
//            loadInfoOfProfile()
        }
    }

    /**
     * Create petition for information of the account from API
     */
    private fun getInfoOfProfile() {
        profileViewModel.requestGetProfile()
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
//                            CoroutineScope(Dispatchers.IO).launch {
                                /*  realm.write {
                                        copyToRealm(it.data)
                                    }
                                */
                                loadInfoOfProfile(it.data)
//                            }
                        }
                    }
                    Status.ERROR -> {
                        Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_LONG).show()
                    }
                    else -> {}
                }
            }
    }

    private fun loadInfoOfProfile(account: Account) {
//        val accountFlow = realm.query(Account::class).first()
//            val account = accountFlow.asFlow()
//            account.collect { changes: SingleQueryChange<Account> ->
        binding.mtvUsername.text = account.name ?: ""
        Glide.with(requireContext())
            .load(BuildConfig.URL_IMAGES + account.avatar?.avatarUrl?.avatarPath)
            .error(R.drawable.ic_photo_account_default)
            .into(binding.sivProfile)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}