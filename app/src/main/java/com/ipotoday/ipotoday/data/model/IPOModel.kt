package com.ipotoday.ipotoday.data.model

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ipotoday.ipotoday.R
import java.util.*

@Entity(
    tableName = "ipo_model"
)
data class IPOModel(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "company_name") var companyName: String? = "",
    @ColumnInfo(name = "company_code") var companyCode: String? = "",
    @ColumnInfo(name = "company_description") var companyDescription: String? = "",
    @ColumnInfo(name = "market_type") var marketType: Int? = MarketType.KOSPI.ordinal,
    @ColumnInfo(name = "image") var image: String? = "",
    @ColumnInfo(name = "sales_amount") var salesAmount: String? = "",
    @ColumnInfo(name = "operating_profit") var operatingProfit: String? = "",
    @ColumnInfo(name = "net_income") var netIncome: String? = "",
    @ColumnInfo(name = "ipo_price") var ipoPrice: String? = "",
    @ColumnInfo(name = "ipo_amount") var ipoAmount: String? = "",
    @ColumnInfo(name = "estimate_ipo_price") var estimateIpoPrice: String? = "",
    @ColumnInfo(name = "estimate_ipo_amount") var estimateIpoAmount: String? = "",
    @ColumnInfo(name = "lead_manager") var leadManger: String? = "",
    @ColumnInfo(name = "demand_forecast_start_date") var demandForecastStartDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "demand_forecast_end_date") var demandForecastEndDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "apply_start_date") var applyStartDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "apply_end_date") var applyEndDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "refund_date") var refundDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "listing_date") var listingDate: Long? = Calendar.getInstance().time.time,
    @ColumnInfo(name = "competition_rate") var competitionRate: String? = "",
    @ColumnInfo(name = "institutional_competition_rate") var institutionalCompetitionRate: String? = "",
    @ColumnInfo(name = "lockup_rate") var lockupRate: String? = "",
    @ColumnInfo(name = "bookmark") var bookmark: Boolean? = false
) {

    enum class MarketType(
        @StringRes resId: Int
    ) {
        KOSPI(R.string.text_kospi),
        KOSDAQ(R.string.text_kosdaq),
        KONEX(R.string.text_konex),
        SPAC(R.string.text_spac)
    }

}