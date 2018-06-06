package academico.cpb.com.br.searchviewexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val productList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adding product title and image in array list of Item type
        productList.add(Item("Harley Davidson Street 750 2016 Std",R.drawable.harleydavidson))
        productList.add(Item("Triumph Street Scramble 2017 Std",R.drawable.streetscramble1))
        productList.add(Item("Suzuki GSX R1000 2017 STD",R.drawable.suzukigsx1000))
        productList.add(Item("Suzuki GSX R1000 2017 R",R.drawable.suzukigsx1000r))
        productList.add(Item("Suzuki Gixxer 2017 SP",R.drawable.suzukigixxer2017))
        productList.add(Item("Suzuki Gixxer 2017 SF 2017 Fuel injected ABS",R.drawable.suzukigixxer2017sf))
        productList.add(Item("BMW R 1200 R 2017",R.drawable.bmwr1200r))
        productList.add(Item("BMW R 1200 RS 2017",R.drawable.bmwr1200rs))
        productList.add(Item("BMW R 1200 GSA 2017",R.drawable.bmwr120gsa))
        productList.add(Item("Royal Enfield Classic 350 2017 Gunmental Grey",R.drawable.class350gungrey))
        productList.add(Item("Honda MSX125 Grom 2018 STD",R.drawable.hondagrom))
        productList.add(Item("UM Motorcycles Renegade 2017 classic",R.drawable.renegadecommandoclassic))
        productList.add(Item("Ducati Scrambler 2017 Mach 2.0",R.drawable.scramblermach))
        productList.add(Item("yamaha Fazer 2017 25",R.drawable.yamahafazer))

        recycler_view_list.layoutManager = LinearLayoutManager(this)
        recycler_view_list.setHasFixedSize(true)
        recycler_view_list.adapter = MainActitityAdapter(productList,this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_file, menu)

        val myActionMenuItem = menu?.findItem(R.id.search)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        (searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as EditText).setHintTextColor(resources.getColor(R.color.primary_dark_material_light))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                myActionMenuItem?.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filtermodelist = filter(productList, newText)
                recycler_view_list.adapter = MainActitityAdapter(filtermodelist,baseContext)
                recycler_view_list.adapter.notifyDataSetChanged()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    fun filter(itemList: List<Item>, query : String): ArrayList<Item> {
        var queryLower = query.toLowerCase()
        val filteredModeList = ArrayList<Item>()
        for (model in itemList) {
            val text = model.title.toLowerCase()
            if (text.startsWith(queryLower)) {
                filteredModeList.add(model)
            }
        }
        return filteredModeList
    }
}
