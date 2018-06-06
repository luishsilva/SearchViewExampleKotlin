package academico.cpb.com.br.searchviewexample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_item.view.*

class MainActitityAdapter (val pItens: ArrayList<Item>, val pContext: Context) : RecyclerView.Adapter<MainActitityAdapter.HolderView>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
        return HolderView(LayoutInflater.from(pContext).inflate(R.layout.custom_item,parent,false))
    }

    override fun getItemCount(): Int {
        return pItens.size
    }

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        holder?.product_image.setImageResource(pItens.get(position).photo)
        holder?.product_title.text = pItens.get(position).title
    }

    class HolderView(view: View) : RecyclerView.ViewHolder(view) {
        var product_image = view.product_image
        var product_title = view.product_title

    }
}
