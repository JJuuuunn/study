package com.yang.kakaomap.parking.service;

import com.yang.kakaomap.parking.entity.Parking;
import com.yang.kakaomap.parking.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingParsingService {

    private final ParkingRepository parkingRepository;

    private static final String PARKINGURL = "http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api";
    private static final String SERVICEKEY = "YwHbHVu7k3RhjvNkyg4KAPrGCajCTKFrKTwDQoPNH%2BVunkDaf8kt1wewc1ExeDLpnqaYUZs9m6oMpqPo2CEFcw%3D%3D";
    private static final String URLTYPE = "json";


    @Transactional
    public void save() throws Exception {
        long totalRow = totalRow();
        if (totalRow % 1000 > 0) totalRow = totalRow / 1000 + 1;

        for (int i = 1; i <= totalRow; i ++) {
            parkingRepository.saveAll(parsing(i));
        }
    }

    private static long totalRow() throws Exception {
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(readUrl(1, 1));
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");

        return Long.valueOf((String) body.get("totalCount"));
    }
    private List<Parking> parsing(int pageNo) throws Exception {
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(readUrl(pageNo, 1000));
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONArray items = (JSONArray) body.get("items");

        List<Parking> parkingList = new ArrayList<>();
        for (int j = 0; j < items.size(); j++) {
            JSONObject row = (JSONObject) items.get(j);

            Parking parking = Parking.builder()
                    .parkingNo((String) row.get("prkplceNo"))
                    .parkingName((String) row.get("prkplceNm"))
                    .stNameAddress((String) row.get("rdnmadr"))
                    .rdNumAddress((String) row.get("lnmadr"))
                    .latitude(((String) row.get("latitude")))
                    .longitude((String) row.get("longitude"))
                    .tel((String) row.get("phoneNumber"))
                    .numParking((String) row.get("prkcmprt"))
                    .info((String) row.get("parkingchrgeInfo"))

                    .basicTime((String) row.get("basicTime"))
                    .basicCharge((String) row.get("basicCharge"))
                    .addUnitTime((String) row.get("addUnitTime"))
                    .addUnitCharge((String) row.get("addUnitCharge"))
                    .dayTime((String) row.get("dayTime"))
                    .dayCharge((String) row.get("dayCharge"))
                    .monthCharge((String) row.get("monthCharge"))
                    .payType((String) row.get("payType"))

                    .operateDay((String) row.get("operDay"))
                    .weekdayOpenTime((String) row.get("weekdayOperOpenHhmm"))
                    .weekdayCloseTime((String) row.get("weekdayOperColseHhmm"))
                    .satOpenTime((String) row.get("satOperOperOpenHhmm"))
                    .satCloseTime((String) row.get("satOperCloseHhmm"))
                    .holOpenTime((String) row.get("holidayOperOpenHhmm"))
                    .holCloseTime((String) row.get("holidayCloseOpenHhmm"))
                    .build();

            parkingList.add(parking);
        }
        return parkingList;
    }

    private static String readUrl(int pageNo, int numOfRows) throws Exception {
        BufferedInputStream reader = null;

        try {
            URL url = new URL(PARKINGURL + "?" +
                    "serviceKey=" + SERVICEKEY +
                    "&pageNo=" + pageNo +
                    "&numOfRows=" + numOfRows +
                    "&type=" + URLTYPE
            );

            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i = 0;
            byte[] b = new byte[4096];
            while ((i = reader.read(b)) != -1) {
                buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null) reader.close();
        }
    }
}
