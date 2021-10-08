package km.binaracademy.minichallenge.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import km.binaracademy.minichallenge.R
import km.binaracademy.minichallenge.data.Biodata
import km.binaracademy.minichallenge.databinding.FragmentProfileBinding
import km.binaracademy.minichallenge.ui.detail.DetailActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private var biodata = Biodata(
        "Muhammad Hermas P",
        "Android Developer",
        "Indonesia",
        "https://avatars.githubusercontent.com/u/21256595?v=4"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        binding.cvHomeProfile.setOnClickListener{ navigateToDetail() }
    }

    private fun bindData() {
        binding.tvProfile.text = biodata.name
        Glide.with(this)
            .load(biodata.imgProfileUrl)
            .centerCrop()
            .into(binding.ivProfile)
    }

    private fun navigateToDetail(){
        DetailActivity.startActivity(context, biodata)
    }

}