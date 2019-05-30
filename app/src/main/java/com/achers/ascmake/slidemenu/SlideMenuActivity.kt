package com.achers.ascmake.slidemenu

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.achers.ascmake.R
import com.achers.ascmake.slidemenu.adapter.SimpleViewPagerAdapter
import com.achers.ascmake.slidemenu.ui.ChatFragment
import com.achers.ascmake.slidemenu.ui.FriendFragment
import com.achers.ascmake.slidemenu.ui.TabOneFragment
import com.achers.ascmake.slidemenu.ui.TabTwoFragment
import kotlinx.android.synthetic.main.custom_drawerlayout.*


class SlideMenuActivity : AppCompatActivity() {
    //    //声明相关变量
    private lateinit var toolbar: Toolbar
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var lvLeftMenu: ListView
    private var lvs = arrayOf("List Item 01", "List Item 02", "List Item 03", "List Item 04")
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var arrayAdapterList: ArrayAdapter<String>
    private lateinit var ivRunningMan: TextView
    private var fragmentList: java.util.ArrayList<Fragment> = java.util.ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)
        findViews()



        fragmentList.add(TabTwoFragment())
        fragmentList.add(ChatFragment())
        fragmentList.add(FriendFragment())
        fragmentList.add(TabOneFragment())
        viewpage.adapter=SimpleViewPagerAdapter(supportFragmentManager,fragmentList)
        viewpage.offscreenPageLimit=4

        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色

        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                ivRunningMan.text = "打开"
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                ivRunningMan.text = "关闭"
            }
        }
        mDrawerToggle.syncState()
        mDrawerLayout.setDrawerListener(mDrawerToggle)
        //设置菜单列表
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs)
        lvLeftMenu.adapter = arrayAdapter
        val listData=ArrayList<String>()
        (0..59).mapTo(listData) { it.toString() }

        arrayAdapterList = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        lv_list.adapter=arrayAdapterList


    }

    private fun findViews() {
        ivRunningMan = findViewById<TextView>(R.id.iv_main)
        toolbar = findViewById<View>(R.id.tl_custom) as Toolbar
        mDrawerLayout = findViewById<DrawerLayout>(R.id.dl_left)
        lvLeftMenu = findViewById<ListView>(R.id.lv_left_menu)
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_slide_menu)
////        initStatusBar();
////        initView();
//    }

//    private val TAG = this.javaClass.simpleName
//    /**
//     * 初始化View
//     */
//    private fun initView() {
//
//        mainSlideMenu.setContentToggle(true)
//        //right
//        val slideTabLayout = findViewById<View>(R.id.fmr_tab) as TabLayout
//        val slideViewPager = findViewById<View>(R.id.fmr_vp) as ViewPager
//        slideTabLayout.addTab(slideTabLayout.newTab())
//        slideTabLayout.addTab(slideTabLayout.newTab())
//        slideViewPager.setAdapter(SlideRightMenuAdapter(supportFragmentManager))
//        slideTabLayout.setupWithViewPager(slideViewPager)
//        //left
//        val leftRecyclerView = findViewById<View>(R.id.cml_rv) as RecyclerView
//        leftRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val slideLeftAdapter = SlideLeftAdapter(this)
//        leftRecyclerView.adapter = slideLeftAdapter
//        //content
//        val contentTabLayout = findViewById<View>(R.id.am_tab) as TabLayout
//        val contentViewPager = findViewById<View>(R.id.am_vp) as ViewPager
//        contentTabLayout.addTab(contentTabLayout.newTab())
//        contentTabLayout.addTab(contentTabLayout.newTab())
//        contentViewPager.setAdapter(ContentAdapter(supportFragmentManager))
//        contentTabLayout.setupWithViewPager(contentViewPager)
//
//        findViewById<View>(R.id.fm_leftMenu).setOnClickListener(this)
//        findViewById<View>(R.id.fm_rightMenu).setOnClickListener(this)
//        mainSlideMenu.addOnSlideChangedListener(OnSlideChangedListener { slideMenu, isLeftSlideOpen, isRightSlideOpen -> Log.d(TAG, "onSlideChanged:isLeftSlideOpen=$isLeftSlideOpen:isRightSlideOpen=$isRightSlideOpen") })
//    }
//
//
//    /**
//     * 初始化沉浸式状态栏
//     */
//    private fun initStatusBar() {
//        //设置是否沉浸式
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
//        val flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//        //透明状态栏
//        window.setFlags(flag_translucent_status, flag_translucent_status)
//    }
//
//    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.fm_leftMenu -> mainSlideMenu.toggleLeftSlide()
//            R.id.fm_rightMenu -> mainSlideMenu.toggleRightSlide()
//        }
//    }
//
//    override fun onBackPressed() {
//        if (mainSlideMenu.isLeftSlideOpen() || mainSlideMenu.isRightSlideOpen()) {
//            mainSlideMenu.closeLeftSlide()
//            mainSlideMenu.closeRightSlide()
//        } else {
//            super.onBackPressed()
//        }
//    }
}
