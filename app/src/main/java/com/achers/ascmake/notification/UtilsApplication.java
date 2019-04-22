package com.achers.ascmake.notification;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created on 2016/9/24 14:12
 * <p>
 * author wang
 * <p>
 * Description:
 */

public class UtilsApplication {

    /**
     * 手机是否处于睡眠状态
     * 已测试
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isPhoneSleeping(Context context) {
        KeyguardManager kgMgr = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        boolean isSleeping = kgMgr.inKeyguardRestrictedInputMode();
        return isSleeping;
    }

    /**
     * 判断应用处于前台还是后台
     * 需要添加权限
     * <uses-permission android:name="android.permission.GET_TASKS" />
     * 已测试
     *
     * @param context Context
     * @return boolean
     */
    public static boolean isApplicationBackground(final Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用图标
     * 已测试
     *
     * @param mContext
     * @return Drawable
     */
    public static Drawable getAppIcon(Context mContext) {
        Drawable icon = null;
        final PackageManager pm = mContext.getPackageManager();
        String packageName = mContext.getPackageName();
        try {
            icon = pm.getApplicationIcon(packageName);
            return icon;
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * 在浏览器中打开url
     * 已测试
     *
     * @param mContext
     * @param url      url的格式必须是“http://”或者“https://”开头的格式
     */
    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    /**
     * 在地图中展示用户搜索的位置
     * 已测试
     *
     * @param mContext
     * @param address  输入的位置，文本
     */
    public static void showAddressOnMap(Context mContext, String address) {
        address = address.replace(' ', '+');
        Intent geoIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + address));
        mContext.startActivity(geoIntent);
    }

    /**
     * 获取手机品牌，手机型号，Android系统版本，应用版本
     */
    public static String getUser_Agent() {
        String ua = "Android;" + getVendor() + "-" + getDevice() + ";" + getSDKVersion() + ";"
                + getOSVersion();
        return ua;
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getVendor() {
        return Build.BRAND;
    }

    public static String getDevice() {
        return Build.MODEL;
    }

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String deviceId(Activity activity) {
        TelephonyManager tm = (TelephonyManager) activity.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        return deviceId;
    }

    /**
     * 获取手机联网ip
     */
    public static String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {

                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取手机UUID
     */
    public static String getUUID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String deviceId = " ";

        try {
            deviceId = tm.getDeviceId();
        } catch (SecurityException exception) {
//            Logger.d("deviceId exception>>" + exception);
        }

        return deviceId;
    }

    /**
     * 获取手机MAC地址
     */

    public static String getMac() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(
                    "cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (Exception ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return macSerial;
    }

    /**
     * 获取应用版本号
     * 已测试
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = "0";
        try {
            version = context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     * 手机是否安装了指定包名的应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstallApkWithName(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 显示通知
     *
     * @param context
     * @param icon
     * @param title
     * @param content
     * @param resultPendingIntent
     */
    public static void showNotification(
            Context context, int icon, String title, String content, PendingIntent resultPendingIntent) {

        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setDefaults(Notification.DEFAULT_VIBRATE);

        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager.notify(0x12, mBuilder.build());

    }

    private static int notifyId = 0;

    /**
     * 显示弹窗的重载方法
     *
     * @param context
     * @param icon
     * @param title
     * @param content
     * @param intent
     */
    public static void showNotification(
            Context context, int icon, String title, String content, Intent intent) {
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(icon)
//                        .setContentTitle(title)
//                        .setContentText(content)
//                        .setDefaults(Notification.DEFAULT_VIBRATE)
//                        .setContentIntent(pendingIntent);
//
//        Notification build = mBuilder.build();
//        build.flags = Notification.FLAG_AUTO_CANCEL;
//
//        NotificationManager mNotificationManager = (NotificationManager)
//                context.getSystemService(NOTIFICATION_SERVICE);
//
//        notifyId++;
//
//        mNotificationManager.notify(notifyId, build);

        //用户点击通知时，发送广播
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);

        notifyId++;

        NotificationCompat.Builder mBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "ASIO_TEA";
            String channelName = "臻茶";

            NotificationChannel channel = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_DEFAULT);

            mNotificationManager.createNotificationChannel(channel);
            mBuilder =
                    new NotificationCompat.Builder(context,channelId)
                            .setSmallIcon(icon)
                            .setContentTitle(title)
                            .setContentText(content)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentIntent(pendingIntent);
//                            .setChannelId(channelId);

        } else {
            mBuilder =
                    new NotificationCompat.Builder(context,"ASIO_TEA")
                            .setSmallIcon(icon)
                            .setContentTitle(title)
                            .setContentText(content)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentIntent(pendingIntent);
        }


        Notification build = mBuilder.build();
        build.flags = Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(notifyId, build);

    }

    /**
     * 是否安装微信
     *
     * @param context 上下文
     * @return Boolean
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * 检测是是否安装支付宝
     * @param context
     * @return
     */
    public static boolean isAliPayInstalled(Context context) {

        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    /**
     * 获取MAC地址
     */
    public static String getMacAddress(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return getMacAddressPreM(context);
        } else {
            return getMacAddressPostM();
        }
    }

    /**
     * API 23 以上 hack 方式获取 MAC 地址
     */
    private static String getMacAddressPostM() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString((b & 0xFF) | 0xFFFFFF00).substring(6));
                    res1.append(":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
//            Logger.getLogger().e(ex.toString());
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressPreM(Context context) {
        String macStr;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getMacAddress() != null) {
            macStr = wifiInfo.getMacAddress();
        } else {
            macStr = "";
        }
        return macStr;
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
}
