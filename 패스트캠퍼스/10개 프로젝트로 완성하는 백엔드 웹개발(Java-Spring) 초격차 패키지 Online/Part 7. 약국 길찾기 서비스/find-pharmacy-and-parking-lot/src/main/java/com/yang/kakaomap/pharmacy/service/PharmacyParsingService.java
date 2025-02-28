package com.yang.kakaomap.pharmacy.service;

import com.yang.kakaomap.pharmacy.entity.Pharmacy;
import com.yang.kakaomap.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyParsingService {
    private final PharmacyRepository pharmacyRepository;

    private static final String URL = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyBassInfoInqire?";
    private static final String SERVICEKEY = "YwHbHVu7k3RhjvNkyg4KAPrGCajCTKFrKTwDQoPNH%2BVunkDaf8kt1wewc1ExeDLpnqaYUZs9m6oMpqPo2CEFcw%3D%3D";

    @Transactional
    public void save() {
        String totalCount = readUrl("totalCount", 1, 0).item(0).getFirstChild().getNodeValue();
        long pageNo = Long.parseLong(totalCount);
        if (pageNo % 1000 > 0) pageNo = pageNo / 1000 + 1;

        for (int i = 1; i <= pageNo; i++) {
            pharmacyRepository.saveAll(parsing("item", i, 1000));
        }
    }

    private List<Pharmacy> parsing(String tagName, int pageNo, int numOfRow) {
        try {
            String uri = URL +
                    "serviceKey=" + SERVICEKEY +
                    "&pageNo=" + pageNo +
                    "&numOfRows=" + numOfRow;

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(uri);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(tagName);

            List<Pharmacy> pharmacyList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                Element element = (Element) node;
                Pharmacy pharmacy = Pharmacy.builder()
                        .hpId(getTagValue("hpid", element))
                        .name(getTagValue("dutyName", element))
                        .info(getTagValue("dutyInf", element))
                        .tel(getTagValue("dutyTel1", element))
                        .address(getTagValue("dutyAddr", element))
                        .zipCode(getTagValue("postCdn1", element) + getTagValue("postCdn2", element))
                        .latitude(getTagValue("wgs84Lat", element))
                        .longitude(getTagValue("wgs84Lon", element))

                        .monOpenTime(getTagValue("dutyTime1s", element))
                        .monCloseTime(getTagValue("dutyTime1c", element))
                        .tueOpenTime(getTagValue("dutyTime1s", element))
                        .tueCloseTime(getTagValue("dutyTime1c", element))
                        .wedOpenTime(getTagValue("dutyTime1s", element))
                        .wedCloseTime(getTagValue("dutyTime1c", element))
                        .thuOpenTime(getTagValue("dutyTime1s", element))
                        .thuCloseTime(getTagValue("dutyTime1c", element))
                        .friOpenTime(getTagValue("dutyTime1s", element))
                        .friCloseTime(getTagValue("dutyTime1c", element))
                        .satOpenTime(getTagValue("dutyTime1s", element))
                        .satCloseTime(getTagValue("dutyTime1c", element))
                        .sunOpenTime(getTagValue("dutyTime1s", element))
                        .sunCloseTime(getTagValue("dutyTime1c", element))
                        .holOpenTime(getTagValue("dutyTime1s", element))
                        .holCloseTime(getTagValue("dutyTime1c", element))
                        .build();
                pharmacyList.add(pharmacy);
            }
            return pharmacyList;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private NodeList readUrl(String tagName, int pageNo, int numOfRow) {
        try {
            String uri = URL +
                    "serviceKey=" + SERVICEKEY +
                    "&pageNo=" + pageNo +
                    "&numOfRows=" + numOfRow;

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(uri);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(tagName);

            return nodeList;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTagValue(String tag, Element element) {
        String result = "";

        Optional<Node> optionalNode = Optional.ofNullable(element.getElementsByTagName(tag).item(0));
        if (optionalNode.isEmpty()) return null;

        NodeList nodeList = optionalNode.get().getChildNodes();

        result = nodeList.item(0).getTextContent();

        return result;
    }
}
