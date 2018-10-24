package com.aliumujib.visionapisample.codescanner

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.ViewGroup
import com.aliumujib.visionapisample.R

/**
 * Created by aliumujib on 04/05/18.
 */

class ScannerOverlay : ViewGroup {
    private var left: Float = 0.toFloat()
    private var top: Float = 0.toFloat()
    private var endY: Float = 0.toFloat()
    private var rectWidth: Int = 0
    private var rectHeight: Int = 0
    private var frames: Int = 0
    private var revAnimation: Boolean = false
    private var lineColor: Int = 0
    private var lineWidth: Int = 0

    constructor(context: Context) : super(context) {}

    @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : super(context, attrs, defStyle) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ScannerOverlay,
                0, 0)
        rectWidth = a.getInteger(R.styleable.ScannerOverlay_square_width, resources.getInteger(R.integer.scanner_rect_width))
        rectHeight = a.getInteger(R.styleable.ScannerOverlay_square_height, resources.getInteger(R.integer.scanner_rect_height))
        lineColor = a.getColor(R.styleable.ScannerOverlay_line_color, ContextCompat.getColor(context, R.color.scanner_line))
        lineWidth = a.getInteger(R.styleable.ScannerOverlay_line_width, resources.getInteger(R.integer.line_width))
        frames = a.getInteger(R.styleable.ScannerOverlay_line_speed, resources.getInteger(R.integer.line_width))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    public override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        left = ((w - dpToPx(rectWidth)) / 2).toFloat()
        top = ((h - dpToPx(rectHeight)) / 2).toFloat()
        endY = top
        super.onSizeChanged(w, h, oldw, oldh)
    }

    fun dpToPx(dp: Int): Int {
        val displayMetrics = resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // draw transparent rect
        val cornerRadius = 0
        val eraser = Paint()
        eraser.isAntiAlias = true
        eraser.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)


        val rect = RectF(left, top, dpToPx(rectWidth) + left, dpToPx(rectHeight) + top)
        canvas.drawRoundRect(rect, cornerRadius.toFloat(), cornerRadius.toFloat(), eraser)

        // draw horizontal line
        val line = Paint()
        line.color = lineColor
        line.strokeWidth = java.lang.Float.valueOf(lineWidth.toFloat())!!

        // draw the line to product animation
        if (endY >= top + dpToPx(rectHeight).toFloat() + frames.toFloat()) {
            revAnimation = true
        } else if (endY == top + frames) {
            revAnimation = false
        }

        // check if the line has reached to bottom
        if (revAnimation) {
            endY -= frames.toFloat()
        } else {
            endY += frames.toFloat()
        }
        canvas.drawLine(left, endY, left + dpToPx(rectWidth), endY, line)
        invalidate()
    }
}