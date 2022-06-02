package com.websarva.wings.android.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbarを取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // ツールバーにロゴを表示
        toolbar.setLogo(R.mipmap.ic_launcher)
        // アクションバーにツールバーを設定
        setSupportActionBar(toolbar)

        // CollapsingToolBarLayoutを取得
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        // タイトルを設定
        toolbarLayout.title = getString(R.string.toolbar_title)
        // 通常サイズ時の文字色を設定
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        // 縮小サイズ時の文字色を設定
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        // 画面部品RecyclerViewを取得
        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        // LinearLayoutManagerオブジェクトを生成
        val layout = LinearLayoutManager(this@MainActivity)
        // RecyclerViewにレイアウトマネージャーとしてLinearLayoutManagerを設定
        lvMenu.layoutManager = layout
        // 定食メニューListオブジェクトをprivateメソッドを利用して用意し、プロパティに格納
        val menuList = createTeishokuList()
        // SimpleAdapterを生成
        val adapter = RecyclerListAdapter(menuList)
        // アダプタを登録
        lvMenu.adapter = adapter

        // 区切り線専用のオブジェクトを追加
        val decorator = DividerItemDecoration(this@MainActivity, layout.orientation)
        // RecyclerViewに区切り線オブジェクトを設定
        lvMenu.addItemDecoration(decorator)
    }

    private inner class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // リスト1行分中でメニュー名を表示する画面部品
        var _tvMenuNameRow: TextView
        // リスト1行分中でメニュー金額を表示する画面部品
        var _tvMenuPriceRow: TextView

        init {
            // 引数で渡されたリスト1行分の画面部品中から表示に使われるTextViewを取得
            _tvMenuNameRow = itemView.findViewById(R.id.tvMenuNameRow)
            _tvMenuPriceRow = itemView.findViewById(R.id.tvMenuPriceRow)
        }
    }

    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>):
    RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            // レイアウトインフレータを取得
            val inflater = LayoutInflater.from(this@MainActivity)
            // row.xmlをインフレートし、1行分の画面部品とする
            val view = inflater.inflate(R.layout.row, parent, false)
            // インフレーとされた1行分の画面部品にリスナを設定
            view.setOnClickListener(ItemClickListener())
            // ビューホルダオブジェクトを生成
            val holder = RecyclerListViewHolder(view)
            // 生成したビューホルダをリターン
            return holder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            // リストデータをから該当1行分のデータを取得
            val item = _listData[position]
            // メニュー名文字列を取得
            val menuName = item["name"] as String
            // メニュー名文字列を取得
            val menuPrice = item["price"] as Int
            // 表示用に金額を文字列に変換
            val menuPriceStr = menuPrice.toString()
            // メニュー名と金額をビュー掘るっだ中のTextViewに設定
            holder._tvMenuNameRow.text = menuName
            holder._tvMenuPriceRow.text = menuPriceStr
        }

        override fun getItemCount(): Int {
            // リストデータ中の件数をリターン
            return _listData.size
        }
    }

    private inner class ItemClickListener: View.OnClickListener {
        override fun onClick(view: View) {
            // タップされたLinearLayout内にあるメニュー名表示TextViewを取得
            val tvMenuName = view.findViewById<TextView>(R.id.tvMenuNameRow)
            // メニュー名表示TextViewから表示されているメニュー名文字列を取得
            val menuName = tvMenuName.text.toString()
            // トーストに表示する文字列を生成
            val msg = getString(R.string.msg_header) + menuName
            // トースト表示
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        // 定食メニュー用のListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // から揚げ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食", "price" to 800, "desc" to "若鶏のから揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンバグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンバー定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ンバグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハグ", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンババ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハグ", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンババ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハグ", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンババ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハグ", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンババ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハグ", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        // ハンバーグ定食 のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = mutableMapOf<String, Any>("name" to "ハンババ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }
}