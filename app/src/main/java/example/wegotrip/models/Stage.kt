package example.wegotrip.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stage(
    val audio: String,
    val images: List<String>,
    val nameStages: String,
    val text: String
) : Parcelable