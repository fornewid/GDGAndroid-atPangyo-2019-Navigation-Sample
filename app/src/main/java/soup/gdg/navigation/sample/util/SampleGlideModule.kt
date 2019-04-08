package soup.gdg.navigation.sample.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class SampleGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .disallowHardwareConfig()
        )
        builder.setMemoryCache(
            LruResourceCache(
                MemorySizeCalculator.Builder(context)
                    .setMemoryCacheScreens(2f)
                    .build()
                    .memoryCacheSize.toLong()
            )
        )
        builder.setDiskCache(InternalCacheDiskCacheFactory(context))
    }

    override fun isManifestParsingEnabled(): Boolean = false
}
