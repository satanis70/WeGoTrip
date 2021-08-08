package example.wegotrip.models

import android.media.MediaPlayer
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Player(
    var mediaPlayer: @RawValue MediaPlayer
): Parcelable
