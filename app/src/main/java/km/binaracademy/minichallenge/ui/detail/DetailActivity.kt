package km.binaracademy.minichallenge.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import km.binaracademy.minichallenge.data.Biodata
import km.binaracademy.minichallenge.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private lateinit var biodata: Biodata

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        bindData()
    }

    private fun bindData() {
        binding.tvProfile.text = biodata.name
        binding.tvJob.text = biodata.job
        Glide.with(this)
            .load(biodata.imgProfileUrl)
            .centerCrop()
            .into(binding.ivProfile)
    }

    private fun getIntentData() {
        intent.extras?.getParcelable<Biodata>(EXTRAS_DATA)?.let {
            biodata = it
        }
    }

    companion object {
        const val EXTRAS_DATA = "EXTRAS_DATA"

        @JvmStatic
        fun startActivity(context: Context?, biodata: Biodata) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_DATA, biodata)
            context?.startActivity(intent)
        }
    }

}