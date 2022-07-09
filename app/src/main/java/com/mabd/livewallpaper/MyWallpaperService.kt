package com.mabd.livewallpaper

import android.graphics.*
import android.os.Handler
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder

class MyWallpaperService: WallpaperService() {


    private inner class MyWallpaperEngine: Engine() {
        private val handler = Handler()
        private val drawRunner = Runnable { draw() }

        private var width = 1
        private var height = 1

        private val circles = mutableListOf<PointF>()

        private var visible = true
        private val maxNumber = 50
        private val touchEnabled = false

        private val paint: Paint = Paint().apply {
            isAntiAlias = true
            color = Color.WHITE
            style = Paint.Style.FILL
            strokeJoin = Paint.Join.ROUND
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)

            this.visible = visible
            if (visible) {
                handler.post(drawRunner)
            } else {
                handler.removeCallbacks(drawRunner);
            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder?) {
            super.onSurfaceDestroyed(holder)
            this.visible = false
            handler.removeCallbacks(drawRunner)
        }

        override fun onSurfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
            this.width = width
            this.height = height
        }


        private fun draw() {
            val holder = surfaceHolder
            var canvas: Canvas? = null
            try {
                canvas = holder.lockCanvas()
                if (canvas == null) {
                    return
                }

                val x = (width * Math.random()).toFloat()
                val y = (height * Math.random()).toFloat()

                if (circles.size >= maxNumber*3) {
                    circles.clear()
                }

                circles.add(PointF(x, y))
                drawCircles(canvas, circles)

            } finally {
                if (canvas != null) holder.unlockCanvasAndPost(canvas)
            }
            handler.removeCallbacks(drawRunner)
            if (visible) {
                handler.postDelayed(drawRunner, 1000)
            }
        }

        private fun drawCircles(canvas: Canvas, circles: List<PointF>) {
            canvas.drawColor(Color.BLACK)
            for (point in circles) {
                canvas.drawCircle(point.x, point.y, 5f, paint)
            }
        }


    }



    override fun onCreateEngine(): Engine {
        return MyWallpaperEngine()
    }


}