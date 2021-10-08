package km.binaracademy.minichallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Biodata(
    var name: String,
    var job: String,
    var country: String,
    var imgProfileUrl: String
): Parcelable