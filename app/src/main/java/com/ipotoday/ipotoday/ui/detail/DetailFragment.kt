package com.ipotoday.ipotoday.ui.detail

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.ipotoday.ipotoday.R
import com.ipotoday.ipotoday.data.model.IPOModel
import com.ipotoday.ipotoday.databinding.FragmentDetailBinding
import com.ipotoday.ipotoday.databinding.FragmentDetailBindingImpl
import com.ipotoday.ipotoday.databinding.FragmentMainBinding
import com.ipotoday.ipotoday.ui.base.BaseFragment
import com.ipotoday.ipotoday.utils.DateUtils
import com.ipotoday.ipotoday.utils.SelectAlarmType
import com.ipotoday.ipotoday.utils.autoCleared
import com.ipotoday.ipotoday.utils.extensions.shortToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

//    override fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ): FragmentDetailBinding {
//        return FragmentDetailBinding.inflate(inflater, container, false)
//    }
    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    private var binding: FragmentDetailBinding by autoCleared()

    private val selectAlarmBottomSheet by lazy { SelectAlarmBottomSheet() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "공모정보"
        binding.toolbar.inflateMenu(R.menu.menu_detail)

        val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        NavigationUI.setupActionBarWithNavController(requireActivity() as AppCompatActivity, navController)

        val passedId = args.id
        viewModel.getIPOModelById(passedId).observe(viewLifecycleOwner) { ipoModel ->
            Timber.d("DEBUG: $ipoModel")
            setupUI(ipoModel)
        }

//        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        binding.toolbar.setNavigationOnClickListener {
//            findNavController().navigateUp()
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                findNavController().navigateUp()
//            }
//        })



        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_alarm -> {
                    showAlarmBottomSheet()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupUI(ipoModel: IPOModel) {
        with(binding) {
            // TODO: Extension으로 빼기
            Glide.with(requireActivity()).load(ipoModel.image).into(imgLogo)
            txtCompanyName.text = ipoModel.companyName
            txtCompanyDescription.text = ipoModel.companyDescription

            txtDemandForecastDate.text = "${DateUtils.parseTime(ipoModel.demandForecastStartDate!!)} " +
                    "~ ${DateUtils.parseTime(ipoModel.demandForecastEndDate!!)}"
            txtEstimateIpoPrice.text = ipoModel.estimateIpoPrice
            txtEstimateIpoAmount.text = ipoModel.estimateIpoAmount

            txtIpoPrice.text = ipoModel.ipoPrice
            txtIpoAmount.text = ipoModel.ipoAmount
            txtApplyDate.text = "${DateUtils.parseTime(ipoModel.applyStartDate!!)} " +
                    "~ ${DateUtils.parseTime(ipoModel.applyEndDate!!)}"
            txtRefundDate.text = "${DateUtils.parseTime(ipoModel.refundDate!!)}"
            txtListingDate.text = "${DateUtils.parseTime(ipoModel.listingDate!!)}"
            txtLeadManager.text = ipoModel.leadManger
            txtInstitutionalCompetitionRate.text = ipoModel.institutionalCompetitionRate
            txtCompetitionRate.text = ipoModel.competitionRate

            txtSalesAmount.text = ipoModel.salesAmount
            txtOperatingProfit.text = ipoModel.operatingProfit
            txtNetIncome.text = ipoModel.netIncome
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_alarm -> {
                showAlarmBottomSheet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAlarmBottomSheet() {
        findNavController().navigate(R.id.action_detailFragment_to_selectAlarmBottomSheet)
/*        selectAlarmBottomSheet.isCancelable = true
        selectAlarmBottomSheet.show(childFragmentManager, SelectAlarmBottomSheet.TAG)
        selectAlarmBottomSheet.setOnSelectAlarmListener(object : SelectAlarmBottomSheet.SelectAlarmListener {
            override fun onSelected(type: SelectAlarmType) {
                when (type) {
                    SelectAlarmType.CUSTOM_ALARM -> {
                        Timber.d("DEBUG: CUSTOM_ALARM")
                    }
                    SelectAlarmType.GOOGLE_CALENDAR -> {
                        Timber.d("DEBUG: GOOGLE_CALENDAR")
                    }
                }
            }
        })*/
    }

    companion object {

    }

}