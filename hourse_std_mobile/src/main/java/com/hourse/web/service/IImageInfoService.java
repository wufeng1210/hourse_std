package com.hourse.web.service;


import com.hourse.web.model.ImageInfo;

import java.util.List;

public interface IImageInfoService {
    public List<ImageInfo> getImageInfo(ImageInfo imageInfo);

    public int insertImageInfo(String imageBase, ImageInfo imageInfo);
}
