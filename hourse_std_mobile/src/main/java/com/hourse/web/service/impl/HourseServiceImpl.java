package com.hourse.web.service.impl;

import com.hourse.web.mapper.HourseMapper;
import com.hourse.web.model.Hourse;
import com.hourse.web.service.IHourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wufeng on 2017/4/11.
 */
@Service
@Transactional
public class HourseServiceImpl implements IHourseService {

    @Resource
    private HourseMapper hourseMapper;
    public List<Hourse> getHourseInfo(Hourse hourse) {
        List<Hourse> hoursesList = hourseMapper.getHourseInfo(hourse);
        return hoursesList;
    }
}
