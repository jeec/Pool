package com.jerry.mycompose_feature

import androidx.lifecycle.ViewModel
import java.util.Calendar

class Bank2ViewModel : ViewModel() {
    private val salaryData30K: DoubleArray = doubleArrayOf(
        26804.62,
        26642.63,
        27793.08,
        26881.45,
        25998.10,
        26793.90,
        26338.61,
        27198.22,
        26419.60,
        25094.96,
        26331.21,
        25980.06,
    )

    //按月薪30k，到手约25来算余额
    private val balanceData30K: DoubleArray = doubleArrayOf(
        72569.88,
        50295.40,
        27159.90,
        73452.55,
        52145.19,
        30919.49,
        91835.01,
        72941.90,
        50961.51,
        38936.67,
        30927.82,
        13618.05,
    )
    var offset = 5000
        private set

    val sum = lazy {
        var temp: Double =  0.0
        salaryData30K.forEach {
            temp+=it
        }
        temp
    }

    val data = mutableListOf<SalaryData>()

    private val lastWorkingDate: Calendar = Calendar.getInstance()

    init {
        lastWorkingDate.set(2024, 1, 10)
        repeat(12) { index ->
            val monthFormat = String.format("%02d", lastWorkingDate.get(Calendar.MONTH) + 1)
            val date = "${lastWorkingDate.get(Calendar.YEAR)}年${monthFormat}月"
            var monthDay = lastWorkingDate.get(Calendar.DAY_OF_MONTH)
            var weekDay = lastWorkingDate.get(Calendar.DAY_OF_WEEK)
            val oriWeekDay = lastWorkingDate.get(Calendar.DAY_OF_WEEK)
            //遇到周末顺延至周一
            if (oriWeekDay == Calendar.SATURDAY) {
                monthDay+=2
                weekDay = Calendar.MONDAY
            } else if (oriWeekDay == Calendar.SUNDAY) {
                monthDay+=1
                weekDay = Calendar.MONDAY
            }
            val weekDayFormat = formChineseWeekDay(weekDay)
            lastWorkingDate.add(Calendar.MONTH, -1)

            val salaryItem = SalaryData(
                date,
                monthDay,
                weekDayFormat,
                "工资",
                corporate = "公司",
                money = salaryData30K[index],
                balance = balanceData30K[index]
            )
            if (index == 0) {
                salaryItem.apply {
                    this.monthDay = 8
                    this.weekDay = formChineseWeekDay(Calendar.THURSDAY)
                }
            }
            data.add(salaryItem)
        }
    }
}

data class SalaryData(
    val date: String,
    var monthDay: Int = 10,
    var weekDay: String,
    val cate: String,
    val corporate: String,
    val money: Double,
    val balance: Double
)

fun formChineseWeekDay(weekIndex: Int): String {
    when (weekIndex) {
        Calendar.SUNDAY -> {
            return "周日"
        }

        Calendar.MONDAY -> {
            return "周一"
        }

        Calendar.TUESDAY -> {
            return "周二"
        }

        Calendar.WEDNESDAY -> {
            return "周三"
        }

        Calendar.THURSDAY -> {
            return "周四"
        }

        Calendar.FRIDAY -> {
            return "周五"
        }

        Calendar.SATURDAY -> {
            return "周六"
        }

        else -> {
            return ""
        }
    }
}

