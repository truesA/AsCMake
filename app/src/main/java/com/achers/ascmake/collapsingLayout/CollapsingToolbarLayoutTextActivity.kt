//package com.achers.ascmake.collapsingLayout
//
//import android.content.Context
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v4.view.ViewPager
//import com.achers.ascmake.R
//import com.achers.ascmake.collapsingLayout.pageindicator.TabPageIndicator
//import com.achers.ascmake.collapsingLayout.fragment.TabFrament
//import android.support.v4.os.HandlerCompat.postDelayed
//import android.view.MotionEvent
//import android.view.View
//import android.widget.TextView
//import android.widget.Toast
//import java.nio.file.Files.size
//import com.achers.ascmake.collapsingLayout.FocusCircleView
//import kotlinx.android.synthetic.main.activity_collapsing_toolbar_layout_text.*
//
//
//class CollapsingToolbarLayoutTextActivity : AppCompatActivity() {
//    private lateinit var mVpContent: ViewPager
//    private lateinit var mFocusViewPager: ImageViewPager
//    private val mData1 = ArrayList<String>()
//    private val mData2 =  ArrayList<String>()
//    private lateinit var mFocusCircleView: FocusCircleView
//    private lateinit var mFocusAdapter: ImageViewPager.BasePagerAdapter<String>
//    private lateinit var mPagerAdapterCompat: FragmentPagerAdapterCompat
//    private lateinit var mTabIndicator: TabPageIndicator
//    private lateinit var mRefreshLayout: RefreshLayout
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_collapsing_toolbar_layout_text)
//        initData();
//        initView();
//        initListener();
//    }
//
//
//    private fun initData() {
//        for (i in 1..8) {
//            mData1.add("第" + i + "页")
//        }
//        for (i in 1..5) {
//            mData2.add("焦点图第" + i + "页")
//        }
//    }
//
//
//    private fun initView() {
//
//        //焦点图和指示器
////        mFocusViewPager = findViewById<ImageViewPager>(R.id.imageview)
//        mFocusViewPager = ImageViewPager.BasePagerAdapter<String>(this, mData2)
//        mFocusViewPager.setAdapter(  object : ImageViewPager.BasePagerAdapter<String>(this, mData2) {
//            override fun getItemView(context: Context?, position: Int, t: String?): View {
//                val view = View.inflate(context, android.R.layout.simple_list_item_1, null) as TextView
//                view.setText(t)
//                return view
//            }
//        })
//        focusCircleView.setCount(mFocusAdapter.getDataCount())
//        focusCircleView.setCurrentFocus(imageview.currentItem % mData2.size)
//
//
//        //内容视图
////        mVpContent = findViewById<ViewPager>(R.id.vp)
//        vp.setAdapter( object : FragmentPagerAdapterCompat(supportFragmentManager) {
//            override fun getItem(position: Int): Fragment {
//                return TabFrament.newInstance(position)
//            }
//
//            override fun getCount(): Int {
//                return mData1.size
//            }
//
//            override fun getPageTitle(position: Int): CharSequence {
//                return mData1.get(position)
//            }
//        })
////        mTabIndicator = findViewById<TabPageIndicator>(R.id.tab_indicator)
//        tab_indicator!!.setViewPager(vp)
//        tab_indicator!!.notifyDataSetChanged()
//        tab_indicator!!.setCurrentItem(0)
//
//        //刷新view
////        mRefreshLayout = findViewById<RefreshLayout>(R.id.refreshLayout)
//    }
//
//
//    private fun initListener() {
//        imageview.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//
//            override fun onPageSelected(position: Int) {
//                var position = position
//                if (mData2 != null && !mData2.isEmpty()) {
//                    position = position % mData2.size
//                    focusCircleView.setCurrentFocus(position)
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
//
//        refreshLayout!!.setOnRefreshListener(RefreshLayout.OnRefreshListener {
//            refreshLayout.postDelayed(Runnable {
//                refreshLayout.onComplete()
//                Toast.makeText(this, "刷新完毕", Toast.LENGTH_SHORT).show()
//                val frament = mPagerAdapterCompat!!.getFragment(vp.getCurrentItem()) as TabFrament
//                frament.refresh()
//            }, 500)
//        })
//    }
//
//    override fun onResume() {
//        super.onResume()
//        imageview.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        imageview.onPause()
//    }
//
//
//}
