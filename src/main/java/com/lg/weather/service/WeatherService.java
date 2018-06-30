package com.lg.weather.service;

import com.lg.weather.Dto.AdminDto;
import com.lg.weather.Dto.BoardDto;
import com.lg.weather.Dto.WeatherDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface WeatherService {
    public Model firstContent(Model model) throws Exception;
    public void insertinfo(WeatherDto weatherDto) throws Exception;
    public int checkinfo() throws Exception;
    public int getLogin(HttpSession session, AdminDto adminDto) throws Exception;
    public void insertNotice(BoardDto boardDto) throws Exception;
    public Model getNotice(Model model) throws Exception;
}
