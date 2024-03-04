package com.kwawannan.countrylistmvvm

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.bumptech.glide.module.AppGlideModule
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.append(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
//            .register(SVG::class.java, InputStream::class.java, SvgDecoder())
    }

    // If you're not interested in manifest parsing, return false here.
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
class SvgDecoder : ResourceDecoder<InputStream, SVG> {
    override fun handles(source: InputStream, options: Options): Boolean = true

    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<SVG>? {
        try {
            val svg = SVG.getFromInputStream(source)
            return SimpleResource(svg)
        } catch (e: SVGParseException) {
            throw IOException("Cannot load SVG from stream", e)
        }
    }
}

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {
    override fun transcode(toTranscode: Resource<SVG>, options: Options): Resource<PictureDrawable>? {
        val svg: SVG = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }
}
