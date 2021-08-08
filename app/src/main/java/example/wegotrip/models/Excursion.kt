package example.wegotrip.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Excursion(
    val id: String,
    val name: String,
    val stages: List<Stage>
): Parcelable