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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingParsingService {

    private final ParkingRepository parkingRepository;

    static final String PARKINGURL = "http://api.data.go.kr/openapi/tn_pubr_prkplce_info_api";
    static final String SERVICEKEY = "이부분은 yml로 빼든 해야지";
    static final String URLTYPE = "json";


    @Transactional
    public void save() throws Exception {
        for (int i = 1; i <= totalRow(); i += 1000) {
            parkingRepository.saveAll(parsing(i));
        }
    }

    private static int totalRow() throws Exception {
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(readUrl(1, 1));
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");

        return (Integer) body.get("totalCount");
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
                    .latitude((double) row.get("longitude"))
                    .longitude((double) row.get("longitude"))
                    .tel((String) row.get("phoneNumber"))
                    .numParking((Integer) row.get("prkcmprt"))
                    .info((String) row.get("parkingchrgeInfo"))

                    .basicTime((LocalTime) row.get("basicTime"))
                    .basicCharge((Integer) row.get("basicCharge"))
                    .addUnitTime((LocalTime) row.get("addUnitTime"))
                    .addUnitCharge((Integer) row.get("addUnitCharge"))
                    .dayTime((LocalTime) row.get("dayTime"))
                    .dayCharge((Integer) row.get("dayCharge"))
                    .monthCharge((Integer) row.get("monthCharge"))
                    .payType((String) row.get("payType"))

                    .operateDay((String) row.get("operDay"))
                    .weekdayOpenTime((LocalTime) row.get("weekdayOperOpenHhmm"))
                    .weekdayCloseTime((LocalTime) row.get("weekdayOperColseHhmm"))
                    .satOpenTime((LocalTime) row.get("satOperOperOpenHhmm"))
                    .satCloseTime((LocalTime) row.get("satOperCloseHhmm"))
                    .holOpenTime((LocalTime) row.get("holidayOperOpenHhmm"))
                    .holCloseTime((LocalTime) row.get("holidayCloseOpenHhmm"))
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
