package com.data.bean.openeyes

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/8/30
 */

data class EyesPageData(var count: Int = 0,
                        var total: Int = 0,
                        var nextPageUrl: String?,
                        var date: Long = 0,
                        var nextPublishTime: Long = 0,
                        var refreshCount: Int = 0,
                        var lastStartId: Int = 0,
                        var itemList: List<ItemListBean>? = null)

data class ItemListBean(
        var type: String?,
        var data: DataBean?,
        var tag: String?
)

data class DataBean(
        var category: String? = null,
        var collected: Boolean = false,
        var consumption: ConsumptionBean? = null,
        var cover: CoverBean? = null,
        var dataType: String? = null,
        var date: Long = 0,
        var description: String? = null,
        var descriptionEditor: String? = null,
        var duration: Int = 0,
        var id: Int = 0,
        var idx: Int = 0,
        var library: String? = null,
        var playUrl: String? = null,
        var played: Boolean = false,
        var provider: ProviderBean? = null,
        var releaseTime: Long = 0,
        var slogan: String? = null,
        var thumbPlayUrl: String? = null,
        var title: String? = null,
        var type: String? = null,
        var webUrl: WebUrlBean? = null,
        var labelList: List<*>? = null,
        var playInfo: List<PlayInfoBean>? = null,
        var subtitles: List<*>? = null,
        var tags: List<TagsBean>? = null,
        var actionUrl: String? = null,
        var header: Any? = null,
        var image: String? = null,
        var label: Any? = null
)

data class ConsumptionBean(
        var collectionCount: Int = 0,
        var replyCount: Int = 0,
        var shareCount: Int = 0
)

data class CoverBean(
        var blurred: String? = null,
        var detail: String? = null,
        var feed: String? = null,
        var homepage: String? = null
)

data class ProviderBean(

        var alias: String? = null,
        var icon: String? = null,
        var name: String? = null
)

data class WebUrlBean(
        var forWeibo: String? = null,
        var raw: String? = null
)

data class PlayInfoBean(
        var height: Int = 0,
        var name: String? = null,
        var type: String? = null,
        var url: String? = null,
        var width: Int = 0,
        var urlList: List<UrlListBean>? = null
)

data class UrlListBean(

        var name: String? = null,
        var size: Int = 0,
        var url: String? = null
)

data class TagsBean(
        var actionUrl: String? = null,
        var id: Int = 0,
        var name: String? = null
)

class EyesPage(var num: Int, var page: Int)

