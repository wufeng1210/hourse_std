package com.hourse.web.provider;

import com.hourse.web.model.ImageInfo;
import com.hourse.web.util.SqlProviderUtil;

/**
 * Created by 吴峰 on 2017/5/14.
 */
public class ImageProvider {
    private final String TBL_ORDER = "image_info AS t ";

    /**
     * 增加房屋图片信息，返回房屋图片id
     * @param imageInfo
     * @return
     */
    public String insert(ImageInfo imageInfo) {
        return SqlProviderUtil.provideInsertNotBlank(imageInfo, "image_info");
    }

}
