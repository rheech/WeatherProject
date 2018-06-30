package com.lg.weather.Dao;

import com.lg.weather.Dto.BoardDto;
import com.lg.weather.Dto.WeatherDto;


import java.util.HashMap;
import java.util.List;

public interface WeatherDao {
    public void insertinfo(WeatherDto weatherDto) throws Exception;
    public List<WeatherDto> weatherSelect(HashMap<String, String> map) throws Exception;
    public int checkinfo(HashMap<String, String> map) throws Exception;
    public int getLogin(HashMap<String, String> map) throws Exception;
    public void insertBoard(BoardDto boardDto) throws Exception;
    public List<BoardDto> getContent() throws Exception;
}
