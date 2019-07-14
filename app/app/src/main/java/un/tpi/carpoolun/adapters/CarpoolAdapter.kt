package un.tpi.carpoolun.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import un.tpi.carpoolun.R
import un.tpi.carpoolun.models.carpool.Carpool

class CarpoolAdapter(private val mContext : Context, private val carpools: List<Carpool>)
    : RecyclerView.Adapter<CarpoolAdapter.CarpoolViewHolder>()
{
    companion object {
        const val TAG = "CarpoolUN.CarpoolAdapt"
    }

    override fun getItemCount(): Int {  return carpools.size  }

    override fun onBindViewHolder(viewHolder: CarpoolViewHolder, index: Int) {
        val carpool = carpools[index]

        val res = viewHolder.parentLayout.resources

        val titleStrId =
            if ( carpool.type == Carpool.OUT_OF_UN )  R.string.carpoolListFragment_titleTypeComes
            else R.string.carpoolListFragment_titleTypeGoes

        viewHolder.titleTV.text = res.getString(titleStrId, carpool.neighbourhood)

        viewHolder.driversNameTV.text = res.getString(
            R.string.carpoolListFragment_driver,
            carpool.driverName )

        viewHolder.departureTimeTV.text = res.getString(
            R.string.carpoolListFragment_time,
            carpool.time)

        viewHolder.capacityTV.text = res.getString(
            R.string.carpoolListFragment_capacity,
            carpool.capacityLeft.toString(),
            carpool.capacity.toString())

        viewHolder.feeTV.text = res.getString(
            R.string.carpoolListFragment_fee,
            carpool.fee.toString())


        viewHolder.parentLayout.setOnClickListener {
            Log.d(TAG, "Go to carpool ${carpool.id}")
            /*val intent = Intent(mContext, ShowUserActivity::class.java)
            intent.putExtra(ShowUserActivity.USER_ID, u.id)
            intent.putExtra(ShowUserActivity.USER_NAME, u.name)
            intent.putExtra(ShowUserActivity.USER_LAST_NAME, u.lastName)
            intent.putExtra(ShowUserActivity.USER_I_FOLLOW, u.iFollow)
            intent.putExtra(ShowUserActivity.USER_FOLLOWS_ME, u.followsMe)
            mContext.startActivity(intent)*/

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): CarpoolViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_carpool, viewGroup, false)
        return CarpoolViewHolder(v)
    }

    class CarpoolViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val parentLayout : CardView = v.findViewById(R.id.itemCarpool_parentLayout)
        val titleTV : TextView = v.findViewById(R.id.itemCarpool_title)
        val driversNameTV : TextView = v.findViewById(R.id.itemCarpool_driver)
        val capacityTV : TextView= v.findViewById(R.id.itemCarpool_capacity)
        val departureTimeTV : TextView = v.findViewById(R.id.itemCarpool_departureTime)
        val feeTV : TextView = v.findViewById(R.id.itemCarpool_fee)
    }
}