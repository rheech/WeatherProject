package com.lg.weather.Dao;

import com.lg.weather.Dto.BoardDto;
import com.lg.weather.Dto.WeatherDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class WeatherDaoImpl implements WeatherDao {

    @Resource
    private SqlSession session;

    private static String namespace = "com.lg.weather.Dao.WeatherDao";

    @Override
    public void insertinfo(WeatherDto weatherDto) throws Exception {
        session.insert(namespace+".insertinfo", weatherDto);
    }

    @Override
    public List<WeatherDto> weatherSelect(HashMap<String, String> paramMap) throws Exception {
        return session.selectList(namespace+".weatherSelect", paramMap);
    }

    @Override
    public int checkinfo(HashMap<String, String> map) throws Exception {
        return session.selectOne(namespace+".checkinfo",map);
    }

    @Override
    public int getLogin(HashMap<String, String> map) {
        return session.selectOne(namespace+".getLogin", map);
    }

    @Override
    public void insertBoard(BoardDto boardDto) throws Exception {
        session.insert(namespace+".insertBoard", boardDto);
    }

    @Override
    public List<BoardDto> getContent() throws Exception {
        return session.selectList(namespace+".getContent");
    }
}
