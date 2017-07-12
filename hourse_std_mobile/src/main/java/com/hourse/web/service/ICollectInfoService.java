package com.hourse.web.service;


import com.hourse.web.model.CollectInfo;
import com.hourse.web.model.ImageInfo;

import java.util.List;

public interface ICollectInfoService {

    public List<CollectInfo> getCollectInfo(CollectInfo collectInfo);

    public int insertCollectInfo(CollectInfo collectInfo);

    public int update(CollectInfo collectInfo);

}
