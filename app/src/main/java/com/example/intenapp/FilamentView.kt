package com.example.intenapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Choreographer
import android.view.MotionEvent
import android.view.SurfaceView
import com.google.android.filament.Filament
import com.google.android.filament.Skybox
import com.google.android.filament.utils.*
import java.net.URI
import java.nio.ByteBuffer

@SuppressLint("ClickableViewAccessibility")
class FilamentView(context: Context, attrs: AttributeSet? = null) : SurfaceView(context, attrs), Choreographer.FrameCallback {

    private val choreographer: Choreographer = Choreographer.getInstance()
    private lateinit var modelViewer: ModelViewer
    private var isHovering = false

    init {
        Filament.init()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        modelViewer = ModelViewer(this)
        modelViewer.scene.skybox = Skybox.Builder().color(0.9f, 0.9f, 1.0f, 1.0f).build(modelViewer.engine)

        // Menggunakan model kuda dari Google untuk tes diagnostik
        val modelUrl = "https://storage.googleapis.com/ar-answers-in-search-models/static/ToyHorse/glTF/ToyHorse.gltf"
        modelViewer.loadModelGltf(URI.create(modelUrl), null)
        modelViewer.transformToUnitCube()

        setOnTouchListener { _, event ->
            modelViewer.onTouchEvent(event)
            when (event.action) {
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN -> isHovering = true
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isHovering = false
            }
            true
        }

        choreographer.postFrameCallback(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        choreographer.removeFrameCallback(this)
        modelViewer.destroy()
    }

    override fun doFrame(frameTimeNanos: Long) {
        choreographer.postFrameCallback(this)
        if (isHovering) {
            val transformManager = modelViewer.engine.transformManager
            modelViewer.asset?.let { asset ->
                val instance = transformManager.getInstance(asset.root)
                val transform = transformManager.getTransform(instance)
                val rotation = Mat4.rotation(0.01f, 0f, 1f, 0f)
                transformManager.setTransform(instance, Mat4.multiply(Mat4(transform), rotation).toFloatArray())
            }
        }
        modelViewer.render(frameTimeNanos)
    }
}