package com.ipotoday.ipotoday.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ipotoday.ipotoday.data.local.IPOLocalDao
import com.ipotoday.ipotoday.data.local.IPOLocalDatabase
import com.ipotoday.ipotoday.data.local.repository.HomeRepository
import com.ipotoday.ipotoday.utils.DATABASE_NAME
import com.ipotoday.ipotoday.utils.SessionManager
import com.ipotoday.ipotoday.utils.extensions.toMillis
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context)

    @Singleton
    @Provides
    fun provideHomeRepository(homeDao: IPOLocalDao) = HomeRepository(homeDao)

    @Singleton
    @Provides
    fun provideIPOLocalDao(ipoLocalDatabase: IPOLocalDatabase) = ipoLocalDatabase.ipoLocalDao

    @Singleton
    @Provides
    fun provideIPOLocalDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, IPOLocalDatabase::class.java, DATABASE_NAME)
        .addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)


                //TODO: 입력방법 정하기
                //TODO: 증권회사별 배정수량 알려주기?
                //TODO: 재무제표 매출액, 영업이익, 순이익 -> 이 구조가 아닐때?
                db.execSQL("""
                    INSERT INTO ipo_model (id, company_name, company_code, company_description, market_type, 
                    image, sales_amount, operating_profit, net_income, ipo_price, ipo_amount, estimate_ipo_price, estimate_ipo_amount, 
                    lead_manager, demand_forecast_start_date, demand_forecast_end_date,
                    apply_start_date, apply_end_date, refund_date, listing_date, 
                    competition_rate, institutional_competition_rate, lockup_rate, bookmark) 
                    VALUES(1, '에스디바이오센서', '137310', '의료용 기기 제조업', 0, 'http://www.sdbiosensor.com/d_html/front/images/logo_en.png', 
                    '1,179,116,264,970', '576,314,729,367', '437,546,860,320', '6,500원', '780억원', '45,000원 ~ 52,000원', '5,599억원 ~ 6,470억원', 
                    'NH투자증권, 한국투자증권, 삼성증권, KB증권', 
                    '${LocalDate.of(2021, 7, 5).toMillis()}', 
                    '${LocalDate.of(2021, 7, 6).toMillis()}',
                    '${LocalDate.of(2021, 7, 8).toMillis()}', 
                    '${LocalDate.of(2021, 7, 9).toMillis()}', 
                    '${LocalDate.of(2021, 7, 13).toMillis()}', 
                    0,
                    '', '', '', '${false}')
                """.trimIndent())
                db.execSQL("""
                    INSERT INTO ipo_model (id, company_name, company_code, company_description, market_type, 
                    image, sales_amount, operating_profit, net_income, ipo_price, ipo_amount, estimate_ipo_price, estimate_ipo_amount, 
                    lead_manager, demand_forecast_start_date, demand_forecast_end_date,
                    apply_start_date, apply_end_date, refund_date, listing_date, 
                    competition_rate, institutional_competition_rate, lockup_rate, bookmark) 
                    VALUES(2, '이노뎁', '303530', '소프트웨어 개발 및 공급업', 1, 'http://www.ipostock.co.kr/upload/img/corp/20201224.png', 
                    '8,541,843,525', '-2,837,139,113', '-2,704,433,501', '18,000원', '189억원', '14,000원 ~ 18,000원', '147억원 ~ 189억원', 
                    '하이투자증권', 
                    '${LocalDate.of(2021, 6, 3).toMillis()}', 
                    '${LocalDate.of(2021, 6, 4).toMillis()}',
                    '${LocalDate.of(2021, 6, 9).toMillis()}', 
                    '${LocalDate.of(2021, 6, 10).toMillis()}', 
                    '${LocalDate.of(2021, 6, 14).toMillis()}', 
                    '${LocalDate.of(2021, 6, 18).toMillis()}',
                    '629:1', '1,573:1', '6.48%', '${false}')
                """.trimIndent())

                db.execSQL("""
                    INSERT INTO ipo_model (id, company_name, company_code, company_description, market_type, 
                    image, sales_amount, operating_profit, net_income, ipo_price, ipo_amount, estimate_ipo_price, estimate_ipo_amount, 
                    lead_manager, demand_forecast_start_date, demand_forecast_end_date,
                    apply_start_date, apply_end_date, refund_date, listing_date, 
                    competition_rate, institutional_competition_rate, lockup_rate, bookmark) 
                    VALUES(3, '삼성머스트스팩5호', '380320', '금융 지원 서비스업', 2, 'http://www.ipostock.co.kr/upload/img/corp/20201224.png', 
                    '', '', '', '2,000원', '80억원', '2,000원 ~ 2,000원', '80억원 ~ 80억원', 
                    '삼성증권', 
                    '${LocalDate.of(2021, 6, 3).toMillis()}', 
                    '${LocalDate.of(2021, 6, 4).toMillis()}',
                    '${LocalDate.of(2021, 6, 8).toMillis()}', 
                    '${LocalDate.of(2021, 6, 9).toMillis()}', 
                    '${LocalDate.of(2021, 6, 11).toMillis()}', 
                    '${LocalDate.of(2021, 6, 17).toMillis()}',
                    '908.5:1', '729.6:1', '7.00%', '${false}')
                """.trimIndent())

                db.execSQL("""
                    INSERT INTO ipo_model (id, company_name, company_code, company_description, market_type, 
                    image, sales_amount, operating_profit, net_income, ipo_price, ipo_amount, estimate_ipo_price, estimate_ipo_amount, 
                    lead_manager, demand_forecast_start_date, demand_forecast_end_date,
                    apply_start_date, apply_end_date, refund_date, listing_date, 
                    competition_rate, institutional_competition_rate, lockup_rate, bookmark) 
                    VALUES(4, '크래프톤', '259960', '소프트웨어 개발 및 공급업', 0, 'http://www.ipostock.co.kr/upload/img/corp/202148(1).png', 
                    '460,993,100,237', '227,177,957,356', '194,018,137,317', '', '', '458,000원 ~ 557,000원', '46,076억원 ~ 56,035억원', 
                    '미래에셋증권, NH투자증권, 삼성증권', 
                    '${LocalDate.of(2021, 6, 28).toMillis()}', 
                    '${LocalDate.of(2021, 7, 9).toMillis()}',
                    '${LocalDate.of(2021, 7, 14).toMillis()}', 
                    '${LocalDate.of(2021, 7, 15).toMillis()}', 
                    '${LocalDate.of(2021, 7, 19).toMillis()}', 
                    0,
                    '', '', '', '${false}')
                """.trimIndent())

                db.execSQL("""
                    INSERT INTO ipo_model (id, company_name, company_code, company_description, market_type, 
                    image, sales_amount, operating_profit, net_income, ipo_price, ipo_amount, estimate_ipo_price, estimate_ipo_amount, 
                    lead_manager, demand_forecast_start_date, demand_forecast_end_date,
                    apply_start_date, apply_end_date, refund_date, listing_date, 
                    competition_rate, institutional_competition_rate, lockup_rate, bookmark) 
                    VALUES(5, '큐라클', '365270', '자연과학 및 공학 연구개발업', 0, 'http://www.ipostock.co.kr/upload/img/corp/202145(2).png', 
                    '', '-2,436,953,361', '-2,415,690,291', '', '', '20,000원 ~ 25,000원', '427억원 ~ 533억원', 
                    '삼성증권, NH투자증권', 
                    '${LocalDate.of(2021, 7, 7).toMillis()}', 
                    '${LocalDate.of(2021, 7, 8).toMillis()}',
                    '${LocalDate.of(2021, 7, 13).toMillis()}', 
                    '${LocalDate.of(2021, 7, 14).toMillis()}', 
                    '${LocalDate.of(2021, 7, 16).toMillis()}', 
                    0,
                    '', '', '', '${false}')
                """.trimIndent())
            }
        })
        .build()

}