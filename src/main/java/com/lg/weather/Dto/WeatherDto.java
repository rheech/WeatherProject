package com.lg.weather.Dto;

import lombok.Data;

@Data
public class WeatherDto {
    private String baseDate;  // 실측날자
    private String baseTime;  // 실측시간
    private int LGT;    // 낙뢰 없음(0), 있음(1)
    private int PTY;    // 강수형태 없음(0), 비(1), 진눈개비(2), 눈(3)
    private int REH;  // 습도
    private Double RN1;  // 1시간 강수량
    private int SKY;  // 하늘상태 맑음(1), 구름조금(2), 구름많음(3), 흐림(4)
    private Double T1H; //기온
    private int VEC;  // 풍향 0-45 N-NE, 45-90 NE-E, 90-135 E-SE, 135-180 SE-S, 180-225 S-SW, 225-270 SW-W, 270-315 W-NW, 315-360 NW-N
    private Double WSD;   // 풍속
    private String LOCATION;

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(String baseTime) {
        this.baseTime = baseTime;
    }

    public int getLGT() {
        return LGT;
    }

    public void setLGT(int LGT) {
        this.LGT = LGT;
    }

    public int getPTY() {
        return PTY;
    }

    public void setPTY(int PTY) {
        this.PTY = PTY;
    }

    public int getREH() {
        return REH;
    }

    public void setREH(int REH) {
        this.REH = REH;
    }

    public Double getRN1() {
        return RN1;
    }

    public void setRN1(Double RN1) {
        this.RN1 = RN1;
    }

    public int getSKY() {
        return SKY;
    }

    public void setSKY(int SKY) {
        this.SKY = SKY;
    }

    public Double getT1H() {
        return T1H;
    }

    public void setT1H(Double t1H) {
        T1H = t1H;
    }

    public int getVEC() {
        return VEC;
    }

    public void setVEC(int VEC) {
        this.VEC = VEC;
    }

    public Double getWSD() {
        return WSD;
    }

    public void setWSD(Double WSD) {
        this.WSD = WSD;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
}