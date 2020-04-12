package com.demo.mine.mvvm.view

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Path
import android.graphics.PathMeasure
import android.os.Build
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.common.base.BaseFragment
import com.common.core.RouterConstants
import com.demo.R
import com.demo.mine.mvvm.viewmodel.MineViewModel
import com.demo.mine.view.BottomDialog
import com.demo.mine.view.BreatheInterpolator
import kotlinx.android.synthetic.main.mine_fragment_mine.*


@Route(path = RouterConstants.MINE_FRAGMENT)
class MineFragment : BaseFragment<MineViewModel>() {
    override fun providerVMClass() = MineViewModel::class.java
    override fun getLayoutId() = R.layout.mine_fragment_mine

    override fun initView() {
        mine_txt.setOnClickListener {
            BottomDialog.Builder()
                    .setDialogData(arrayOf("0", "1", "2"))
                    .setCallBackDismiss(true)
                    .setItemLineColor(R.color.line_color)
                    .setItemOnListener(object : BottomDialog.BottomDialogOnClickListener {
                        override fun onDialogClick(tag: String?) {
                            Toast.makeText(context, tag, Toast.LENGTH_SHORT).show()
                        }
                    })
                    .show(fragmentManager, "tag")
        }

        //up
        img_icon_lay1.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay1, img_icon_lay_folt)
        }

        img_icon_lay2.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay2, img_icon_lay_folt)
        }

        img_icon_lay3.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay3, img_icon_lay_folt)
        }

        //dw
        img_icon_lay4.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay_folt1, img_icon_lay4)
        }

        img_icon_lay5.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay_folt2, img_icon_lay5)
        }

        img_icon_lay6.setOnClickListener {
            addGoods(img_icon_lay, img_icon_lay_folt3, img_icon_lay6)

            addGoods(img_icon_lay_folt4, img_icon_c1, img_icon_cont1)
            addGoods(img_icon_lay_folt4, img_icon_c4, img_icon_cont1)

        }

    }

    /**
     * 开启缩放渐变呼吸动画
     */
    private fun startScaleBreathAnimation(view: View, s: Float, e: Float) {
        val scaleX: ObjectAnimator = ObjectAnimator.ofFloat(view, "scaleX", s, e)
        val scaleY: ObjectAnimator = ObjectAnimator.ofFloat(view, "scaleY", s, e)
        scaleX.setRepeatCount(ValueAnimator.INFINITE)
        scaleY.setRepeatCount(ValueAnimator.INFINITE)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.setDuration(400)
        animatorSet.setInterpolator(BreatheInterpolator())
        animatorSet.start()
    }


    /**
     * 点对点移动
     */
    private fun addGoods(prtV: View, starV: View, endV: View) {
        // 创造出执行动画的主题goodsImg（这个图片就是执行动画的图片,从开始位置出发,经过一个抛物线（贝塞尔曲线））
//        val goods = View(context)
//        val params = RelativeLayout.LayoutParams(100, 100)
//        img_icon_lay.addView(goods, params)

        // 得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        val parentLocation = IntArray(2)
        prtV.getLocationInWindow(parentLocation)

        // （用于计算动画开始的坐标）
        val startLoc = IntArray(2)
        starV.getLocationInWindow(startLoc)

        // (用于计算动画结束后的坐标)
        val endLoc = IntArray(2)
        endV.getLocationInWindow(endLoc)

        // 开始掉落的起始点
        val startX = startLoc[0] - parentLocation[0].toFloat()
        val startY = startLoc[1] - parentLocation[1].toFloat()

        // 掉落后的终点坐标
        val toX: Float = endLoc[0] - parentLocation[0].toFloat()
        val toY = endLoc[1] - parentLocation[1].toFloat()

        // 开始绘制贝塞尔曲线
        val path = Path()
        // 移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY)
        // 使用二阶贝塞尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
//        path.quadTo((startX + toX) / 2, startY, toX, toY)
        path.lineTo(toX, toY)
        // mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，如果是true，path会形成一个闭环  // 路径测量
        var mPathMeasure: PathMeasure? = PathMeasure(path, false)
        // 属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        val valueAnimator = ValueAnimator.ofFloat(0f, mPathMeasure!!.getLength())

        valueAnimator.duration = 2000
        // 匀速线性插值器
        valueAnimator.interpolator = LinearInterpolator()
        val mCurrentPosition = FloatArray(2)// 贝塞尔曲线中间过程点坐标

//        valueAnimator.addUpdateListener { animation ->
//            // 当插值计算进行时，获取中间的每个值，
//            // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
//            val value = animation.animatedValue as Float
//            // 获取当前点坐标封装到mCurrentPosition
//            // boolean getPosTan(float distance, float[] pos, float[] tan) ：
//            // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
//            // mCurrentPosition此时就是中间距离点的坐标值
//            mPathMeasure!!.getPosTan(value, mCurrentPosition, null)
//            // （动画图片）的坐标设置为该中间点的坐标
//            goodsImg.translationX = mCurrentPosition.get(0)
//            goodsImg.translationY = mCurrentPosition.get(1)
//        }

        //添加监听
        //添加监听
        valueAnimator.addUpdateListener { animation ->
            //获取当前位置
            val value = animation.animatedValue as Float
            //传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
            // 离的坐标点和切线，pos会自动填充上坐标
            mPathMeasure.getPosTan(value, mCurrentPosition, null)
            //打印当前坐标
            //                KLog.i(mCurrentPosition[0]+"    "+mCurrentPosition[1]);
            //设置视图坐标
            starV.setX(mCurrentPosition[0])
            starV.setY(mCurrentPosition[1])
        }

        /**
         * 设置循环次数,设置为 INFINITE 表示无限循环 ValueAnimator.INFINITE
         */
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE)
        /**
         * 设置循环模式
         * value 取值有 RESTART，REVERSE，
         */
//        valueAnimator.setRepeatMode()

        // 开始执行动画
        valueAnimator.start()
        // 动画结束后的处理
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData() {
        //呼吸灯动画测试
        startScaleBreathAnimation(img_icon1, 0.7f, 1f)
        startScaleBreathAnimation(img_icon2, 1f, 0.7f)
        startScaleBreathAnimation(img_icon3, 0.7f, 1f)
        startScaleBreathAnimation(img_icon4, 1f, 0.7f)
    }

}