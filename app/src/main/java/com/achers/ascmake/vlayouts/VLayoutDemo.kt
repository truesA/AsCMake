package com.achers.ascmake.vlayouts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.achers.ascmake.R
import kotlinx.android.synthetic.main.activity_vlayout_demo.*
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import android.support.v7.widget.RecyclerView
import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.achers.ascmake.slidemenu.ui.FriendFragment
import com.achers.ascmake.tabs.SimpleFragment
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper
import com.alibaba.android.vlayout.layout.StickyLayoutHelper
import java.util.*


class VLayoutDemo : AppCompatActivity() {

    private lateinit var  mAdapters: ArrayList<DelegateAdapter.Adapter<*>> //存放各个模块的适配器

    private val mTabContents = ArrayList<Fragment>()
    private var mAdapter: FragmentPagerAdapter? = null
    private var mViewPager: ViewPager? = null
    private val mDatas = Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vlayout_demo)
        initDatas()
        initRecyclerView()


    }

    private fun initRecyclerView() {
        mAdapters = ArrayList<DelegateAdapter.Adapter<*>>()
        //初始化
        val layoutManager = VirtualLayoutManager(this)
        vlayout_recy.setLayoutManager(layoutManager)

//设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()
        vlayout_recy.setRecycledViewPool(viewPool)
        viewPool.setMaxRecycledViews(0, 5)
        viewPool.setMaxRecycledViews(1, 50)
        viewPool.setMaxRecycledViews(2, 6)
        viewPool.setMaxRecycledViews(3, 6)
        viewPool.setMaxRecycledViews(4, 2)
        viewPool.setMaxRecycledViews(5, 1)

        val delegateAdapter = DelegateAdapter(layoutManager, true)


        val arrayList = ArrayList<String>()
        arrayList.add("https://oss.zunlongcp.com/users/2018/6/2018061593099.jpg")
        arrayList.add("https://oss.zunlongcp.com/users/2018/6/2018061553970.jpeg")
        arrayList.add("https://oss.zunlongcp.com/users/2018/6/2018061593099.jpg")
        val bannerAdapter =BannerAdapter(this,LinearLayoutHelper(),1,arrayList)
        //banner

        //把轮播器添加到集合
        mAdapters!!.add(bannerAdapter)
//        mAdapters.add(init(this))
        mAdapters.add(gridInit(this))
        mAdapters.add(gridTeaInit(this))
        mAdapters.add(initSingleLayout(this))
        mAdapters.add(gridTeaInit(this))
        mAdapters.add(gridInit3(this))
        mAdapters.add(StickyInit(this))
        mAdapters.add(initViewPageSingleLayout(this))
//        mAdapters.add(init(this))


        //设置适配器
        delegateAdapter.setAdapters(mAdapters);

        vlayout_recy.setAdapter(delegateAdapter)
    }

    fun init(context: Context): DelegateRecyclerAdapter {
        val linearLayoutHelper = LinearLayoutHelper()
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5)
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.marginBottom = 20
        //设置间距
        linearLayoutHelper.setMargin(20, 20, 20, 20)
        val delegateRecyclerAdapter = DelegateRecyclerAdapter(context, linearLayoutHelper, "LinearLayoutHelper")
        return delegateRecyclerAdapter
    }
    //网格
    fun gridInit(context: Context):GridImageAdapter{
        val gridLayoutHelper = GridLayoutHelper(4)
        gridLayoutHelper.setMargin(0, 0, 0, 0)
        gridLayoutHelper.setPadding(0, 0, 0, 0)
        gridLayoutHelper.setVGap(0)// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE)
        gridLayoutHelper.setAutoExpand(true)//是否自动填充空白区域
        val arrayList = ArrayList<String>()
        arrayList.add("熟普")
        arrayList.add("生普")
        arrayList.add("茶具")
        arrayList.add("茶礼")
        val gridAdapter =GridImageAdapter(context,gridLayoutHelper,4,arrayList)
        return gridAdapter
    } //网格

    fun gridTeaInit(context: Context):TeaImageGridAdapter{
        val gridLayoutHelper = GridLayoutHelper(2)
        gridLayoutHelper.setMargin(0, 0, 0, 0)
        gridLayoutHelper.setPadding(0, 0, 0, 0)
        gridLayoutHelper.setVGap(0)// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.BLUE)
        gridLayoutHelper.setAutoExpand(true)//是否自动填充空白区域
        val arrayList = ArrayList<String>()
        arrayList.add("熟普")
        arrayList.add("生普")
        arrayList.add("茶具")
        arrayList.add("茶礼")
        val gridAdapter =TeaImageGridAdapter(context,gridLayoutHelper,4,arrayList)
        return gridAdapter
    }

    fun initSingleLayout(context: Context): SingleLayoutAdapter {
        val singleLayoutHelper = SingleLayoutHelper()
        //设置间距
        singleLayoutHelper.setMargin(20, 20, 20, 20)
        return SingleLayoutAdapter(context, singleLayoutHelper, 1,"新品推荐")
    }

    //网格
    fun gridInit3(context: Context):GridImageAdapter{
        val gridLayoutHelper = GridLayoutHelper(3)
        gridLayoutHelper.setMargin(0, 0, 0, 0)
        gridLayoutHelper.setPadding(0, 0, 0, 0)
        gridLayoutHelper.setVGap(0)// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE)
        gridLayoutHelper.setAutoExpand(true)//是否自动填充空白区域
        val arrayList = ArrayList<String>()
        arrayList.add("熟普")
        arrayList.add("生普")
        arrayList.add("生普")
        val gridAdapter =GridImageAdapter(context,gridLayoutHelper,3,arrayList)
        return gridAdapter
    } //网格
    fun StickyInit(context: Context):StickyLayoutAdapter{
        val stickyLayoutHelper=StickyLayoutHelper(true)
        // 公共属性
        stickyLayoutHelper.setItemCount(1);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(3f);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

//        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量
        return StickyLayoutAdapter(context, stickyLayoutHelper, 1,"StickyInit")
    }



    fun initViewPageSingleLayout(context: Context): ViewPagerAdapter {
        val singleLayoutHelper = SingleLayoutHelper()
        //设置间距
        singleLayoutHelper.setMargin(20, 20, 20, 20)

        return ViewPagerAdapter(context, singleLayoutHelper, 1,mAdapter)
    }


    private fun initDatas() {
        for (data in mDatas) {
//            val fragment = SimpleFragment.newInstance(data)
            val fragment = FriendFragment()
            mTabContents.add(fragment)
        }

        mAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mTabContents.size
            }

            override fun getItem(position: Int): Fragment {
                return mTabContents.get(position)
            }
        }
    }
}
