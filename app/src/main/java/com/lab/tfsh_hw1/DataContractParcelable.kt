package com.lab.tfsh_hw1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Контракт класс, по которому мы производиам обмен данными между компонентами приложения
 */
@Parcelize
data class DataContractParcelable(
    val contactNames: List<String>?,
    val eventNames: List<String>?
) : Parcelable