package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloPurple
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloRedLight
import java.util.UUID


object MenuItemContentType {
    const val TYPE1 = 401
    const val TYPE2 = 402
    const val TYPE3 = 403
    const val TYPE4 = 404
}

data class MenuItemModel(
    val id: String = UUID.randomUUID().toString(),
    val contentType: Int = 1,
    val description: String = "Test",
    val action: String = "",
    val color: Color = Color.Transparent,
    val imageUrl: String = "",
    @DrawableRes val imageResId: Int = 0,
)

///


fun generateType1List(): List<MenuItemModel> {
    val uuid = UUID.randomUUID().toString()
    return listOf<MenuItemModel>(
        MenuItemModel(
            id = "{$uuid}_1",
            color = HoloRedLight,
            description = "Auction Online",
            action = "auction_online"
        ),
        MenuItemModel(
            id = "{$uuid}_2",
            color = HoloOrangeLight,
            description = "Auction Online 2nd",
            action = "auction_online2"
        ),
        MenuItemModel(
            id = "{$uuid}_3",
            color = HoloGreenLight,
            description = "Auction Offline",
            action = "auction_offline"
        ),
        MenuItemModel(
            id = "{$uuid}_41",
            color = HoloBlueLight,
            description = "Download",
            action = "download"
        ),
        MenuItemModel(
            id = "{$uuid}_5",
            color = HoloPurple,
            description = "Upload",
            action = "upload"
        ),
        MenuItemModel(
            id = "{$uuid}_6",
            color = DarkerGray,
            description = "Unit Test",
            action = "unit_test",
        ),
    )
}


fun generateType2List(): List<MenuItemModel> {
    val dataList: MutableList<MenuItemModel> = mutableListOf()

    val uuid = UUID.randomUUID().toString()
    val urlArray = arrayOf<String>(

        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8504546/0/638229888195170000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8546390/0/638229888129470000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8504545/0/638229888195170000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8252773/0/638229888129470000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8504544/0/638229888195170000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8250116/0/638229888129470000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8504509/0/638229888129470000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Cropped/SalePage/8216174/0/638229888158300000?v=1",

        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/660bb28e-37b2-4720-8fc9-fd422bb139ad/sportswear-premium-essentials-%E7%94%B7%E6%AC%BE-t-%E6%81%A4-K9vnhV.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/164bdbd9-5db5-497f-a8aa-5b0ed34dc4aa/sportswear-%E9%95%B7%E8%A2%96%E4%B8%8A%E8%A1%A3-mlTqkV.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/1b2b9417-5d4f-442a-817d-0ec0c2b7ec55/sportswear-%E7%94%B7%E6%AC%BE%E7%89%88-t-%E6%81%A4-ptNVST.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/adfd77cc-aa6e-4035-8d6a-b819191b3e62/dri-fit-strike-%E7%9F%AD%E8%A2%96%E4%B8%8A%E8%A1%A3-fnwCv5.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/77d88870-47a9-4529-8cd6-37194288e0d2/dri-fit-primary-%E7%94%B7%E6%AC%BE%E8%A8%93%E7%B7%B4-t-%E6%81%A4-z9dSFb.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/2d315637-0b9b-40d0-91c9-7d042e97ef54/dri-fit-one-%E6%A8%99%E6%BA%96%E5%89%AA%E8%A3%81%E9%95%B7%E8%A2%96%E4%B8%8A%E8%A1%A3-dTQ53q.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/ed91ca71-83f4-4545-9f1d-9694b28c64d9/dri-fit-hyverse-%E7%94%B7%E6%AC%BE%E7%9F%AD%E8%A2%96%E5%81%A5%E8%BA%AB%E8%83%8C%E5%BF%83-qjZM1B.png",
        "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/8369c3e2-8f44-4d05-8f06-8fc8e7ef7a8a/air-%E7%9F%AD%E8%A2%96%E7%9F%AD%E7%89%88%E4%B8%8A%E8%A1%A3-5Ntd4C.png",

        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9714680/0/638497266693730000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9567555/0/638493952472300000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9734923/0/638497527246330000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9714601/0/638499850782770000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9714632/0/638497266673570000?v=1",
        "https://diz36nn4q02zr.cloudfront.net/webapi/imagesV3/Original/SalePage/9714672/0/638497267078630000?v=1",
    )

    for (i in urlArray.indices) {
        val delegate = MenuItemModel(
            id = "${uuid}_${i + 1}",
            description = "photo-${i + 1}",
            contentType = MenuItemContentType.TYPE2,
            color = DarkerGray,
            imageUrl = urlArray[i],
        )
        dataList.add(delegate)
    }

    return dataList
}





