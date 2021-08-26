package com.example.bombx

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import java.util.Collections.shuffle

var xC = mutableListOf<Int>()

var yClick = 0

var temp = mutableSetOf<Int>()

var win = false


class Wired(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    var bg = Paint()
    var lineR = Paint()
    var lineG = Paint()
    var lineB = Paint()
    var bomb = Paint()
    var disguise = Paint()

    var red = Paint()
    var blue = Paint()

    var number = Paint()


    init {
        bg.color = Color.WHITE
        bg.style = Paint.Style.FILL_AND_STROKE

        lineR.color = Color.RED
        lineR.style = Paint.Style.FILL
        lineR.isAntiAlias = true
        lineR.strokeWidth = 20f

        lineG.color = Color.GREEN
        lineG.style = Paint.Style.FILL
        lineG.isAntiAlias = true
        lineG.strokeWidth = 20f

        lineB.color = Color.BLACK
        lineB.style = Paint.Style.FILL
        lineB.isAntiAlias = true
        lineB.strokeWidth = 20f


        newGame()
    }

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheight: Int) {
        super.onSizeChanged(width, height, oldwidth, oldheight)
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bg)

        canvas?.drawLine(
            300f,
            (360 + xC[0] * 200).toFloat(),
            800f,
            (360 + xC[0] * 200).toFloat(),
            lineR
        )

        canvas?.drawLine(
            300f,
            (360 + xC[1] * 200).toFloat(),
            800f,
            (360 + xC[1] * 200).toFloat(),
            lineR
        )

        canvas?.drawLine(
            300f,
            (360 + xC[2] * 200).toFloat(),
            800f,
            (360 + xC[2] * 200).toFloat(),
            lineG
        )

        canvas?.drawLine(
            300f,
            (360 + xC[3] * 200).toFloat(),
            800f,
            (360 + xC[3] * 200).toFloat(),
            lineG
        )

        canvas?.drawLine(
            300f,
            (360 + xC[4] * 200).toFloat(),
            800f,
            (360 + xC[4] * 200).toFloat(),
            lineB
        )

        canvas?.drawLine(
            300f,
            (360 + xC[5] * 200).toFloat(),
            800f,
            (360 + xC[5] * 200).toFloat(),
            lineB
        )

        Log.d("🥰🥰🥰","${xC}")

        if(((xC[0] - xC[1] == 1)||(xC[1] - xC[0] == 1))&&((xC[2] - xC[3] == 1)||(xC[3] - xC[2] == 1))&&((xC[4] - xC[5] == 1)||(xC[5] - xC[4] == 1))){
            win = true
        }

    }//l,t,r,b

    /**
    canvas?.drawRect(x1Blue, y1Blue, x2Blue, y2Blue, blue)

    canvas?.drawText("${numMines}", x1Blue + 25f, y1Blue + 60f, number)

    canvas?.drawRect(x1Red, y1Red, x2Red, y2Red, red)

    extraCanvas.drawRect(x1Blue, y1Blue, x2Blue, y2Blue, blue)
    extraCanvas.drawText("${numMines}", x1Blue + 25f, y1Blue + 60f, number)
    extraCanvas.drawRect(x1Red, y1Red, x2Red, y2Red, red)

    canvas?.drawBitmap(extraBitmap, 0f, 0f, null)

     **/

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                yClick = event.y.toInt()
            }
        }
        if((yClick in 280..440)||(yClick in 480..640)||(yClick in 580..840)||(yClick in 880..1040)||(yClick in 1080..1240 )||(yClick in 1280..1440)){
            processSwitch(yClick)
        }
        return true
    }

    private fun processSwitch(coor : Int) {

        when(yClick){
            in 280..440 -> temp.add(0)
            in 480..640 -> temp.add(1)
            in 580..840 -> temp.add(2)
            in 880..1040 -> temp.add(3)
            in 1080..1240 -> temp.add(4)
            in 1280..1440 -> temp.add(5)
        }

        if(temp.size == 2){
            doIt()
        }
    }

    private fun doIt() {

        var temp1 = 0
        var temp2 = 0

        for( i in 0..5){
            if (temp.elementAt(0) == xC[i]){
                temp1 = i
            }
        }

        for( i in 0..5){
            if (temp.elementAt(1) == xC[i]){
                temp2 = i
            }
        }
        var o1 = xC[temp1]
        var o2 = xC[temp2]

        xC[temp1] = o2
        xC[temp2] = o1

        temp.clear()

        postInvalidate()
    }

    fun newGame(){
        xC.clear()
        var numbers = mutableListOf<Int>(0, 1, 2, 3, 4, 5)
        var size = numbers.size
        shuffle(numbers)
        xC.addAll(numbers)
        win = false
    }



}

