package com.hourse.web.service;


import com.hourse.web.model.Hourse;

import java.util.List;

public interface IHourseService {
    public List<Hourse> getHourseInfo(Hourse hourse);

    public List<Hourse> getMapInfo(Hourse hourse);

    public List<Hourse> getRecommendHourseInfo(Hourse hourse);

    public List<Hourse> queryHourseByisRentAndState(Hourse hourse);

    public int insert(Hourse hourse);
}
