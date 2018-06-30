package com.lg.weather.service;

import com.lg.weather.Dao.WeatherDao;
import com.lg.weather.Dto.AdminDto;
import com.lg.weather.Dto.BoardDto;
import com.lg.weather.Dto.WeatherDto;
import com.lg.weather.Util.DateLoader;
import com.lg.weather.Util.HtmlParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService, WeatherKeyInfo {

    @Autowired
    private WeatherDao weatherDao;

    List<WeatherDto> weatherDtoList;
    List<BoardDto> boardDtoList;
    HashMap<String, String> paramMap;

    private StringBuilder weatherStrUrl = new StringBuilder();

    @Override
    public Model firstContent(Model model) throws Exception {
        String now = new DateLoader().DateLoader();
        System.out.println(now);

        paramMap = new HashMap<String, String>();
        paramMap.put("baseDate",now.substring(0,8));
        paramMap.put("baseTime",now.substring(9,11) + "00");

        weatherDtoList=weatherDao.weatherSelect(paramMap);
        model.addAttribute("weatherDtoList", weatherDtoList);

        for(WeatherDto speed : weatherDao.weatherSelect(paramMap)) {
            System.out.println(speed);
        }
        return model;
    }

    @Override
    public void insertinfo(WeatherDto weatherDto) throws Exception {
        String now = new DateLoader().DateLoader();

        for (int j=0; j<REGION.length; j++) {
            weatherStrUrl.setLength(0);
            weatherStrUrl.append("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?serviceKey=" +
                    SERVICEKEY + "&base_date=" + now.substring(0, 8) + "&base_time=" + now.substring(9, 13));
            weatherStrUrl.append("&nx=" + REGION[j][0] + "&ny=" + REGION[j][1] + "&numOfRows=10&pageSize=10&pageNo=1&startPage=1&_type=json");
            String jsonSouce = new HtmlParser().HtmlParser(weatherStrUrl.toString());

            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonSouce);
                JSONObject response = (JSONObject) jsonObject.get("response");
                JSONObject body = (JSONObject) response.get("body");
                JSONObject items = (JSONObject) body.get("items");
                JSONArray item = (JSONArray) items.get("item");

                JSONObject weatherInfo = (JSONObject) item.get(0);
                weatherDto.setBaseDate(weatherInfo.get("baseDate").toString());
                weatherDto.setBaseTime(weatherInfo.get("baseTime").toString());
                weatherDto.setLOCATION(LOCATION[j]);


                for (int i = 0; i < item.size(); i++) {
                    weatherInfo = (JSONObject) item.get(i);
                    if (weatherInfo.get("category").equals("T1H")) {
                        weatherDto.setT1H(Double.parseDouble(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("RN1")) {
                        weatherDto.setRN1(Double.parseDouble(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("SKY")) {
                        weatherDto.setSKY(Integer.parseInt(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("PTY")) {
                        weatherDto.setPTY(Integer.parseInt(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("LGT")) {
                        weatherDto.setLGT(Integer.parseInt(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("VEC")) {
                        weatherDto.setVEC(Integer.parseInt(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("WSD")) {
                        weatherDto.setWSD(Double.parseDouble(weatherInfo.get("obsrValue").toString()));
                    } else if (weatherInfo.get("category").equals("REH")) {
                        weatherDto.setREH(Integer.parseInt(weatherInfo.get("obsrValue").toString()));
                    }
                }
            } catch (Exception e) {
                e.getMessage();
            }
            weatherDao.insertinfo(weatherDto);
        }
    }

    @Override
    public int checkinfo() throws Exception {
        String now = new DateLoader().DateLoader();
        paramMap = new HashMap<String, String>();
        paramMap.put("baseDate",now.substring(0,8));
        paramMap.put("baseTime",now.substring(9,11) + "00");

        return weatherDao.checkinfo(paramMap);
    }

    @Override
    public int getLogin(HttpSession session, AdminDto adminDto) throws Exception {
        paramMap = new HashMap<String, String>();
        paramMap.put("admin_id", adminDto.getAdmin_id());
        paramMap.put("admin_pass",adminDto.getAdmin_pass());

        return weatherDao.getLogin(paramMap);
    }


    @Override
    public void insertNotice(BoardDto boardDto) throws Exception {
        weatherDao.insertBoard(boardDto);
     }

    @Override
    public Model getNotice(Model model) throws Exception {
        boardDtoList = weatherDao.getContent();
        model.addAttribute("boardList", boardDtoList);
        return model;
    }
}
